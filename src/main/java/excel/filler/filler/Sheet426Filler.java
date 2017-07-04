package excel.filler.filler;

import excel.filler.config.ExcelConfig;
import excel.filler.config.ExcelType;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.Sheet426PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static excel.filler.utils.ExcelUtils.setDefaultDateCellStyle;

/**
 * Created by guanl on 7/4/2017.
 */
public class Sheet426Filler {
    
    private static boolean hasLoadIcon = false;
    private static int iconID = 0;

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

        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);

        Cell cellORA = ExcelUtils.getCell(row, ExcelConfig.Sheet426Start);
        cellORA.setCellStyle(cellStyle);
        cellORA.setCellValue(sheet426PDM.getError2());

        if(!hasLoadIcon){
            iconID = loadIcon(wb);
        }
        insertLog2(sheet426PDM, sheet426, ExcelType.Xlsx, index);

        if(destination.equals(FileDestination.Personal)){
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet426PersonalBlankStart
                    , ExcelConfig.Sheet426PersonalBlankEnd);
        }else{
            ExcelUtils.fillRowWithBlank(row, ExcelConfig.Sheet426CoreBlankStart
                    , ExcelConfig.Sheet426CoreBlankEnd);
        }
    }

    private static void insertLog2(Sheet426PersonalPDM sheet426PDM
            , Sheet sheet, ExcelType excelType, int rowIndex) throws Exception{
        String log20 = sheet426PDM.getLog20();
        String log21 = sheet426PDM.getLog21();
        Date currentTime = new Date();
        SimpleDateFormat df = new SimpleDateFormat(ExcelConfig.fileDateFormat);
        String strTime = df.format(currentTime);

        int oleId20 = sheet.getWorkbook().addOlePackage(log20.getBytes()
                , strTime + "host20.log"
                , strTime + "host20.log", strTime + "host20.log");

        int oleId21 = sheet.getWorkbook().addOlePackage(log21.getBytes()
                , strTime + "host21.log"
                , strTime + "host21.log", strTime + "host21.log");

        if(excelType.equals(ExcelType.Xlsx)){
            XSSFSheet sheetXSSF = (XSSFSheet)sheet;

            XSSFClientAnchor imgAnchor20 = new XSSFClientAnchor(0, 0, 0, 0
                    , ExcelConfig.Sheet426Start, rowIndex, ExcelConfig.Sheet426Start, rowIndex);
            XSSFClientAnchor imgAnchor21 = new XSSFClientAnchor(0, 0, 10, 10
                    , ExcelConfig.Sheet426Start, rowIndex, ExcelConfig.Sheet426Start, rowIndex);

            XSSFDrawing patriarch = sheetXSSF.createDrawingPatriarch();
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

    private static int loadIcon(Workbook wb) throws Exception{
        URL url = Sheet426Filler.class.getResource("/jessica.png");
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

        return wb.addPicture(bos.toByteArray(), Workbook.PICTURE_TYPE_PNG);        
    }

}
