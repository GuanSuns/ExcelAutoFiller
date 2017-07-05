package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.ExcelType;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet426CorePDM;
import excel.filler.model.Sheet426PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static excel.filler.utils.ExcelUtils.setDefaultDateCellStyle;

/**
 * Created by guanl on 7/4/2017.
 */
public class Sheet426Filler {
    
    private static int iconID;

    public static void fill(Sheet426PersonalPDM sheet426PDM
            , FileDestination destination) throws Exception{
        if(sheet426PDM == null || destination == null){
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
        Sheet sheet426 = wb.getSheet(ExcelConfig.SheetName426);
        if(sheet426 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName426);
        }

        insert(sheet426, sheet426PDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet426
            , Sheet426PersonalPDM sheet426PDM
            , FileDestination destination) throws Exception{

        //Get the row index for the new data
        int index = ExcelUtils.getLastRow(sheet426
                , ExcelConfig.Sheet426RecordStartRow
                , ExcelConfig.Sheet426TimeCellIndex) + 1;

        Row row = ExcelUtils.getRow(sheet426, index);

        //Handle missing values
        if(sheet426PDM.getProvince() == null
                || sheet426PDM.getProvince().equals("")){
            sheet426PDM.setProvince(ExcelConfig.Sheet426DefaultProvince);
        }
        if(sheet426PDM.getDate() == null){
            Timestamp currentTime = new Timestamp(new Date().getTime());
            sheet426PDM.setDate(currentTime);
        }
        sheet426PDM.setOrder1((long)(index - ExcelConfig.Sheet426RecordStartRow + 1));
        //Write inspection time, order and province
        setBasicProperties(row, sheet426PDM, destination);
        sheet426.autoSizeColumn(ExcelConfig.Sheet426TimeCellIndex);

        Workbook wb = row.getSheet().getWorkbook();               

        ExcelType excelType;
        if(sheet426 instanceof XSSFSheet){
            excelType = ExcelType.Xlsx;
            iconID = loadIcon(wb, ExcelType.Xlsx);
            insertLog2(sheet426PDM, sheet426, ExcelType.Xlsx, index, row);
        }else{
            excelType = ExcelType.Xls;
            iconID = loadIcon(wb, ExcelType.Xls);
            insertLog2(sheet426PDM, sheet426, ExcelType.Xls, index, row);
        }        

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet426PersonalBlankStart
                    , ExcelConfig.Sheet426PersonalBlankEnd);
        }else{
            insertLog3(sheet426PDM, sheet426, excelType, index, row);
            insertLog4(sheet426PDM, sheet426, excelType, index, row);
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet426CoreBlankStart
                    , ExcelConfig.Sheet426CoreBlankEnd);
        }
    }

    private static void insertLog2(Sheet426PersonalPDM sheet426PDM
            , Sheet sheet, ExcelType excelType, int rowIndex, Row row) throws Exception{

        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);

        Cell cellORA = ExcelUtils.getCell(row, ExcelConfig.Sheet426Start);
        cellORA.setCellStyle(cellStyle);
        cellORA.setCellValue(sheet426PDM.getError2());

        Cell cellLog = ExcelUtils.getCell(row, ExcelConfig.Sheet426Start+1);
        cellLog.setCellStyle(cellStyle);
        
        String log20 = sheet426PDM.getLog20();
        String log21 = sheet426PDM.getLog21();
        Date currentTime = new Date();

        int oleId20 = sheet.getWorkbook().addOlePackage(log20.getBytes()
                , currentTime.getTime()+ "_host20.log"
                , currentTime.getTime()+"_host20.log"
                , currentTime.getTime()+"_host20.log");

        int oleId21 = sheet.getWorkbook().addOlePackage(log21.getBytes()
                , currentTime.getTime()+"_host21.log"
                , currentTime.getTime()+"_host21.log"
                , currentTime.getTime()+"_host21.log");

        row.setHeightInPoints(ExcelConfig.Sheet426LogCellHeight);
        sheet.setColumnWidth((ExcelConfig.Sheet426Start+1)
                , (short)(ExcelConfig.Sheet426LogCellWidth));

        if(excelType.equals(ExcelType.Xlsx)){
            XSSFSheet sheetXSSF = (XSSFSheet)sheet;

            XSSFClientAnchor imgAnchor20 = new XSSFClientAnchor(ExcelConfig.Sheet426Pic20X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic20X2
                    , ExcelConfig.Sheet426PicY2
                    , ExcelConfig.Sheet426Start+1, rowIndex, ExcelConfig.Sheet426Start+1
                    , rowIndex);
            XSSFClientAnchor imgAnchor21 = new XSSFClientAnchor(ExcelConfig.Sheet426Pic21X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic21X2
                    , ExcelConfig.Sheet426PicY2
                    , ExcelConfig.Sheet426Start+1, rowIndex, ExcelConfig.Sheet426Start+1
                    , rowIndex);

            XSSFDrawing patriarch = sheetXSSF.createDrawingPatriarch();
            patriarch.createObjectData(imgAnchor20, oleId20, iconID);
            patriarch.createObjectData(imgAnchor21, oleId21, iconID);
        }else{
            HSSFSheet sheetHSSF = (HSSFSheet)sheet;

            HSSFClientAnchor imgAnchor20 = new HSSFClientAnchor(ExcelConfig.Sheet426Pic20X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic20X2
                    , ExcelConfig.Sheet426PicY2
                    , (short)(ExcelConfig.Sheet426Start+1), rowIndex
                    , (short)(ExcelConfig.Sheet426Start+1)
                    , rowIndex);
            HSSFClientAnchor imgAnchor21 = new HSSFClientAnchor(ExcelConfig.Sheet426Pic21X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic21X2
                    , ExcelConfig.Sheet426PicY2
                    , (short)(ExcelConfig.Sheet426Start+1), rowIndex
                    , (short)(ExcelConfig.Sheet426Start+1)
                    , rowIndex);

            HSSFPatriarch patriarch = sheetHSSF.createDrawingPatriarch();
            patriarch.createObjectData(imgAnchor20, oleId20, iconID);
            patriarch.createObjectData(imgAnchor21, oleId21, iconID);
        }

    }

    private static void insertLog3(Sheet426PersonalPDM sheet426PDM
            , Sheet sheet, ExcelType excelType, int rowIndex, Row row) throws Exception{

        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);

        Sheet426CorePDM sheet426CorePDM = (Sheet426CorePDM)sheet426PDM;

        Cell cellORA = ExcelUtils.getCell(row, ExcelConfig.Sheet426Start + 2);
        cellORA.setCellStyle(cellStyle);
        cellORA.setCellValue(sheet426CorePDM.getError3());

        Cell cellLog = ExcelUtils.getCell(row, ExcelConfig.Sheet426Start + 3);
        cellLog.setCellStyle(cellStyle);

        String log3 = sheet426CorePDM.getLog3();
        Date currentTime = new Date();

        int oleId3 = sheet.getWorkbook().addOlePackage(log3.getBytes()
                , currentTime.getTime()+ "_host3.log"
                , currentTime.getTime()+"_host3.log"
                , currentTime.getTime()+"_host3.log");

        row.setHeightInPoints(ExcelConfig.Sheet426LogCellHeight);
        sheet.setColumnWidth((ExcelConfig.Sheet426Start+3)
                , (short)(ExcelConfig.Sheet426LogCellWidth));

        if(excelType.equals(ExcelType.Xlsx)){
            XSSFSheet sheetXSSF = (XSSFSheet)sheet;

            XSSFClientAnchor imgAnchor3 = new XSSFClientAnchor(ExcelConfig.Sheet426Pic1X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic1X2
                    , ExcelConfig.Sheet426PicY2
                    , ExcelConfig.Sheet426Start+3, rowIndex, ExcelConfig.Sheet426Start+3
                    , rowIndex);            

            XSSFDrawing patriarch = sheetXSSF.createDrawingPatriarch();
            patriarch.createObjectData(imgAnchor3, oleId3, iconID);
        }else{
            HSSFSheet sheetHSSF = (HSSFSheet)sheet;

            HSSFClientAnchor imgAnchor3 = new HSSFClientAnchor(ExcelConfig.Sheet426Pic1X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic1X2
                    , ExcelConfig.Sheet426PicY2
                    , (short)(ExcelConfig.Sheet426Start+3), rowIndex
                    , (short)(ExcelConfig.Sheet426Start+3)
                    , rowIndex);

            HSSFPatriarch patriarch = sheetHSSF.createDrawingPatriarch();
            patriarch.createObjectData(imgAnchor3, oleId3, iconID);
        }

    }

    private static void insertLog4(Sheet426PersonalPDM sheet426PDM
            , Sheet sheet, ExcelType excelType, int rowIndex, Row row) throws Exception{

        Sheet426CorePDM sheet426CorePDM = (Sheet426CorePDM)sheet426PDM;

        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);

        Cell cellORA = ExcelUtils.getCell(row, ExcelConfig.Sheet426Start + 4);
        cellORA.setCellStyle(cellStyle);
        cellORA.setCellValue(sheet426CorePDM.getError4());

        Cell cellLog = ExcelUtils.getCell(row, ExcelConfig.Sheet426Start + 5);
        cellLog.setCellStyle(cellStyle);

        String log40 = sheet426CorePDM.getLog40();
        String log41 = sheet426CorePDM.getLog41();
        Date currentTime = new Date();

        int oleId20 = sheet.getWorkbook().addOlePackage(log40.getBytes()
                , currentTime.getTime()+ "_host40.log"
                , currentTime.getTime()+"_host40.log"
                , currentTime.getTime()+"_host40.log");

        int oleId21 = sheet.getWorkbook().addOlePackage(log41.getBytes()
                , currentTime.getTime()+"_host41.log"
                , currentTime.getTime()+"_host41.log"
                , currentTime.getTime()+"_host41.log");

        row.setHeightInPoints(ExcelConfig.Sheet426LogCellHeight);
        sheet.setColumnWidth((ExcelConfig.Sheet426Start+5)
                , (short)(ExcelConfig.Sheet426LogCellWidth));

        if(excelType.equals(ExcelType.Xlsx)){
            XSSFSheet sheetXSSF = (XSSFSheet)sheet;

            XSSFClientAnchor imgAnchor20 = new XSSFClientAnchor(ExcelConfig.Sheet426Pic20X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic20X2
                    , ExcelConfig.Sheet426PicY2
                    , ExcelConfig.Sheet426Start+5, rowIndex, ExcelConfig.Sheet426Start+5
                    , rowIndex);
            XSSFClientAnchor imgAnchor21 = new XSSFClientAnchor(ExcelConfig.Sheet426Pic21X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic21X2
                    , ExcelConfig.Sheet426PicY2
                    , ExcelConfig.Sheet426Start+5, rowIndex, ExcelConfig.Sheet426Start+5
                    , rowIndex);

            XSSFDrawing patriarch = sheetXSSF.createDrawingPatriarch();
            patriarch.createObjectData(imgAnchor20, oleId20, iconID);
            patriarch.createObjectData(imgAnchor21, oleId21, iconID);
        }else{
            HSSFSheet sheetHSSF = (HSSFSheet)sheet;

            HSSFClientAnchor imgAnchor20 = new HSSFClientAnchor(ExcelConfig.Sheet426Pic20X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic20X2
                    , ExcelConfig.Sheet426PicY2
                    , (short)(ExcelConfig.Sheet426Start+5), rowIndex
                    , (short)(ExcelConfig.Sheet426Start+5)
                    , rowIndex);
            HSSFClientAnchor imgAnchor21 = new HSSFClientAnchor(ExcelConfig.Sheet426Pic21X1
                    , ExcelConfig.Sheet426PicY1, ExcelConfig.Sheet426Pic21X2
                    , ExcelConfig.Sheet426PicY2
                    , (short)(ExcelConfig.Sheet426Start+5), rowIndex
                    , (short)(ExcelConfig.Sheet426Start+5)
                    , rowIndex);

            HSSFPatriarch patriarch = sheetHSSF.createDrawingPatriarch();
            patriarch.createObjectData(imgAnchor20, oleId20, iconID);
            patriarch.createObjectData(imgAnchor21, oleId21, iconID);
        }

    }

    //Write inspection time, order and province
    private static void setBasicProperties(Row row
            , Sheet426PersonalPDM sheet426PDM
            , FileDestination destination) throws Exception {
        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellDateStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellDateStyle, font);

        Cell cellDate = ExcelUtils.getCell(row, ExcelConfig.Sheet426TimeCellIndex);
        setDefaultDateCellStyle(cellDateStyle, wb);
        cellDate.setCellStyle(cellDateStyle);
        cellDate.setCellValue(sheet426PDM.getDate());

        Cell cellOrder = ExcelUtils.getCell(row, ExcelConfig.Sheet426OrderCellIndex);
        cellOrder.setCellStyle(cellStyle);
        cellOrder.setCellValue(sheet426PDM.getOrder1());

        Cell cellBatch = ExcelUtils.getCell(row, ExcelConfig.Sheet426BatchCellIndex);
        cellBatch.setCellStyle(cellStyle);
        cellBatch.setCellValue(sheet426PDM.getBatch());

        Cell cellProvince = ExcelUtils.getCell(row, ExcelConfig.Sheet426ProvinceCellIndex);
        cellProvince.setCellStyle(cellStyle);
        cellProvince.setCellValue(sheet426PDM.getProvince());
    }

    private static int loadIcon(Workbook wb, ExcelType excelType) throws Exception{
        URL url = Sheet426Filler.class.getResource(ExcelConfig.Sheet426LogIcon);
        File pic = new File(url.toURI());
        FileInputStream fis = new FileInputStream(pic);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        
        fis.close();
        bos.close();

        byte[] picByte = bos.toByteArray();

        if(excelType.equals(ExcelType.Xls)){
            HSSFWorkbook wbHSSF = (HSSFWorkbook)wb;
            List<HSSFPictureData> pictureData = wbHSSF.getAllPictures();

            for(int i=0; i<pictureData.size(); i++){
                if(isBytesEqual(picByte, pictureData.get(i).getData())){
                    //System.out.println("Found Equal Picture, ID " + (i+1));
                    return i+1;
                }
            }
        }else{
            XSSFWorkbook wbHSSF = (XSSFWorkbook)wb;
            List<XSSFPictureData> pictureData = wbHSSF.getAllPictures();

            for(int i=0; i<pictureData.size(); i++){
                if(isBytesEqual(picByte, pictureData.get(i).getData())){
                    //System.out.println("Found Equal Picture, ID " + (i+1));
                    return i+1;
                }
            }
        }

        return wb.addPicture(picByte, Workbook.PICTURE_TYPE_PNG);
    }

    private static boolean isBytesEqual(byte[] b0, byte[] b1){
        if(b0 == null || b1 == null){
            return false;
        }

        if(b0.length != b1.length){
            return false;
        }else{
            for(int i=0; i< b0.length; i++){
                if(b0[i] != b1[i]){
                    return false;
                }
            }
        }

        return true;
    }

}
