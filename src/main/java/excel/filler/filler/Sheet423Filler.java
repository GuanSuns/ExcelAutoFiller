package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet423CorePDM;
import excel.filler.model.Sheet423PersonalPDM;
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
public class Sheet423Filler {
    public static void fill(Sheet423PersonalPDM sheet423PDM
            , FileDestination destination) throws Exception{

        if(sheet423PDM == null || destination == null){
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
        Sheet sheet423 = wb.getSheet(ExcelConfig.SheetName423);
        if(sheet423 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName423);
        }

        insert(sheet423, sheet423PDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet423
            , Sheet423PersonalPDM sheet423PDM
            , FileDestination destination) throws Exception{
        //Get the row index for the new data
        int index = ExcelUtils.getLastRow(sheet423
                , ExcelConfig.Sheet423RecordStartRow
                , ExcelConfig.Sheet423TimeCellIndex) + 1;

        Row row = ExcelUtils.getRow(sheet423, index);

        //Handle missing values
        if(sheet423PDM.getProvince() == null
                || sheet423PDM.getProvince().equals("")){
            sheet423PDM.setProvince(ExcelConfig.Sheet423DefaultProvince);
        }
        if(sheet423PDM.getBatchID() == null){
            sheet423PDM.setBatchID("");
        }
        if(sheet423PDM.getDate() == null){
            sheet423PDM.setDate(new Timestamp(new Date().getTime()));
        }

        sheet423PDM.setOrder1((long)(index - ExcelConfig.Sheet423RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet423PDM, destination);

        insertNumeric(row, sheet423PDM, destination);
        insertASMName(row, sheet423PDM, destination);

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet423PersonalBlankStart
                    , ExcelConfig.Sheet423PersonalBlankEnd);
        }else{
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet423CoreBlankStart
                    , ExcelConfig.Sheet423CoreBlankEnd);
        }
    }

    //Insert sequence of numerical data
    private static void insertNumeric(Row row
            , Sheet423PersonalPDM sheet423PDM
            , FileDestination destination) throws Exception{

        //Pour numeric data into an ArrayList
        ArrayList<Float> data = new ArrayList<>();
        data.add(0F);
        data.add(new Float(sheet423PDM.getTotalSpace2()));
        data.add(new Float(sheet423PDM.getRemainSpace2()));
        data.add(sheet423PDM.getUsage2());

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithFloat(row, ExcelConfig.Sheet423PersonalStart
                    , ExcelConfig.Sheet423PersonalEnd, data);
        }else{
            Sheet423CorePDM sheet423CorePDM = (Sheet423CorePDM)sheet423PDM;
            data.add(0F);
            data.add(new Float(sheet423PDM.getTotalSpace3()));
            data.add(new Float(sheet423PDM.getRemainSpace3()));
            data.add(sheet423PDM.getUsage3());
            data.add(0F);
            data.add(new Float(sheet423CorePDM.getTotalSpace4()));
            data.add(new Float(sheet423CorePDM.getRemainSpace4()));
            data.add(sheet423CorePDM.getUsage4());

            ExcelUtils.fillRowWithFloat(row, ExcelConfig.Sheet423CoreStart
                    , ExcelConfig.Sheet423CoreEnd, data);
        }
    }

    //Insert ASM Disk Name
    private static void insertASMName(Row row, Sheet423PersonalPDM sheet423PDM
            , FileDestination destination) throws Exception{
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);

        Cell cellASM2 = ExcelUtils.getCell(row, ExcelConfig.Sheet423PersonalStart);
        cellASM2.setCellStyle(cellStyle);
        cellASM2.setCellValue(sheet423PDM.getAsmName2());

        if(destination.equals(FileDestination.Core)){
            Sheet423CorePDM sheet423CorePDM = (Sheet423CorePDM)sheet423PDM;

            Cell cellASM3 = ExcelUtils.getCell(row, ExcelConfig.Sheet423PersonalStart
                    + ExcelConfig.Sheet423DataDistance);
            cellASM3.setCellStyle(cellStyle);
            cellASM3.setCellValue(sheet423PDM.getAsmName3());

            Cell cellASM4 = ExcelUtils.getCell(row, ExcelConfig.Sheet423PersonalStart
                    + 2*ExcelConfig.Sheet423DataDistance);
            cellASM4.setCellStyle(cellStyle);
            cellASM4.setCellValue(sheet423CorePDM.getAsmName4());
        }
    }

    //Write inspection time, order, batch ID and province
    private static void setBasicProperties(Row row
            , Sheet423PersonalPDM sheet423PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellDateStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellDateStyle, font);

        Cell cellOrder = ExcelUtils.getCell(row, ExcelConfig.Sheet423OrderCellIndex);
        cellOrder.setCellStyle(cellStyle);
        cellOrder.setCellValue(sheet423PDM.getOrder1());

        Cell cellBatch = ExcelUtils.getCell(row, ExcelConfig.Sheet423BatchCellIndex);
        cellBatch.setCellStyle(cellStyle);
        cellBatch.setCellValue(sheet423PDM.getBatchID());

        Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet423ProvinceCellIndex);
        cellProvince.setCellStyle(cellStyle);
        cellProvince.setCellValue(sheet423PDM.getProvince());

        Cell cellDate = ExcelUtils.getCell(row, ExcelConfig.Sheet423TimeCellIndex);
        setDefaultDateCellStyle(cellDateStyle, wb);
        cellDate.setCellStyle(cellDateStyle);
        cellDate.setCellValue(sheet423PDM.getDate());

    }
}
