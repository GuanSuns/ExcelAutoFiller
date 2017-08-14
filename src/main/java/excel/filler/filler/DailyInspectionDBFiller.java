package excel.filler.filler;

import excel.filler.config.DailyDBExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.DailyDBInspectionPDM;
import excel.filler.utils.DailyInspectionUtils;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.suns.inspection.logger.InspectionLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DailyInspectionDBFiller {
    public static void fill(DailyDBInspectionPDM dailyDBPDM
            , FileDestination destination) throws Exception{
        if(dailyDBPDM == null || destination == null){
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
        Sheet sheetDailyDB = wb.getSheet(DailyDBExcelConfig.getSheetName());
        if(sheetDailyDB == null){
            throw  new Exception("No corresponding sheet "
                    + DailyDBExcelConfig.getSheetName());
        }

        insert(sheetDailyDB, dailyDBPDM, destination);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    private static void insert(Sheet sheetDailyDB
            , DailyDBInspectionPDM dailyDBPDM
            , FileDestination destination) throws Exception{

        int lastRowIndex = ExcelUtils.getLastRow(sheetDailyDB
                , DailyDBExcelConfig.getDBRecordStartRow()
                , DailyDBExcelConfig.getDBDayCellIndex());

        if(lastRowIndex == DailyDBExcelConfig.getDBRecordStartRow() - 1){
            lastRowIndex++;
        }

        InspectionLogger.info("Calculating row index for new daily DB PDM \'"
                + dailyDBPDM.getClusterName() + "\'");

        String[] printedNameList;
        String[] nameList;
        if(destination.equals(FileDestination.Core)){
            printedNameList = DailyDBExcelConfig.getCoreInspectClustersPrintedNames();
            nameList = DailyDBExcelConfig.getCoreInspectClustersNames();
        }else{
            printedNameList = DailyDBExcelConfig.getPersonalInspectClustersPrintedNames();
            nameList = DailyDBExcelConfig.getPersonalInspectClustersNames();
        }

        Integer newRowIndex = DailyInspectionUtils.getRowIndexAndCreateFrame(sheetDailyDB
                , dailyDBPDM.getInspectTime()
                , dailyDBPDM.getClusterName()
                , lastRowIndex
                , DailyDBExcelConfig.getDBDayCellIndex()
                , DailyDBExcelConfig.getDBTimeCellIndex()
                , DailyDBExcelConfig.getDBHostNamesIndex()
                , DailyDBExcelConfig.getDBStart()
                , DailyDBExcelConfig.getDBEnd()
                , printedNameList
                , nameList
                , DailyDBExcelConfig.getInspectTimes());

        if(newRowIndex == null){
            return;
        }

        InspectionLogger.info("Adding new daily DB PDM \'"
                + dailyDBPDM.getClusterName() + "\', New row index is " + newRowIndex);

        Row newRow = ExcelUtils.getRow(sheetDailyDB, newRowIndex);

        ArrayList<Float> dataFloat = new ArrayList<>();
        dataFloat.add(dailyDBPDM.getCpuUsage());
        dataFloat.add(dailyDBPDM.getMemoryUsage());
        dataFloat.add(dailyDBPDM.getArchiveUsage());
        ExcelUtils.fillRowWithPercentage(newRow, DailyDBExcelConfig.getCpuUsageIndex()
                , DailyDBExcelConfig.getArchiveUsageIndex() + 1
                , dataFloat, false);

        Workbook wb = sheetDailyDB.getWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        //Set cell style to default style
        ExcelUtils.initDefaultCellStyle(cellStyle, font);

        Cell cellLongTermLock = ExcelUtils.getCell(newRow, DailyDBExcelConfig.getLongTermLockIndex());
        cellLongTermLock.setCellStyle(cellStyle);
        cellLongTermLock.setCellValue(dailyDBPDM.getHasLongTermLock());

        Cell cellOverloadedTable = ExcelUtils.getCell(newRow, DailyDBExcelConfig.getTableSpaceCheckIndex());
        cellOverloadedTable.setCellStyle(cellStyle);
        cellOverloadedTable.setCellValue(dailyDBPDM.getHasOverloadedTable());

        Cell cellAlertLogCheck = ExcelUtils.getCell(newRow, DailyDBExcelConfig.getAlertLogIndex());
        cellAlertLogCheck.setCellStyle(cellStyle);
        cellAlertLogCheck.setCellValue(dailyDBPDM.getHasErrorLog());

        ArrayList<Float> diskBusyData = new ArrayList<>();
        diskBusyData.add(dailyDBPDM.getDiskBusy());
        ExcelUtils.fillRowWithPercentage(newRow, DailyDBExcelConfig.getDiskBusyIndex()
                , DailyDBExcelConfig.getDiskBusyIndex() + 1
                , diskBusyData, false);
    }
}
