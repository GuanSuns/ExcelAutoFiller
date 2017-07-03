package excel.filler.generator;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.filler.Sheet411Filler;
import excel.filler.model.Sheet411CorePDM;
import excel.filler.model.Sheet411PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.suns.database.utils.controller.Sheet411Controller;
import org.suns.database.utils.model.Sheet411CoreModel;
import org.suns.database.utils.model.Sheet411PersonalModel;
import org.suns.database.utils.model.Sheet421PersonalModel;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guanl on 7/2/2017.
 */
public class Sheet411Generator {
    public static void generatePersonal() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet411PersonalModel> sheet411Models = Sheet411Controller.getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);
        if(latestDate != null){
            for(Sheet411PersonalModel sheet411Model : sheet411Models){
                if(sheet411Model.getDate().getTime() > latestDate.getTime()){
                    Sheet411PersonalPDM sheet411PersonalPDM = new Sheet411PersonalPDM();
                    fillPersonalPdmFromModel(sheet411PersonalPDM, sheet411Model);
                    Sheet411Filler.fill(sheet411PersonalPDM, FileDestination.Personal);
                }
            }
        }else{
            for(Sheet411PersonalModel sheet411Model : sheet411Models){
                Sheet411PersonalPDM sheet411PersonalPDM = new Sheet411PersonalPDM();
                fillPersonalPdmFromModel(sheet411PersonalPDM, sheet411Model);
                Sheet411Filler.fill(sheet411PersonalPDM, FileDestination.Personal);
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

        ArrayList<Sheet411CoreModel> sheet411Models = Sheet411Controller.getRecentInstancesCore(ExcelConfig.daysRecentInstances);
        if(latestDate != null){

            for(Sheet411CoreModel sheet411Model : sheet411Models){
                if(sheet411Model.getDate().getTime() > latestDate.getTime()){
                    Sheet411CorePDM sheet411CorePDM = new Sheet411CorePDM();
                    fillCorePdmFromModel(sheet411CorePDM, sheet411Model);
                    Sheet411Filler.fill(sheet411CorePDM, FileDestination.Core);
                }
            }
        }else{
            for(Sheet411CoreModel sheet411Model : sheet411Models){
                Sheet411CorePDM sheet411CorePDM = new Sheet411CorePDM();
                fillCorePdmFromModel(sheet411CorePDM, sheet411Model);
                Sheet411Filler.fill(sheet411CorePDM, FileDestination.Core);
            }
        }
    }
    
    private static void fillPersonalPdmFromModel(Sheet411PersonalPDM pdm, Sheet411PersonalModel model){
        pdm.setDate(model.getDate());
        pdm.setOrder1(null);
        pdm.setProvince(null);
        pdm.setUsage2(model.getUsage2());
        pdm.setWeblogicUsage2(model.getWeblogicUsage2());
        pdm.setUsage3(model.getUsage3());
        pdm.setWeblogicUsage3(model.getWeblogicUsage3());
        pdm.setUsage4(model.getUsage4());
        pdm.setWeblogicUsage4(model.getWeblogicUsage4());
        pdm.setUsage5(model.getUsage5());
        pdm.setWeblogicUsage5(model.getWeblogicUsage5());
        pdm.setUsage6(null);
        pdm.setWeblogicUsage6(null);
        pdm.setUsage7(null);
        pdm.setWeblogicUsage7(null);
    }
    
    private static void fillCorePdmFromModel(Sheet411CorePDM pdm, Sheet411CoreModel model){
        fillPersonalPdmFromModel(pdm, model);
        pdm.setUsage6(model.getUsage6());
        pdm.setWeblogicUsage6(model.getWeblogicUsage6());
        pdm.setUsage8(model.getUsage8());
        pdm.setWeblogicUsage8(model.getUsage8());
    }

    private static Date getLatestTime(FileDestination fileDestination) throws Exception{

        File destFile;
        if(fileDestination.equals(FileDestination.Personal)){
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.PersonalSystemFile);
        }else{
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.CoreSystemFile);
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
        Sheet sheet411 = wb.getSheet(ExcelConfig.SheetName411);
        if(sheet411 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName411);
        }

        int index = ExcelUtils.getLastRow(sheet411
                , ExcelConfig.Sheet411RecordStartRow
                , ExcelConfig.Sheet411TimeCellIndex);

        if(index <= ExcelConfig.Sheet411RecordStartRow) return null;

        Row row = ExcelUtils.getRow(sheet411, index);
        return ExcelUtils.getCell(row, ExcelConfig.Sheet411TimeCellIndex).getDateCellValue();
    }
}
