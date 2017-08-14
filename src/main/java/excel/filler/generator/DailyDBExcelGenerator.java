package excel.filler.generator;

import excel.filler.config.DailyAppExcelConfig;
import excel.filler.config.DailyDBExcelConfig;
import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.filler.DailyInspectionAppFiller;
import excel.filler.filler.DailyInspectionDBFiller;
import excel.filler.model.DailyAppInspectionPDM;
import excel.filler.model.DailyDBInspectionPDM;
import org.suns.database.utils.controller.DailyAppController;
import org.suns.database.utils.controller.DailyDBController;
import org.suns.database.utils.model.DailyAppInspectionModel;
import org.suns.database.utils.model.DailyDBInspectionModel;

import java.util.ArrayList;

public class DailyDBExcelGenerator {
    
    public static void generatePersonal() throws Exception{

        ArrayList<DailyDBInspectionModel> dailyDBModels = DailyDBController
                .getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);

        if(dailyDBModels == null || dailyDBModels.isEmpty()){
            return;
        }

        for(DailyDBInspectionModel dailyDBModel : dailyDBModels){
            DailyDBInspectionPDM dailyPDM = new DailyDBInspectionPDM();
            fillDBPdmFromModel(dailyPDM, dailyDBModel);

            DailyInspectionDBFiller.fill(dailyPDM, FileDestination.Personal);
        }
    }

    public static void generateCore() throws Exception{
        ArrayList<DailyDBInspectionModel> dailyDBModels = DailyDBController
                .getRecentInstancesCore(ExcelConfig.daysRecentInstances);

        if(dailyDBModels == null || dailyDBModels.isEmpty()){
            return;
        }

        for(DailyDBInspectionModel dailyDBModel : dailyDBModels){
            DailyDBInspectionPDM dailyPDM = new DailyDBInspectionPDM();
            fillDBPdmFromModel(dailyPDM, dailyDBModel);

            DailyInspectionDBFiller.fill(dailyPDM, FileDestination.Core);
        }
    }

    private static void fillDBPdmFromModel(DailyDBInspectionPDM dailyPDM
            , DailyDBInspectionModel dailyDBModel){
        dailyPDM.setInspectTime(dailyDBModel.getInspectTime());
        dailyPDM.setClusterName(dailyDBModel.getName());
        dailyPDM.setCpuUsage(dailyDBModel.getUsageCPU());
        dailyPDM.setMemoryUsage(dailyDBModel.getUsageMemory());
        dailyPDM.setArchiveUsage(dailyDBModel.getUsageArchiveSpace());

        if(dailyDBModel.getHasLongTermLock() == 1){
            dailyPDM.setHasLongTermLock(DailyDBExcelConfig.getDescriptionHasException());
        }else{
            dailyPDM.setHasLongTermLock(DailyDBExcelConfig.getDescriptionNoException());
        }

        if(dailyDBModel.getHasOverloadTableSpace() == 1){
            dailyPDM.setHasOverloadedTable(DailyDBExcelConfig.getDescriptionHasOverloadedTable());
        }else{
            dailyPDM.setHasOverloadedTable(DailyDBExcelConfig.getDescriptionNoOverloadedTable());
        }

        if(dailyDBModel.getHasErrorInLog() == 1){
            dailyPDM.setHasErrorLog(DailyDBExcelConfig.getDescriptionHasException());
        }else{
            dailyPDM.setHasErrorLog(DailyDBExcelConfig.getDescriptionNoException());
        }

        dailyPDM.setDiskBusy(dailyDBModel.getDiskBusy());

    }
}
