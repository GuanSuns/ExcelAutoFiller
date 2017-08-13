package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet411CorePDM;
import excel.filler.model.Sheet411PersonalPDM;
import org.apache.poi.ss.usermodel.*;
import excel.filler.utils.ExcelUtils;
import org.suns.database.utils.config.DBConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static excel.filler.utils.ExcelUtils.setDefaultDateCellStyle;

/**
 * Created by guanl on 6/19/2017.
 */
public class Sheet411Filler {
    public static void fill(Sheet411PersonalPDM sheet411PDM
            , FileDestination destination) throws Exception{
        if(sheet411PDM == null || destination == null){
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
        Sheet sheet411 = wb.getSheet(ExcelConfig.SheetName411);
        if(sheet411 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName411);
        }

        insert(sheet411, sheet411PDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet411
            , Sheet411PersonalPDM sheet411PDM
            , FileDestination destination) throws Exception{

        //Get the row index for the new data
        int index = ExcelUtils.getLastRow(sheet411
                , ExcelConfig.Sheet411RecordStartRow
                , ExcelConfig.Sheet411TimeCellIndex) + 1;

        Row row = ExcelUtils.getRow(sheet411, index);

        //Handle missing values
        if(sheet411PDM.getProvince() == null
                || sheet411PDM.getProvince().equals("")){
            sheet411PDM.setProvince(ExcelConfig.Sheet411DefaultProvince);
        }
        if(sheet411PDM.getDate() == null){
            Timestamp currentTime = new Timestamp(new Date().getTime());
            sheet411PDM.setDate(currentTime);
        }
        sheet411PDM.setOrder1((long)(index - ExcelConfig.Sheet411RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet411PDM, destination);

        //Pour numeric data into an ArrayList
        ArrayList<Float> data = new ArrayList<>();
        data.add(sheet411PDM.getUsage2());
        data.add(sheet411PDM.getWeblogicUsage2());
        data.add(sheet411PDM.getUsage3());
        data.add(sheet411PDM.getWeblogicUsage3());
        data.add(sheet411PDM.getUsage4());
        data.add(sheet411PDM.getWeblogicUsage4());
        data.add(sheet411PDM.getUsage5());
        data.add(sheet411PDM.getWeblogicUsage5());
        data.add(sheet411PDM.getUsage6());
        data.add(sheet411PDM.getWeblogicUsage6());
        data.add((float)DBConfig.getDefaultNumericNullValue());
        data.add((float)DBConfig.getDefaultNumericNullValue());

        if(destination.equals(FileDestination.Core)){
            data.add(((Sheet411CorePDM)sheet411PDM).getUsage8());
            data.add(((Sheet411CorePDM)sheet411PDM).getWeblogicUsage8());
        }

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithPercentage(row, ExcelConfig.Sheet411PersonalStart
                    , ExcelConfig.Sheet411PersonalEnd, data, true);
        }else{
            ExcelUtils.fillRowWithPercentage(row, ExcelConfig.Sheet411CoreStart
                    , ExcelConfig.Sheet411CoreEnd, data, true);
        }
    }

    //Write inspection time, order and province
    private static void setBasicProperties(Row row
            , Sheet411PersonalPDM sheet411PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellDateStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellDateStyle, font);

        Cell cellDate = ExcelUtils.getCell(row, ExcelConfig.Sheet411TimeCellIndex);
        setDefaultDateCellStyle(cellDateStyle, wb);
        cellDate.setCellStyle(cellDateStyle);
        cellDate.setCellValue(sheet411PDM.getDate());
        row.getSheet().autoSizeColumn(ExcelConfig.Sheet411TimeCellIndex);

        Cell cellOrder = ExcelUtils.getCell(row, ExcelConfig.Sheet411Order1CellIndex);
        cellOrder.setCellStyle(cellStyle);
        cellOrder.setCellValue(sheet411PDM.getOrder1());

        Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet411ProvinceCellIndex);
        cellProvince.setCellStyle(cellStyle);
        cellProvince.setCellValue(sheet411PDM.getProvince());

        if(destination.equals(FileDestination.Personal)){
            Cell cellRemark = ExcelUtils.getCell(row, ExcelConfig.Sheet411PersonalRemarkIndex);
            cellRemark.setCellStyle(cellStyle);
            cellRemark.setCellValue(" ");
        }else{
            Cell cellRemark = ExcelUtils.getCell(row, ExcelConfig.Sheet411CoreRemarkIndex);
            cellRemark.setCellStyle(cellStyle);
            cellRemark.setCellValue(" ");
        }

    }
}
