package utils;

import config.ExcelConfig;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;

/**
 * Created by guanl on 6/19/2017.
 */
public class ExcelUtils {

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
        //If border is turned on
        if(ExcelConfig.cellBorder){
            setBorder(cellStyle);
        }
        //If central alignment is turned on
        if(ExcelConfig.centralAlginment){
            centralize(cellStyle);
        }

        //Set font
        Font font = wb.createFont();
        setDefaultFont(font);
        cellStyle.setFont(font);

        //Set data format to percentage
        if(isRound){
            cellStyle.setDataFormat(wb.createDataFormat().getFormat("0%"));
        }else{
            cellStyle.setDataFormat(wb.createDataFormat().getFormat("0.00%"));
        }

        fillPercentages(row, start, end, data, cellStyle);
    }

    private static void fillPercentages(Row row
            , int start, int end
            , ArrayList<Float> data
            , CellStyle cellStyle) throws Exception{
        //fill cells in row
        for(int i = 0; start + i < end; i++){
            Cell cellPercentage = getCell(row, start);
            cellPercentage.setCellStyle(cellStyle);
            cellPercentage.setCellValue(data.get(i));
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
}
