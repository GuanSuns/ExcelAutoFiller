package excel.filler.generator;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.filler.Sheet422Filler;
import excel.filler.model.Sheet422CorePDM;
import excel.filler.model.Sheet422PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.suns.database.utils.controller.Sheet422Controller;
import org.suns.database.utils.model.Sheet422CoreModel;
import org.suns.database.utils.model.Sheet422PersonalModel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet422Generator {

    public static void generatePersonal() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet422PersonalModel> sheet422Models = Sheet422Controller.getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);
        if(sheet422Models == null || sheet422Models.isEmpty()){
            return;
        }

        if(latestDate != null){

            for(Sheet422PersonalModel sheet422Model : sheet422Models){
                if(sheet422Model.getDate().getTime() > latestDate.getTime()){
                    Sheet422PersonalPDM sheet422PersonalPDM = new Sheet422PersonalPDM();
                    fillPersonalPdmFromModel(sheet422PersonalPDM, sheet422Model);
                    Sheet422Filler.fill(sheet422PersonalPDM, FileDestination.Personal);
                }
            }
        }else{
            for(Sheet422PersonalModel sheet422Model : sheet422Models){
                Sheet422PersonalPDM sheet422PersonalPDM = new Sheet422PersonalPDM();
                fillPersonalPdmFromModel(sheet422PersonalPDM, sheet422Model);
                Sheet422Filler.fill(sheet422PersonalPDM, FileDestination.Personal);
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

        ArrayList<Sheet422CoreModel> sheet422Models = Sheet422Controller.getRecentInstancesCore(ExcelConfig.daysRecentInstances);
        if(sheet422Models == null || sheet422Models.isEmpty()){
            return;
        }
        if(latestDate != null){

            for(Sheet422CoreModel sheet422Model : sheet422Models){
                if(sheet422Model.getDate().getTime() > latestDate.getTime()){
                    Sheet422CorePDM sheet422CorePDM = new Sheet422CorePDM();
                    fillCorePdmFromModel(sheet422CorePDM, sheet422Model);
                    Sheet422Filler.fill(sheet422CorePDM, FileDestination.Core);
                }
            }
        }else{
            for(Sheet422CoreModel sheet422Model : sheet422Models){
                Sheet422CorePDM sheet422CorePDM = new Sheet422CorePDM();
                fillCorePdmFromModel(sheet422CorePDM, sheet422Model);
                Sheet422Filler.fill(sheet422CorePDM, FileDestination.Core);
            }
        }
    }

    private static void fillPersonalPdmFromModel(Sheet422PersonalPDM pdm, Sheet422PersonalModel model){
        pdm.setDate(model.getDate());
        pdm.setOrder1(null);
        pdm.setProvince(null);
        pdm.setTsName2(model.getName2());
        pdm.setTsTotalSpace2(model.getTotalSpace2());
        pdm.setTsUsage2(model.getUsage2());
        pdm.setTsUsedSpace2(model.getUsedOrRemainSpace2());
    }

    private static void fillCorePdmFromModel(Sheet422CorePDM pdm, Sheet422CoreModel model){
        fillPersonalPdmFromModel(pdm, model);
        pdm.setTsName3(model.getName3());
        pdm.setTsTotalSpace3(model.getTotalSpace3());
        pdm.setTsUsage3(model.getUsage3());
        pdm.setTsUsedSpace3(model.getUsedOrRemainSpace3());

        pdm.setTsName4(model.getName4());
        pdm.setTsTotalSpace4(model.getTotalSpace4());
        pdm.setTsUsage4(model.getUsage4());
        pdm.setTsUsedSpace4(model.getUsedOrRemainSpace4());
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
        Sheet sheet422 = wb.getSheet(ExcelConfig.SheetName422);
        if(sheet422 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName422);
        }

        int index = ExcelUtils.getLastRow(sheet422
                , ExcelConfig.Sheet422RecordStartRow
                , ExcelConfig.Sheet422InspectTimeCellIndex);

        if(index <= ExcelConfig.Sheet422RecordStartRow) return null;

        Row row = ExcelUtils.getRow(sheet422, index);
        return ExcelUtils.getCell(row, ExcelConfig.Sheet422InspectTimeCellIndex).getDateCellValue();
    }
}
