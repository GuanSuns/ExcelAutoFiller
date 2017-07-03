package excel.filler.generator;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.filler.Sheet421Filler;
import excel.filler.model.Sheet421CorePDM;
import excel.filler.model.Sheet421PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.suns.database.utils.config.DBConfig;
import org.suns.database.utils.controller.Sheet421Controller;
import org.suns.database.utils.model.Sheet421CoreModel;
import org.suns.database.utils.model.Sheet421PersonalModel;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet421Generator {

    public static void generatePersonal() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet421PersonalModel> sheet421Models = Sheet421Controller.getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);
        if(latestDate != null){

            for(Sheet421PersonalModel sheet421Model : sheet421Models){
                if(sheet421Model.getDate().getTime() > latestDate.getTime()){
                    Sheet421PersonalPDM sheet421PersonalPDM = new Sheet421PersonalPDM();
                    fillPersonalPdmFromModel(sheet421PersonalPDM, sheet421Model);
                    Sheet421Filler.fill(sheet421PersonalPDM, FileDestination.Personal);
                }
            }
        }else{
            for(Sheet421PersonalModel sheet421Model : sheet421Models){
                Sheet421PersonalPDM sheet421PersonalPDM = new Sheet421PersonalPDM();
                fillPersonalPdmFromModel(sheet421PersonalPDM, sheet421Model);
                Sheet421Filler.fill(sheet421PersonalPDM, FileDestination.Personal);
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

        ArrayList<Sheet421CoreModel> sheet421Models = Sheet421Controller.getRecentInstancesCore(ExcelConfig.daysRecentInstances);
        if(latestDate != null){

            for(Sheet421CoreModel sheet421Model : sheet421Models){
                if(sheet421Model.getDate().getTime() > latestDate.getTime()){
                    Sheet421CorePDM sheet421CorePDM = new Sheet421CorePDM();
                    fillCorePdmFromModel(sheet421CorePDM, sheet421Model);
                    Sheet421Filler.fill(sheet421CorePDM, FileDestination.Core);
                }
            }
        }else{
            for(Sheet421CoreModel sheet421Model : sheet421Models){
                Sheet421CorePDM sheet421CorePDM = new Sheet421CorePDM();
                fillCorePdmFromModel(sheet421CorePDM, sheet421Model);
                Sheet421Filler.fill(sheet421CorePDM, FileDestination.Core);
            }
        }
    }

    private static void fillPersonalPdmFromModel(Sheet421PersonalPDM pdm, Sheet421PersonalModel model){
        pdm.setDate(model.getDate());
        pdm.setOrder1(null);
        pdm.setProvince(null);
        pdm.setUsage2(model.getUsage2());
        pdm.setU01Usage2(model.getU01Usage2());
        pdm.setGoldUsage2(model.getGoldUsage2());
        pdm.setUsage3(model.getUsage3());
        pdm.setU01Usage3(model.getU01Usage3());
        pdm.setGoldUsage3((float)DBConfig.getDefaultNumericNullValue());
    }

    private static void fillCorePdmFromModel(Sheet421CorePDM pdm, Sheet421CoreModel model){
        fillPersonalPdmFromModel(pdm, model);
        pdm.setUsage4(model.getUsage4());
        pdm.setU01Usage4(model.getU01Usage4());
        pdm.setGoldUsage4(model.getGoldUsage4());
        pdm.setUsage5(model.getUsage5());
        pdm.setU01Usage5(model.getU01Usage5());
        pdm.setGoldUsage5((float)DBConfig.getDefaultNumericNullValue());
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
        Sheet sheet421 = wb.getSheet(ExcelConfig.SheetName421);
        if(sheet421 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName421);
        }

        int index = ExcelUtils.getLastRow(sheet421
                , ExcelConfig.Sheet421RecordStartRow
                , ExcelConfig.Sheet421TimeCellIndex);

        if(index <= ExcelConfig.Sheet421RecordStartRow) return null;

        Row row = ExcelUtils.getRow(sheet421, index);
        return ExcelUtils.getCell(row, ExcelConfig.Sheet421TimeCellIndex).getDateCellValue();
    }
}
