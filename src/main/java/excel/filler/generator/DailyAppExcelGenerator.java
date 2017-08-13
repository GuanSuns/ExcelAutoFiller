package excel.filler.generator;

import excel.filler.config.DailyAppExcelConfig;
import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.filler.DailyInspectionAppFiller;
import excel.filler.model.DailyAppInspectionPDM;
import excel.filler.utils.ExcelUtils;
import org.suns.database.utils.controller.DailyAppController;
import org.suns.database.utils.controller.DailyDBController;
import org.suns.database.utils.model.DailyAppInspectionModel;

import java.util.ArrayList;
import java.util.Date;

public class DailyAppExcelGenerator {

    public static void generatePersonal() throws Exception{

        ArrayList<DailyAppInspectionModel> dailyAppModels = DailyAppController
                .getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);

        if(dailyAppModels == null || dailyAppModels.isEmpty()){
            return;
        }

        for(DailyAppInspectionModel dailyAppModel : dailyAppModels){
            DailyAppInspectionPDM dailyPDM = new DailyAppInspectionPDM();
            fillAppPdmFromModel(dailyPDM, dailyAppModel);
            DailyInspectionAppFiller.fill(dailyPDM, FileDestination.Personal);
        }
    }

    public static void generateCore() throws Exception{
        ArrayList<DailyAppInspectionModel> dailyAppModels = DailyAppController
                .getRecentInstancesCore(ExcelConfig.daysRecentInstances);

        if(dailyAppModels == null || dailyAppModels.isEmpty()){
            return;
        }

        for(DailyAppInspectionModel dailyAppModel : dailyAppModels){
            DailyAppInspectionPDM dailyPDM = new DailyAppInspectionPDM();
            fillAppPdmFromModel(dailyPDM, dailyAppModel);
            DailyInspectionAppFiller.fill(dailyPDM, FileDestination.Core);
        }
    }

    private static void fillAppPdmFromModel(DailyAppInspectionPDM dailyPDM
            , DailyAppInspectionModel dailyAppModel){
        dailyPDM.setInspectTime(dailyAppModel.getInspectTime());
        dailyPDM.setClusterName(dailyAppModel.getName());
        dailyPDM.setCpuUsage(dailyAppModel.getUsageCPU());
        dailyPDM.setMemoryUsage(dailyAppModel.getUsageMemory());
        dailyPDM.setSoftwareDirectoryUsage(dailyAppModel.getFileSysUsage());

        if(dailyAppModel.getSvrState() == 1){
            dailyPDM.setSvrState(DailyAppExcelConfig.getDescriptionIsRunning());
        }else{
            dailyPDM.setSvrState(DailyAppExcelConfig.getDescriptionNotRunning());
        }

        dailyPDM.setHoggingThreadCnt(dailyAppModel.getHoggingCount().intValue());
        dailyPDM.setJdbcConnectionCnt(dailyAppModel.getDataSourceConnectionCount().intValue());

        if(dailyAppModel.getDataSourceState() == 1){
            dailyPDM.setJdbcState(DailyAppExcelConfig.getDescriptionIsRunning());
        }else{
            dailyPDM.setJdbcState(DailyAppExcelConfig.getDescriptionNotRunning());
        }

        dailyPDM.setJdbcConnectionCnt(dailyAppModel.getDataSourceConnectionCount().intValue());
    }
}
