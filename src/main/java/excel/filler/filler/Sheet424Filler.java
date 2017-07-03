package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet424CorePDM;
import excel.filler.model.Sheet424PersonalPDM;
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
public class Sheet424Filler {
    //Use this class to write data
    private static class TimeStatusPair{
        public Timestamp collectTime;
        public String status;

        public TimeStatusPair(Timestamp collectTime, String status) {
            this.collectTime = collectTime;
            this.status = status;
        }
    }

    public static void fill(Sheet424PersonalPDM sheet424PDM
            , FileDestination destination) throws Exception{

        if(sheet424PDM == null || destination == null){
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
        Sheet sheet424 = wb.getSheet(ExcelConfig.SheetName424);
        if(sheet424 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName424);
        }

        insert(sheet424, sheet424PDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    private static void insertTimeStatusPair(Row row
            , ArrayList<TimeStatusPair> data) throws Exception{
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellDateStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellDateStyle, font);

        ExcelUtils.setDefaultDateCellStyle(cellDateStyle, wb);

        for(int i = 0; i < data.size(); i++){
            Cell cellTime = ExcelUtils.getCell(row
                    , ExcelConfig.Sheet424Start + ExcelConfig.Sheet424DataDistance*i);
            Timestamp time = data.get(i).collectTime;
            if(time == null){
                time = new Timestamp(new Date().getTime());
            }
            cellTime.setCellStyle(cellDateStyle);
            cellTime.setCellValue(time);

            Cell cellStatus = ExcelUtils.getCell(row
                    , ExcelConfig.Sheet424Start + i*ExcelConfig.Sheet424DataDistance + 1);
            cellStatus.setCellStyle(cellStyle);
            cellStatus.setCellValue(data.get(i).status);
        }
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet424
            , Sheet424PersonalPDM sheet424PDM
            , FileDestination destination) throws Exception{
        //Get the row index for the new data
        int index = ExcelUtils.getLastRow(sheet424
                , ExcelConfig.Sheet424RecordStartRow
                , ExcelConfig.Sheet424TimeCellIndex) + 1;

        Row row = ExcelUtils.getRow(sheet424, index);

        //Handle missing values
        if(sheet424PDM.getProvince() == null
                || sheet424PDM.getProvince().equals("")){
            sheet424PDM.setProvince(ExcelConfig.Sheet424DefaultProvince);
        }
        if(sheet424PDM.getBatch() == null){
            sheet424PDM.setBatch("");
        }
        if(sheet424PDM.getDate() == null){
            sheet424PDM.setDate(new Timestamp(new Date().getTime()));
        }

        sheet424PDM.setOrder1((long)(index - ExcelConfig.Sheet424RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet424PDM, destination);

        //Pour numeric data into an ArrayList
        ArrayList<TimeStatusPair> data = new ArrayList<>();
        data.add(new TimeStatusPair(sheet424PDM.getCollectTime2()
                , sheet424PDM.getTempStatus2()));

        if(destination.equals(FileDestination.Core)){
            data.add(new TimeStatusPair(sheet424PDM.getCollectTime3()
                    , sheet424PDM.getTempStatus3()));

            Sheet424CorePDM sheet424CorePDM = (Sheet424CorePDM)sheet424PDM;
            data.add(new TimeStatusPair(sheet424CorePDM.getCollectTime4()
                    , sheet424CorePDM.getTempStatus4()));
        }

        //Insert time and status pairs
        insertTimeStatusPair(row, data);

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet424PersonalBlankStart
                    , ExcelConfig.Sheet424PersonalBlankEnd);
        }else{
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet424CoreBlankStart
                    , ExcelConfig.Sheet424CoreBlankEnd);
        }
    }

    //Write inspection time, order, batch ID and province
    private static void setBasicProperties(Row row
            , Sheet424PersonalPDM sheet424PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellDateStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellDateStyle, font);

        Cell cellOrder = ExcelUtils.getCell(row, ExcelConfig.Sheet424OrderCellIndex);
        cellOrder.setCellStyle(cellStyle);
        cellOrder.setCellValue(sheet424PDM.getOrder1());

        Cell cellBatch = ExcelUtils.getCell(row, ExcelConfig.Sheet424BatchCellIndex);
        cellBatch.setCellStyle(cellStyle);
        cellBatch.setCellValue(sheet424PDM.getBatch());

        Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet424ProvinceCellIndex);
        cellProvince.setCellStyle(cellStyle);
        cellProvince.setCellValue(sheet424PDM.getProvince());

        Cell cellDate = ExcelUtils.getCell(row, ExcelConfig.Sheet424TimeCellIndex);
        setDefaultDateCellStyle(cellDateStyle, wb);
        cellDate.setCellStyle(cellDateStyle);
        cellDate.setCellValue(sheet424PDM.getDate());
    }
}
