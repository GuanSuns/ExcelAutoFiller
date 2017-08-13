package excel.filler.generator;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.filler.Sheet424Filler;
import excel.filler.model.Sheet424CorePDM;
import excel.filler.model.Sheet424PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.suns.database.utils.controller.Sheet424Controller;
import org.suns.database.utils.model.Sheet424CoreModel;
import org.suns.database.utils.model.Sheet424PersonalModel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet424Generator {
    public static void generatePersonal() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet424PersonalModel> sheet424Models = Sheet424Controller.getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);
        if(sheet424Models == null || sheet424Models.isEmpty()){
            return;
        }

        if(latestDate != null){

            for(Sheet424PersonalModel sheet424Model : sheet424Models){
                if(sheet424Model.getDate().getTime() > latestDate.getTime()){
                    Sheet424PersonalPDM sheet424PersonalPDM = new Sheet424PersonalPDM();
                    fillPersonalPdmFromModel(sheet424PersonalPDM, sheet424Model);
                    Sheet424Filler.fill(sheet424PersonalPDM, FileDestination.Personal);
                }
            }
        }else{
            for(Sheet424PersonalModel sheet424Model : sheet424Models){
                Sheet424PersonalPDM sheet424PersonalPDM = new Sheet424PersonalPDM();
                fillPersonalPdmFromModel(sheet424PersonalPDM, sheet424Model);
                Sheet424Filler.fill(sheet424PersonalPDM, FileDestination.Personal);
            }
        }
    }

    public static void generateCore() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Core);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet424CoreModel> sheet424Models = Sheet424Controller.getRecentInstancesCore(ExcelConfig.daysRecentInstances);
        if(sheet424Models == null || sheet424Models.isEmpty()){
            return;
        }

        if(latestDate != null){
            for(Sheet424CoreModel sheet424Model : sheet424Models){
                if(sheet424Model.getDate().getTime() > latestDate.getTime()){
                    Sheet424CorePDM sheet424CorePDM = new Sheet424CorePDM();
                    fillCorePdmFromModel(sheet424CorePDM, sheet424Model);
                    Sheet424Filler.fill(sheet424CorePDM, FileDestination.Core);
                }
            }
        }else{
            for(Sheet424CoreModel sheet424Model : sheet424Models){
                Sheet424CorePDM sheet424CorePDM = new Sheet424CorePDM();
                fillCorePdmFromModel(sheet424CorePDM, sheet424Model);
                Sheet424Filler.fill(sheet424CorePDM, FileDestination.Core);
            }
        }
    }

    private static void fillPersonalPdmFromModel(Sheet424PersonalPDM pdm, Sheet424PersonalModel model){
        pdm.setDate(model.getDate());
        pdm.setOrder1(null);
        pdm.setProvince(null);
        pdm.setCollectTime2(model.getInspectTime2());
        pdm.setTempStatus2(model.getStatus2());
    }

    private static void fillCorePdmFromModel(Sheet424CorePDM pdm, Sheet424CoreModel model){
        fillPersonalPdmFromModel(pdm, model);

        pdm.setCollectTime3(model.getInspectTime3());
        pdm.setTempStatus3(model.getStatus3());

        pdm.setCollectTime4(model.getInspectTime4());
        pdm.setTempStatus4(model.getStatus4());
    }

    private static Date getLatestTime(FileDestination fileDestination) throws Exception{

        File destFile;
        if(fileDestination.equals(FileDestination.Personal)){
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.MonthlyPersonalFile);
        }else{
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.MonthlyCoreFile);
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
        Sheet sheet424 = wb.getSheet(ExcelConfig.SheetName424);
        if(sheet424 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName424);
        }

        int index = ExcelUtils.getLastRow(sheet424
                , ExcelConfig.Sheet424RecordStartRow
                , ExcelConfig.Sheet424TimeCellIndex);

        if(index <= ExcelConfig.Sheet424RecordStartRow) return null;

        Row row = ExcelUtils.getRow(sheet424, index);
        return ExcelUtils.getCell(row, ExcelConfig.Sheet424TimeCellIndex).getDateCellValue();
    }
}
