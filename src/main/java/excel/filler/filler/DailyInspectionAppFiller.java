package excel.filler.filler;

import excel.filler.config.DailyAppExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.DailyAppInspectionPDM;
import excel.filler.utils.DailyInspectionUtils;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.suns.inspection.logger.InspectionLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DailyInspectionAppFiller {

    public static void fill(DailyAppInspectionPDM dailyAppPDM
            , FileDestination destination) throws Exception{
        if(dailyAppPDM == null || destination == null){
            throw new Exception("Invalid null arguments");
        }

        File destFile;
        if(destination.equals(FileDestination.Core)){
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.DailyCoreFile);
        }else{
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.DailyPersonalFile);
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
        Sheet sheetDailyApp = wb.getSheet(DailyAppExcelConfig.getSheetName());
        if(sheetDailyApp == null){
            throw  new Exception("No corresponding sheet "
                    + DailyAppExcelConfig.getSheetName());
        }

        insert(sheetDailyApp, dailyAppPDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    private static void insert(Sheet sheetDailyApp
            , DailyAppInspectionPDM dailyAppPDM
            , FileDestination destination) throws Exception{

        int lastRowIndex = ExcelUtils.getLastRow(sheetDailyApp
                , DailyAppExcelConfig.getAppRecordStartRow()
                , DailyAppExcelConfig.getAppDayCellIndex());

        if(lastRowIndex == DailyAppExcelConfig.getAppRecordStartRow() - 1){
            lastRowIndex++;
        }

        InspectionLogger.info("Calculating row index for new daily App PDM \'"
                + dailyAppPDM.getClusterName() + "\'");

        String[] printedNameList;
        if(destination.equals(FileDestination.Core)){
            printedNameList = DailyAppExcelConfig.getCoreInspectClustersPrintedNames();
        }else{
            printedNameList = DailyAppExcelConfig.getPersonalInspectClustersPrintedNames();
        }

        Integer newRowIndex = DailyInspectionUtils.getRowIndexAndCreateFrame(sheetDailyApp
                , dailyAppPDM.getInspectTime(), dailyAppPDM.getClusterName()
                , lastRowIndex
                , DailyAppExcelConfig.getAppDayCellIndex()
                , DailyAppExcelConfig.getAppTimeCellIndex()
                , DailyAppExcelConfig.getAppHostNamesIndex()
                , DailyAppExcelConfig.getAppStart()
                , DailyAppExcelConfig.getAppEnd()
                , printedNameList
                , DailyAppExcelConfig.getCoreInspectClustersNames()
                , DailyAppExcelConfig.getInspectTimes());

        if(newRowIndex != null){
            return;
        }

        InspectionLogger.info("Adding new daily App PDM \'"
                + dailyAppPDM.getClusterName() + "\', New row index is " + newRowIndex);

        Row newRow = ExcelUtils.getRow(sheetDailyApp, newRowIndex);

        ArrayList<Float> dataFloat = new ArrayList<>();
        dataFloat.add(dailyAppPDM.getCpuUsage());
        dataFloat.add(dailyAppPDM.getMemoryUsage());
        dataFloat.add(dailyAppPDM.getSoftwareDirectoryUsage());
        ExcelUtils.fillRowWithPercentage(newRow, DailyAppExcelConfig.getCpuUsageIndex()
                , DailyAppExcelConfig.getSoftwareDirectoryUsageIndex()+1
                , dataFloat, false);

        Workbook wb = sheetDailyApp.getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);

        Cell cellSvrState = ExcelUtils.getCell(newRow, DailyAppExcelConfig.getSvrStateIndex());
        cellSvrState.setCellStyle(cellStyle);
        cellSvrState.setCellValue(dailyAppPDM.getSvrState());

        Cell cellHoggingThreadCnt = ExcelUtils.getCell(newRow, DailyAppExcelConfig.getHoggingThreadCntIndex());
        cellHoggingThreadCnt.setCellStyle(cellStyle);
        cellHoggingThreadCnt.setCellValue(dailyAppPDM.getHoggingThreadCnt());

        Cell cellJdbcRunningState = ExcelUtils.getCell(newRow, DailyAppExcelConfig.getJdbcStateIndex());
        cellJdbcRunningState.setCellStyle(cellStyle);
        cellJdbcRunningState.setCellValue(dailyAppPDM.getJdbcState());

        Cell cellJdbcConnectionCnt = ExcelUtils.getCell(newRow, DailyAppExcelConfig.getJdbcConnectionCntIndex());
        cellJdbcConnectionCnt.setCellStyle(cellStyle);
        cellJdbcConnectionCnt.setCellValue(dailyAppPDM.getJdbcConnectionCnt());
    }
}
