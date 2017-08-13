package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet429CorePDM;
import excel.filler.model.Sheet429PersonalPDM;
import org.apache.poi.ss.usermodel.*;
import excel.filler.utils.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by guanl on 6/23/2017.
 */
public class Sheet429Filler {
    public static void fill(Sheet429PersonalPDM sheet429PDM
            , FileDestination destination) throws Exception{

        if(sheet429PDM == null || destination == null){
            throw new Exception("Invalid null arguments");
        }

        File destFile = null;
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
        Sheet sheet429 = wb.getSheet(ExcelConfig.SheetName429);
        if(sheet429 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName429);
        }

        insert(sheet429, sheet429PDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet429
            , Sheet429PersonalPDM sheet429PDM
            , FileDestination destination) throws Exception{
        //Get the row index for the new data
        int index = ExcelUtils.getLastRow(sheet429
                , ExcelConfig.Sheet429RecordStartRow
                , ExcelConfig.Sheet429TimeCellIndex) + 1;

        Row row = ExcelUtils.getRow(sheet429, index);

        //Handle missing values
        if(sheet429PDM.getProvince() == null
                || sheet429PDM.getProvince().equals("")){
            sheet429PDM.setProvince(ExcelConfig.Sheet429DefaultProvince);
        }
        if(sheet429PDM.getBatch() == null){
            sheet429PDM.setBatch("");
        }

        sheet429PDM.setOrder((long)(index - ExcelConfig.Sheet429RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet429PDM, destination);

        insertRecord(row, sheet429PDM, destination);

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet429PersonalBlankStart
                    , ExcelConfig.Sheet429PersonalBlankEnd);
        }else{
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet429CoreBlankStart
                    , ExcelConfig.Sheet429CoreBlankEnd);
        }
    }

    private static void insertRecord(Row row
            , Sheet429PersonalPDM sheet429PDM
            , FileDestination destination) throws Exception{

        ArrayList<String> strSQLResults = new ArrayList<>();

        if(destination.equals(FileDestination.Core)){
            Sheet429CorePDM sheet429CorePDM = (Sheet429CorePDM)sheet429PDM;

            strSQLResults.add(sheet429CorePDM.getHeartBeat1());
            strSQLResults.add(sheet429CorePDM.getHeartBeat2());
            strSQLResults.add(sheet429CorePDM.getHeartBeat3());

            ExcelUtils.fillRowWithString(row, ExcelConfig.Sheet429Start
                    , ExcelConfig.Sheet429CoreBlankStart, strSQLResults);
        }else{
            strSQLResults.add(sheet429PDM.getHeartBeat1());

            ExcelUtils.fillRowWithString(row, ExcelConfig.Sheet429Start
                    , ExcelConfig.Sheet429PersonalBlankStart, strSQLResults);
        }


    }

    //Write inspection time, order, batch ID and province
    private static void setBasicProperties(Row row
            , Sheet429PersonalPDM sheet429PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellStyleDate = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellStyleDate, font);

        ExcelUtils.setDefaultDateCellStyle(cellStyleDate, wb);
        Cell cellDate = ExcelUtils.getCell(row, ExcelConfig.Sheet429TimeCellIndex);
        cellDate.setCellStyle(cellStyleDate);
        cellDate.setCellValue(sheet429PDM.getDate());
        row.getSheet().autoSizeColumn(ExcelConfig.Sheet429TimeCellIndex);

        Cell cellOrder = ExcelUtils.getCell(row, ExcelConfig.Sheet429OrderCellIndex);
        cellOrder.setCellStyle(cellStyle);
        cellOrder.setCellValue(sheet429PDM.getOrder());

        Cell cellBatch = ExcelUtils.getCell(row, ExcelConfig.Sheet429BatchCellIndex);
        cellBatch.setCellStyle(cellStyle);
        cellBatch.setCellValue(sheet429PDM.getBatch());

        Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet429ProvinceCellIndex);
        cellProvince.setCellStyle(cellStyle);
        cellProvince.setCellValue(sheet429PDM.getProvince());
    }
}
