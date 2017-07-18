package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet421CorePDM;
import excel.filler.model.Sheet421PersonalPDM;
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
 * Created by guanl on 6/21/2017.
 */
public class Sheet421Filler {
    public static void fill(Sheet421PersonalPDM sheet421PDM
            , FileDestination destination) throws Exception{

        if(sheet421PDM == null || destination == null){
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
        Sheet sheet421 = wb.getSheet(ExcelConfig.SheetName421);
        if(sheet421 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName421);
        }

        insert(sheet421, sheet421PDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet421
            , Sheet421PersonalPDM sheet421PDM
            , FileDestination destination) throws Exception{

        //Get the row index for the new data
        int index = ExcelUtils.getLastRow(sheet421
                , ExcelConfig.Sheet421RecordStartRow
                , ExcelConfig.Sheet421TimeCellIndex) + 1;

        Row row = ExcelUtils.getRow(sheet421, index);

        //Handle missing values
        if(sheet421PDM.getProvince() == null
                || sheet421PDM.getProvince().equals("")){
            sheet421PDM.setProvince(ExcelConfig.Sheet421DefaultProvince);
        }
        if(sheet421PDM.getDate() == null){
            Timestamp currentTime = new Timestamp(new Date().getTime());
            sheet421PDM.setDate(currentTime);
        }
        
        if(destination.equals(FileDestination.Core)){
            Sheet421CorePDM sheet421CorePDM = (Sheet421CorePDM)sheet421PDM;
            if(sheet421CorePDM.getBatchID() == null){
                sheet421CorePDM.setBatchID("");
            }
        }
        
        sheet421PDM.setOrder1((long)(index - ExcelConfig.Sheet421RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet421PDM, destination);

        //Pour numeric data into an ArrayList
        ArrayList<Float> data = new ArrayList<>();
        data.add(sheet421PDM.getUsage2());
        data.add(sheet421PDM.getU01Usage2());
        data.add(sheet421PDM.getGoldUsage2());
        data.add(sheet421PDM.getUsage3());
        data.add(sheet421PDM.getU01Usage3());
        data.add(sheet421PDM.getGoldUsage3());

        if(destination.equals(FileDestination.Core)){
            Sheet421CorePDM sheet421CorePDM = (Sheet421CorePDM)sheet421PDM;
            data.add(sheet421CorePDM.getUsage4());
            data.add(sheet421CorePDM.getU01Usage4());
            data.add(sheet421CorePDM.getGoldUsage4());
            data.add(sheet421CorePDM.getUsage5());
            data.add(sheet421CorePDM.getU01Usage5());
            data.add(sheet421CorePDM.getGoldUsage5());
        }

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithPercentage(row, ExcelConfig.Sheet421PersonalStart
                    , ExcelConfig.Sheet421PersonalEnd, data, true);
        }else{
            ExcelUtils.fillRowWithPercentage(row, ExcelConfig.Sheet421CoreStart
                    , ExcelConfig.Sheet421CoreEnd, data, true);
        }
    }

    //Write inspection time, order, batch ID and province
    //Also fill in blank cells
    private static void setBasicProperties(Row row
            , Sheet421PersonalPDM sheet421PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellDateStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellDateStyle, font);

        Cell cellDate = ExcelUtils.getCell(row, ExcelConfig.Sheet421TimeCellIndex);
        setDefaultDateCellStyle(cellDateStyle, wb);
        cellDate.setCellStyle(cellDateStyle);
        cellDate.setCellValue(sheet421PDM.getDate());
        row.getSheet().autoSizeColumn(ExcelConfig.Sheet421TimeCellIndex);

        Cell cellOrder = ExcelUtils.getCell(row, ExcelConfig.Sheet421Order1CellIndex);
        cellOrder.setCellStyle(cellStyle);
        cellOrder.setCellValue(sheet421PDM.getOrder1());
        

        if(destination.equals(FileDestination.Core)){
            Sheet421CorePDM sheet421CorePDM = (Sheet421CorePDM)sheet421PDM;
            Cell cellBatch = ExcelUtils.getCell(row, ExcelConfig.Sheet421CoreBatchCellIndex);
            cellBatch.setCellStyle(cellStyle);
            cellBatch.setCellValue(sheet421CorePDM.getBatchID());
            Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet421CoreProvinceCellIndex);
            cellProvince.setCellStyle(cellStyle);
            cellProvince.setCellValue(sheet421CorePDM.getProvince());
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet421CoreBlankStart
                    , ExcelConfig.Sheet421CoreBlankEnd);
        }else{
            Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet421PersonalProvinceCellIndex);
            cellProvince.setCellStyle(cellStyle);
            cellProvince.setCellValue(sheet421PDM.getProvince());
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet421PersonalBlankStart
                    , ExcelConfig.Sheet421PersonalBlankEnd);
        }
    }
}
