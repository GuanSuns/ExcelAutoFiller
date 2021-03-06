package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet422CorePDM;
import excel.filler.model.Sheet422PersonalPDM;
import org.apache.poi.ss.usermodel.*;
import excel.filler.utils.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static excel.filler.utils.ExcelUtils.setDefaultDateCellStyle;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet422Filler {
    public static void fill(Sheet422PersonalPDM sheet422PDM
            , FileDestination destination) throws Exception{

        if(sheet422PDM == null || destination == null){
            throw new Exception("Invalid null arguments");
        }

        File destFile;
        if(destination.equals(FileDestination.Core)){
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.MonthlyCoreFile);
        }else{
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.MonthlyPersonalFile);
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
        int index = ExcelUtils.getLastRow(sheet422
                , ExcelConfig.Sheet422RecordStartRow
                , ExcelConfig.Sheet422InspectTimeCellIndex) + 1;

        Row row = ExcelUtils.getRow(sheet422, index);

        //Pour numeric data into an ArrayList
        ArrayList<String> data = new ArrayList<>();
        data.add(booleanIntToString(sheet422PDM.getTsUsage2().intValue()));

        if(destination.equals(FileDestination.Core)){
            Sheet422CorePDM sheet422CorePDM = (Sheet422CorePDM)sheet422PDM;
            data.add(booleanIntToString(sheet422CorePDM.getTsUsage3().intValue()));
            data.add(booleanIntToString(sheet422CorePDM.getTsUsage4().intValue()));
            data.add(booleanIntToString(sheet422CorePDM.getTsUsage3().intValue()));
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

        //Handle missing values
        if(sheet422PDM.getProvince() == null
                || sheet422PDM.getProvince().equals("")){
            sheet422PDM.setProvince(ExcelConfig.Sheet422DefaultProvince);
        }
        if(sheet422PDM.getBatch() == null){
            sheet422PDM.setBatch("");
        }
        if(sheet422PDM.getDate() == null){
            sheet422PDM.setDate(new Timestamp(new Date().getTime()));
        }

        sheet422PDM.setOrder1((long)(index - ExcelConfig.Sheet422RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet422PDM, destination);
    }

    private static String booleanIntToString(int booleanInt){
        if(booleanInt == 1){
            return ExcelConfig.descriptionHasOverloadedTable;
        }else{
            return ExcelConfig.descriptionNoOverloadedTable;
        }
    }

    //Write inspection time, order, batch ID and province
    private static void setBasicProperties(Row row
            , Sheet422PersonalPDM sheet422PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellDateStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellDateStyle, font);

        Cell cellDate = ExcelUtils.getCell(row, ExcelConfig.Sheet422InspectTimeCellIndex);
        ExcelUtils.setDefaultDateCellStyle(cellDateStyle, wb);
        cellDate.setCellStyle(cellDateStyle);
        cellDate.setCellValue(sheet422PDM.getDate());
        row.getSheet().autoSizeColumn(ExcelConfig.Sheet422InspectTimeCellIndex);

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
