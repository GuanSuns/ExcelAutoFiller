package excel.filler.utils;

import excel.filler.config.ExcelConfig;
import org.apache.poi.ss.usermodel.*;
import org.suns.database.utils.config.DBConfig;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by guanl on 6/19/2017.
 */
public class ExcelUtils {

    private static void fillPercentages(Row row
            , int start, int end
            , ArrayList<Float> data, CellStyle cellStyle
            , CellStyle cellBlankStyle) throws Exception{
        //fill cells in row
        for(int i = 0; start + i < end; i++){
            Float d = data.get(i);
            Cell cellPercentage = getCell(row, start + i);
            cellPercentage.setCellStyle(cellStyle);

            //If the data is not collected
            if(d == null || d == DBConfig.getDefaultNumericNullValue()){
                cellPercentage.setCellStyle(cellBlankStyle);
                cellPercentage.setCellType(CellType.STRING);
                cellPercentage.setCellValue(ExcelConfig.nullValue);
            }else{
                cellPercentage.setCellValue(d/100f);
            }
        }
    }

    private static void setDefaultFont(Font font){
        font.setFontHeight(ExcelConfig.fontSize);
        font.setFontName(ExcelConfig.font);
    }

    //Turn on borders
    private static void setBorder(CellStyle cellStyle){
        cellStyle.setBorderBottom(ExcelConfig.borderStyle);
        cellStyle.setBottomBorderColor(ExcelConfig.borderColor);
        cellStyle.setBorderTop(ExcelConfig.borderStyle);
        cellStyle.setTopBorderColor(ExcelConfig.borderColor);
        cellStyle.setBorderLeft(ExcelConfig.borderStyle);
        cellStyle.setLeftBorderColor(ExcelConfig.borderColor);
        cellStyle.setBorderRight(ExcelConfig.borderStyle);
        cellStyle.setRightBorderColor(ExcelConfig.borderColor);
    }

    //Set inner alignment to center (both horizontal and vertical alignment)
    private static void centralize(CellStyle cellStyle){
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    public static Cell getCell(Row row, int id) throws Exception{
        if(row == null){
            throw new Exception("Uninitialized Row");
        }else if(id < 0){
            throw  new Exception("Invalid Cell ID");
        }

        Cell cell = row.getCell(id);
        if(cell == null){
            cell = row.createCell(id);
        }

        return cell;
    }

    public static Row getRow(Sheet sheet, int id) throws Exception{
        if(sheet == null){
            throw new Exception("Uninitialized Sheet");
        }else if(id < 0){
            throw new Exception("Invalid Row ID");
        }

        Row row = sheet.getRow(id);
        if(row == null){
            row = sheet.createRow(id);
        }

        return row;
    }

    public static void setDefaultDateCellStyle(CellStyle cellStyle, Workbook wb){
        short format = wb.createDataFormat().getFormat(ExcelConfig.dateFormat);
        cellStyle.setDataFormat(format);
    }

    public static void initDefaultCellStyle(CellStyle cellStyle, Font font){
        if(cellStyle == null){
            return;
        }

        //If border is turned on
        if(ExcelConfig.cellBorder){
            setBorder(cellStyle);
        }
        //If central alignment is turned on
        if(ExcelConfig.centralAlignment){
            centralize(cellStyle);
        }

        //Set font
        setDefaultFont(font);
        cellStyle.setFont(font);
    }

    //variable start is inclusive, while variable end is exclusive
    public static void fillRowWithPercentage(Row row
            , int start, int end
            , ArrayList<Float> data, boolean isRound) throws Exception{
        //Safety Check
        if(row == null || data == null || data.isEmpty()){
            return;
        }else if(start < 0 || end < start){
            return;
        }

        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellStyleBlank = wb.createCellStyle();
        Font font = wb.createFont();

        initDefaultCellStyle(cellStyle, font);
        initDefaultCellStyle(cellStyleBlank, font);

        //Set data format to percentage
        if(isRound){
            cellStyle.setDataFormat(wb.createDataFormat().getFormat("0%"));
        }else{
            cellStyle.setDataFormat(wb.createDataFormat().getFormat("0.00%"));
        }

        fillPercentages(row, start, end, data, cellStyle, cellStyleBlank);
    }

    public static void fillRowWithBlank(Row row, int start, int end) throws Exception{
        //Safety Check
        if(start < 0 || end < start){
            return;
        }

        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();

        initDefaultCellStyle(cellStyle, font);

        //fill cells in row
        for(int i = 0; start + i < end; i++){
            Cell cellBlank = getCell(row, start + i);
            cellBlank.setCellStyle(cellStyle);

            cellBlank.setCellType(CellType.STRING);
            cellBlank.setCellValue("");
        }
    }

    public static void fillRowWithString(Row row, int start, int end
            , ArrayList<String> data) throws Exception{
        //Safety Check
        if(row == null || data == null || data.isEmpty()){
            return;
        }else if(start < 0 || end < start){
            return;
        }

        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();

        initDefaultCellStyle(cellStyle, font);

        //fill cells in row
        for(int i = 0; start + i < end; i++){
            Cell cellBlank = getCell(row, start + i);
            cellBlank.setCellStyle(cellStyle);
            cellBlank.setCellValue(data.get(i));
        }
    }

    public static void fillRowWithFloat(Row row, int start, int end
            , ArrayList<Float> data) throws Exception{
        //Safety Check
        if(row == null || data == null || data.isEmpty()){
            return;
        }else if(start < 0 || end < start){
            return;
        }

        Workbook wb = row.getSheet().getWorkbook();
        CellStyle cellStyleFloat = wb.createCellStyle();
        CellStyle cellStyleInt = wb.createCellStyle();
        CellStyle cellStyleBlank = wb.createCellStyle();
        Font font = wb.createFont();

        initDefaultCellStyle(cellStyleFloat, font);
        initDefaultCellStyle(cellStyleInt, font);
        initDefaultCellStyle(cellStyleBlank, font);
        cellStyleFloat.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        cellStyleInt.setDataFormat(wb.createDataFormat().getFormat("0"));

        //fill cells in row
        for(int i = 0; start + i < end; i++){
            Cell cellData = getCell(row, start + i);
            float f = data.get(i);
            if(f == DBConfig.getDefaultNumericNullValue()){
                cellData.setCellStyle(cellStyleBlank);
                cellData.setCellValue(" ");
                continue;
            }

            if(Math.round(f) - f == 0){
                cellData.setCellStyle(cellStyleInt);
                cellData.setCellValue((int)f);
            }else{
                cellData.setCellStyle(cellStyleFloat);
                cellData.setCellValue(f);
            }

        }
    }

    public static String timestampToString(Timestamp timestamp) throws Exception{
        DateFormat dateFormat = new SimpleDateFormat(ExcelConfig.dateFormat);
        return dateFormat.format(timestamp);
    }

    public static int getLastRow(Sheet sheet, int startRow, int idIndex) throws Exception{
        int lastIndex = sheet.getLastRowNum();
        if(lastIndex == startRow) return lastIndex;

        for(int i = lastIndex; i >= startRow; i--){
            Row row = getRow(sheet, i);
            Cell cellID = getCell(row, idIndex);
            if(DateUtil.isCellDateFormatted(cellID)
                    && cellID.getDateCellValue()!= null){
                return i;
            }
        }

        return startRow - 1;
    }
}
