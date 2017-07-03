package excel.filler.generator;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.filler.Sheet423Filler;
import excel.filler.model.Sheet423CorePDM;
import excel.filler.model.Sheet423PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.suns.database.utils.controller.Sheet423Controller;
import org.suns.database.utils.model.Sheet423CoreModel;
import org.suns.database.utils.model.Sheet423PersonalModel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet423Generator {

    public static void generatePersonal() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet423PersonalModel> sheet423Models = Sheet423Controller.getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);
        if(sheet423Models == null || sheet423Models.isEmpty()){
            return;
        }

        if(latestDate != null){

            for(Sheet423PersonalModel sheet423Model : sheet423Models){
                if(sheet423Model.getDate().getTime() > latestDate.getTime()){
                    Sheet423PersonalPDM sheet423PersonalPDM = new Sheet423PersonalPDM();
                    fillPersonalPdmFromModel(sheet423PersonalPDM, sheet423Model);
                    Sheet423Filler.fill(sheet423PersonalPDM, FileDestination.Personal);
                }
            }
        }else{
            for(Sheet423PersonalModel sheet423Model : sheet423Models){
                Sheet423PersonalPDM sheet423PersonalPDM = new Sheet423PersonalPDM();
                fillPersonalPdmFromModel(sheet423PersonalPDM, sheet423Model);
                Sheet423Filler.fill(sheet423PersonalPDM, FileDestination.Personal);
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

        ArrayList<Sheet423CoreModel> sheet423Models = Sheet423Controller.getRecentInstancesCore(ExcelConfig.daysRecentInstances);
        if(sheet423Models == null || sheet423Models.isEmpty()){
            return;
        }

        if(latestDate != null){

            for(Sheet423CoreModel sheet423Model : sheet423Models){
                if(sheet423Model.getDate().getTime() > latestDate.getTime()){
                    Sheet423CorePDM sheet423CorePDM = new Sheet423CorePDM();
                    fillCorePdmFromModel(sheet423CorePDM, sheet423Model);
                    Sheet423Filler.fill(sheet423CorePDM, FileDestination.Core);
                }
            }
        }else{
            for(Sheet423CoreModel sheet423Model : sheet423Models){
                Sheet423CorePDM sheet423CorePDM = new Sheet423CorePDM();
                fillCorePdmFromModel(sheet423CorePDM, sheet423Model);
                Sheet423Filler.fill(sheet423CorePDM, FileDestination.Core);
            }
        }
    }

    private static void fillPersonalPdmFromModel(Sheet423PersonalPDM pdm, Sheet423PersonalModel model){
        pdm.setDate(model.getDate());
        pdm.setOrder1(null);
        pdm.setProvince(null);
        pdm.setAsmName2(model.getAsmName2());
        pdm.setTotalSpace2(model.getTotalSpace2());
        pdm.setUsage2(model.getUsage2());
        pdm.setRemainSpace2(model.getRemainSpace2());
    }

    private static void fillCorePdmFromModel(Sheet423CorePDM pdm, Sheet423CoreModel model){
        fillPersonalPdmFromModel(pdm, model);
        pdm.setAsmName3(model.getAsmName3());
        pdm.setTotalSpace3(model.getTotalSpace3());
        pdm.setUsage3(model.getUsage3());
        pdm.setRemainSpace3(model.getRemainSpace3());

        pdm.setAsmName4(model.getAsmName4());
        pdm.setTotalSpace4(model.getTotalSpace4());
        pdm.setUsage4(model.getUsage4());
        pdm.setRemainSpace4(model.getRemainSpace4());
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
        Sheet sheet423 = wb.getSheet(ExcelConfig.SheetName423);
        if(sheet423 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName423);
        }

        int index = ExcelUtils.getLastRow(sheet423
                , ExcelConfig.Sheet423RecordStartRow
                , ExcelConfig.Sheet423TimeCellIndex);

        if(index <= ExcelConfig.Sheet423RecordStartRow) return null;

        Row row = ExcelUtils.getRow(sheet423, index);
        return ExcelUtils.getCell(row, ExcelConfig.Sheet423TimeCellIndex).getDateCellValue();
    }
}
