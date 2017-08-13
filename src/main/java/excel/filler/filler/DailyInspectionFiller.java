package excel.filler.filler;

import excel.filler.config.DailyAppExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.model.DailyAppInspectionPDM;
import excel.filler.utils.DailyInspectionUtils;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DailyInspectionFiller {

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

        insert(sheetDailyApp, dailyAppPDM);

        //Safely save and close workbook
        is.close();
        FileOutputStream out = new FileOutputStream(destFile);
        wb.write(out);
        out.close();
        wb.close();
    }

    private static void insert(Sheet sheetDailyApp
            , DailyAppInspectionPDM dailyAppPDM) throws Exception{

        int lastRowIndex = ExcelUtils.getLastRow(sheetDailyApp
                , DailyAppExcelConfig.getAppRecordStartRow()
                , DailyAppExcelConfig.getAppDayCellIndex());

        if(lastRowIndex == DailyAppExcelConfig.getAppRecordStartRow() - 1){
            lastRowIndex++;
        }

        Integer newRowIndex = DailyInspectionUtils.getRowIndexAndCreateFrame(sheetDailyApp
                , dailyAppPDM.getInspectTime(), dailyAppPDM.getClusterName()
                , lastRowIndex
                , DailyAppExcelConfig.getAppDayCellIndex()
                , DailyAppExcelConfig.getAppTimeCellIndex()
                , DailyAppExcelConfig.getAppHostNamesIndex()
                , DailyAppExcelConfig.getAppStart()
                , DailyAppExcelConfig.getAppEnd()
                , DailyAppExcelConfig.getCoreInspectClustersNames()
                , DailyAppExcelConfig.getInspectTimes());

    }
}
