package excel.filler.utils;

import excel.filler.config.DailyExcelConfig;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.sql.Timestamp;
import java.util.Calendar;

public class DailyInspectionUtils {
    public static void setDefaultDayCellStyle(CellStyle cellStyle, Workbook wb){
        short format = wb.createDataFormat().getFormat(DailyExcelConfig.getDayDateFormat());
        cellStyle.setDataFormat(format);
    }

    public static void setDefaultTimeCellStyle(CellStyle cellStyle, Workbook wb){
        short format = wb.createDataFormat().getFormat(DailyExcelConfig.getTimeDateFormat());
        cellStyle.setDataFormat(format);
    }

    public static Integer getRowIndexAndCreateFrame(Sheet sheet
            , Timestamp pdmInspectTime, String clusterName
            , int lastRowIndex
            , int dayCellIndex, int hourIndex, int nameListIndex
            , int startIndex, int endIndex, String[] printedNameList
            , String[] nameList, Calendar[] inspectTimes) throws Exception{

        Row row = ExcelUtils.getRow(sheet, lastRowIndex);
        Cell cellID = ExcelUtils.getCell(row, dayCellIndex);

        Calendar inspectTime = Calendar.getInstance();
        inspectTime.setTime(pdmInspectTime);

        if(DateUtil.isCellDateFormatted(cellID)
                && cellID.getDateCellValue()!= null){

            Calendar frameDate = Calendar.getInstance();
            frameDate.setTime(cellID.getDateCellValue());

            if(isInSameDay(frameDate, inspectTime)){
                return getIndex(nameList, clusterName, inspectTimes
                        , inspectTime, lastRowIndex);
            }else if(isLaterThan(frameDate, inspectTime)){
                int newRowIndex = lastRowIndex + nameList.length * inspectTimes.length;
                createNewFrame(sheet, inspectTime
                        , newRowIndex
                        , dayCellIndex
                        , hourIndex
                        , nameListIndex
                        , startIndex
                        , endIndex
                        , printedNameList
                        , inspectTimes);
                return getIndex(nameList, clusterName, inspectTimes
                        , inspectTime, newRowIndex);
            }else{
                return null;
            }
        }else{

            createNewFrame(sheet, inspectTime
                    , lastRowIndex
                    , dayCellIndex
                    , hourIndex
                    , nameListIndex
                    , startIndex
                    , endIndex
                    , printedNameList
                    , inspectTimes);
            return getIndex(nameList, clusterName, inspectTimes
                    , inspectTime, lastRowIndex);
        }
    }

    private static void createNewFrame(Sheet sheet
            , Calendar inspectTime, int newRowIndex
            , int dayCellIndex, int hourIndex, int nameListIndex
            , int startIndex, int endIndex
            , String[] printedNameList, Calendar[] inspectTimes) throws Exception{


        Row row = ExcelUtils.getRow(sheet, newRowIndex);
        Workbook wb = sheet.getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle cellDayDateStyle = wb.createCellStyle();
        CellStyle cellTimeDateStyle = wb.createCellStyle();
        Font font = wb.createFont();

        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);
        ExcelUtils.initDefaultCellStyle(cellDayDateStyle, font);
        ExcelUtils.initDefaultCellStyle(cellTimeDateStyle, font);
        DailyInspectionUtils.setDefaultDayCellStyle(cellDayDateStyle, wb);
        DailyInspectionUtils.setDefaultTimeCellStyle(cellTimeDateStyle, wb);

        Cell dayDateCell = ExcelUtils.getCell(row, dayCellIndex);
        dayDateCell.setCellStyle(cellDayDateStyle);
        dayDateCell.setCellValue(inspectTime);
        dayDateCell.getSheet().autoSizeColumn(dayCellIndex);

        CellRangeAddress dayCellRange = new CellRangeAddress(newRowIndex
                , newRowIndex + printedNameList.length * inspectTimes.length - 1
                , dayCellIndex, dayCellIndex);

        sheet.addMergedRegion(dayCellRange);
        ExcelUtils.setMergeRegionBorder(sheet, dayCellRange);

        for(int i=0; i<inspectTimes.length; i++){

            int timeCellStartRow = newRowIndex + i*printedNameList.length;

            Row timeRow = ExcelUtils.getRow(sheet, timeCellStartRow);
            Cell timeDateCell = ExcelUtils.getCell(timeRow, hourIndex);
            timeDateCell.setCellStyle(cellTimeDateStyle);
            timeDateCell.setCellValue(inspectTimes[i]);

            if(printedNameList.length > 1){
                CellRangeAddress timeCellRange = new CellRangeAddress(timeCellStartRow
                        , timeCellStartRow + printedNameList.length - 1
                        , hourIndex, hourIndex);

                sheet.addMergedRegion(timeCellRange);
                ExcelUtils.setMergeRegionBorder(sheet, timeCellRange);
            }

            for (int j=0; j<printedNameList.length; j++){
                int nameRowIndex = timeCellStartRow + j;
                Row nameRow = ExcelUtils.getRow(sheet, nameRowIndex);
                Cell nameCell = ExcelUtils.getCell(nameRow, nameListIndex);
                nameCell.setCellStyle(cellStyle);
                nameCell.setCellValue(printedNameList[j]);
                ExcelUtils.fillRowWithBlank(nameRow, startIndex, endIndex);
            }
        }
    }

    private static boolean isLaterThan(Calendar expectTime, Calendar actualTime){
        if(expectTime.get(Calendar.YEAR) < actualTime.get(Calendar.YEAR)
                || expectTime.get(Calendar.MONTH) < actualTime.get(Calendar.MONTH)
                || expectTime.get(Calendar.DAY_OF_MONTH) < actualTime.get(Calendar.DAY_OF_MONTH)){
            return true;
        }else{
            return false;
        }
    }

    private static Integer getIndex(String[] nameList, String clusterName
            , Calendar[] inspectTimes, Calendar inspectTime, int lastRowIndex){
        Integer timeIntervalIndex = getTimeIntervalIndex(inspectTimes, inspectTime);
        if(timeIntervalIndex == null){
            return null;
        }

        Integer nameListIndex = getNameListIndex(nameList, clusterName);
        if(nameListIndex == null){
            return null;
        }else{
            return lastRowIndex
                    + timeIntervalIndex * nameList.length
                    + nameListIndex;
        }
    }

    private static Integer getNameListIndex(String[] NameList, String hostName){
        for(int i=0; i<NameList.length; i++){
            if(NameList[i].equals(hostName)){
                return i;
            }
        }
        return null;
    }

    private static boolean isInSameDay(Calendar time1, Calendar time2){
        if(time1.get(Calendar.YEAR) == time2.get(Calendar.YEAR)
                && time1.get(Calendar.MONTH) == time2.get(Calendar.MONTH)
                && time1.get(Calendar.DAY_OF_MONTH) == time2.get(Calendar.DAY_OF_MONTH)){
            return true;
        }else{
            return false;
        }
    }

    private static Integer getTimeIntervalIndex(Calendar[] inspectTimes
            , Calendar inspectTime){

        for(int i=0; i<inspectTimes.length; i++){
            Calendar timeInterval = inspectTimes[i];
            if(isInTimeInterval(timeInterval, inspectTime)){
                return i;
            }
        }
        return null;
    }

    private static boolean isInTimeInterval(Calendar expectTime, Calendar actualTime){
        if(expectTime.get(Calendar.HOUR_OF_DAY)-1 <= actualTime.get(Calendar.HOUR_OF_DAY)
                && expectTime.get(Calendar.HOUR_OF_DAY)+1 >= actualTime.get(Calendar.HOUR_OF_DAY)){
            return true;
        }else{
            return false;
        }
    }

}
