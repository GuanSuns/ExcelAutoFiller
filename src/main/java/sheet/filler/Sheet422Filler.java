package sheet.filler;

import config.ExcelConfig;
import config.FileDestination;
import config.POIConfig;
import model.Sheet422CorePDM;
import model.Sheet422PersonalPDM;
import org.apache.poi.ss.usermodel.*;
import utils.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet422Filler {
    public static void fill(Sheet422PersonalPDM sheet422PDM
            , FileDestination destination) throws Exception{

        if(sheet422PDM == null || destination == null){
            throw new Exception("Invalid null arguments");
        }

        File destFile = null;
        if(destination.equals(FileDestination.Core)){
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.CoreSystemFile);
        }else{
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.PersonalSystemFile);
        }

        //Check if the file exists
        if(!destFile.isFile() || !destFile.exists()){
            throw new Exception("Cannot find file "
                    + destFile.getName());
        }

        FileInputStream is = new FileInputStream(destFile);
        //Create POI workbook
        Workbook wb = WorkbookFactory.create(is);

        //Get sheet from the workbook
        Sheet sheet422 = wb.getSheet(ExcelConfig.SheetName422);
        if(sheet422 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName422);
        }

        insert(sheet422, sheet422PDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet422
            , Sheet422PersonalPDM sheet422PDM
            , FileDestination destination) throws Exception{
        //Get the row index for the new data
        int index = sheet422.getLastRowNum() + 1;

        Row row = ExcelUtils.getRow(sheet422, index);

        //Handle missing values
        if(sheet422PDM.getProvince() == null
                || sheet422PDM.getProvince().equals("")){
            sheet422PDM.setProvince(ExcelConfig.Sheet422DefaultProvince);
        }
        if(sheet422PDM.getBatch() == null){
            sheet422PDM.setBatch("");
        }

        sheet422PDM.setOrder1((long)(index - ExcelConfig.Sheet422RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet422PDM, destination);

        //Pour numeric data into an ArrayList
        ArrayList<String> data = new ArrayList<>();
        data.add(sheet422PDM.getTsName2());
        data.add(sheet422PDM.getTsTotalSpace2());
        data.add(sheet422PDM.getTsUsedSpace2());
        data.add(sheet422PDM.getTsUsage2());
        data.add(sheet422PDM.getTsName3());
        data.add(sheet422PDM.getTsTotalSpace3());
        data.add(sheet422PDM.getTsUsedSpace3());
        data.add(sheet422PDM.getTsUsage3());

        if(destination.equals(FileDestination.Core)){
            Sheet422CorePDM sheet422CorePDM = (Sheet422CorePDM)sheet422PDM;
            data.add(sheet422CorePDM.getTsName4());
            data.add(sheet422CorePDM.getTsTotalSpace4());
            data.add(sheet422CorePDM.getTsUsedSpace4());
            data.add(sheet422CorePDM.getTsUsage4());
            data.add(sheet422CorePDM.getTsName5());
            data.add(sheet422CorePDM.getTsTotalSpace5());
            data.add(sheet422CorePDM.getTsUsedSpace5());
            data.add(sheet422CorePDM.getTsUsage5());
        }

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithString(row, ExcelConfig.Sheet422PersonalStart
                    , ExcelConfig.Sheet422PersonalEnd, data);
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet422PersonalBlankStart
                    , ExcelConfig.Sheet422PersonalBlankEnd);
        }else{
            ExcelUtils.fillRowWithString(row, ExcelConfig.Sheet422CoreStart
                    , ExcelConfig.Sheet422CoreEnd, data);
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet422CoreBlankStart
                    , ExcelConfig.Sheet422CoreBlankEnd);
        }
    }

    //Write inspection time, order, batch ID and province
    private static void setBasicProperties(Row row
            , Sheet422PersonalPDM sheet422PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);

        Cell cellOrder = ExcelUtils.getCell(row, ExcelConfig.Sheet422OrderCellIndex);
        cellOrder.setCellStyle(cellStyle);
        cellOrder.setCellValue(sheet422PDM.getOrder1());

        Cell cellBatch = ExcelUtils.getCell(row, ExcelConfig.Sheet422BatchCellIndex);
        cellBatch.setCellStyle(cellStyle);
        cellBatch.setCellValue(sheet422PDM.getBatch());

        Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet422ProvinceCellIndex);
        cellProvince.setCellStyle(cellStyle);
        cellProvince.setCellValue(sheet422PDM.getProvince());
    }
}
