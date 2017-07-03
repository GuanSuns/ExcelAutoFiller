package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet428CorePDM;
import excel.filler.model.Sheet428PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet428Filler {
    public static void fill(Sheet428PersonalPDM sheet428PDM
            , FileDestination destination) throws Exception{

        if(sheet428PDM == null || destination == null){
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
        Sheet sheet428 = wb.getSheet(ExcelConfig.SheetName428);
        if(sheet428 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName428);
        }

        insert(sheet428, sheet428PDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet428
            , Sheet428PersonalPDM sheet428PDM
            , FileDestination destination) throws Exception{
        //Get the row index for the new data
        int index = ExcelUtils.getLastRow(sheet428
                , ExcelConfig.Sheet428RecordStartRow
                , ExcelConfig.Sheet428TimeCellIndex) + 1;

        Row row = ExcelUtils.getRow(sheet428, index);

        //Handle missing values
        if(sheet428PDM.getProvince() == null
                || sheet428PDM.getProvince().equals("")){
            sheet428PDM.setProvince(ExcelConfig.Sheet428DefaultProvince);
        }
        if(sheet428PDM.getBatch() == null){
            sheet428PDM.setBatch("");
        }

        sheet428PDM.setOrder((long)(index - ExcelConfig.Sheet428RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet428PDM, destination);

        insertRecord(row, sheet428PDM, destination);

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet428PersonalBlankStart
                    , ExcelConfig.Sheet428PersonalBlankEnd);
        }else{
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet428CoreBlankStart
                    , ExcelConfig.Sheet428CoreBlankEnd);
        }
    }

    private static void insertRecord(Row row
            , Sheet428PersonalPDM sheet428PDM
            , FileDestination destination) throws Exception{

        ArrayList<String> strSQLResults = new ArrayList<>();

        if(destination.equals(FileDestination.Core)){
            Sheet428CorePDM sheet428CorePDM = (Sheet428CorePDM)sheet428PDM;

            strSQLResults.add(sheet428PDM.getStatus1());
            strSQLResults.add(sheet428PDM.getStatus2());
            strSQLResults.add(sheet428PDM.getStatus3());
            strSQLResults.add(sheet428PDM.getStatus4());

            ExcelUtils.fillRowWithString(row, ExcelConfig.Sheet428Start
                    , ExcelConfig.Sheet428CoreBlankStart, strSQLResults);
        }else{
            strSQLResults.add(sheet428PDM.getStatus1());
            strSQLResults.add("");
            strSQLResults.add(sheet428PDM.getStatus2());
            strSQLResults.add(sheet428PDM.getStatus3());
            strSQLResults.add("");
            strSQLResults.add(sheet428PDM.getStatus4());
            strSQLResults.add("");

            ExcelUtils.fillRowWithString(row, ExcelConfig.Sheet428Start
                    , ExcelConfig.Sheet428PersonalBlankStart, strSQLResults);
        }


    }

    //Write inspection time, order, batch ID and province
    private static void setBasicProperties(Row row
            , Sheet428PersonalPDM sheet428PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellStyleDate = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellStyleDate, font);

        ExcelUtils.setDefaultDateCellStyle(cellStyleDate, wb);
        Cell cellDate = ExcelUtils.getCell(row, ExcelConfig.Sheet428TimeCellIndex);
        cellDate.setCellStyle(cellStyleDate);
        cellDate.setCellValue(sheet428PDM.getDate());

        Cell cellOrder = ExcelUtils.getCell(row, ExcelConfig.Sheet428OrderCellIndex);
        cellOrder.setCellStyle(cellStyle);
        cellOrder.setCellValue(sheet428PDM.getOrder());

        Cell cellBatch = ExcelUtils.getCell(row, ExcelConfig.Sheet428BatchCellIndex);
        cellBatch.setCellStyle(cellStyle);
        cellBatch.setCellValue(sheet428PDM.getBatch());

        Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet428ProvinceCellIndex);
        cellProvince.setCellStyle(cellStyle);
        cellProvince.setCellValue(sheet428PDM.getProvince());
    }
}
