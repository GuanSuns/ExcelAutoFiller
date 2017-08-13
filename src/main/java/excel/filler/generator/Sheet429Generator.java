package excel.filler.generator;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.filler.Sheet429Filler;
import excel.filler.model.Sheet429CorePDM;
import excel.filler.model.Sheet429PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.suns.database.utils.controller.Sheet429Controller;
import org.suns.database.utils.model.Sheet429CoreModel;
import org.suns.database.utils.model.Sheet429PersonalModel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet429Generator {
    public static void generatePersonal() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet429PersonalModel> sheet429Models =
                Sheet429Controller.getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);
        if(sheet429Models == null || sheet429Models.isEmpty()){
            return;
        }

        if(latestDate != null){

            for(Sheet429PersonalModel sheet429Model : sheet429Models){
                if(sheet429Model.getInspectTime().getTime() > latestDate.getTime()){
                    Sheet429PersonalPDM sheet429PersonalPDM = new Sheet429PersonalPDM();
                    fillPersonalPdmFromModel(sheet429PersonalPDM, sheet429Model);
                    Sheet429Filler.fill(sheet429PersonalPDM, FileDestination.Personal);
                }
            }
        }else{
            for(Sheet429PersonalModel sheet429Model : sheet429Models){
                Sheet429PersonalPDM sheet429PersonalPDM = new Sheet429PersonalPDM();
                fillPersonalPdmFromModel(sheet429PersonalPDM, sheet429Model);
                Sheet429Filler.fill(sheet429PersonalPDM, FileDestination.Personal);
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

        ArrayList<Sheet429CoreModel> sheet429Models =
                Sheet429Controller.getRecentInstancesCore(ExcelConfig.daysRecentInstances);
        if(sheet429Models == null || sheet429Models.isEmpty()){
            return;
        }

        if(latestDate != null){
            for(Sheet429CoreModel sheet429Model : sheet429Models){
                if(sheet429Model.getInspectTime().getTime() > latestDate.getTime()){
                    Sheet429CorePDM sheet429CorePDM = new Sheet429CorePDM();
                    fillCorePdmFromModel(sheet429CorePDM, sheet429Model);
                    Sheet429Filler.fill(sheet429CorePDM, FileDestination.Core);
                }
            }
        }else{
            for(Sheet429CoreModel sheet429Model : sheet429Models){
                Sheet429CorePDM sheet429CorePDM = new Sheet429CorePDM();
                fillCorePdmFromModel(sheet429CorePDM, sheet429Model);
                Sheet429Filler.fill(sheet429CorePDM, FileDestination.Core);
            }
        }
    }

    private static void fillPersonalPdmFromModel(Sheet429PersonalPDM pdm, Sheet429PersonalModel model){
        pdm.setDate(model.getInspectTime());
        pdm.setBatch("");
        pdm.setOrder(null);
        pdm.setProvince(null);
        pdm.setHeartBeat1(model.getHeartBeat1());
    }

    private static void fillCorePdmFromModel(Sheet429CorePDM pdm, Sheet429CoreModel model){
        fillPersonalPdmFromModel(pdm, model);

        pdm.setHeartBeat2(model.getHeartBeat2());
        pdm.setHeartBeat3(model.getHeartBeat3());
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
        Sheet sheet429 = wb.getSheet(ExcelConfig.SheetName429);
        if(sheet429 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName429);
        }

        int index = ExcelUtils.getLastRow(sheet429
                , ExcelConfig.Sheet429RecordStartRow
                , ExcelConfig.Sheet429TimeCellIndex);

        if(index <= ExcelConfig.Sheet429RecordStartRow) return null;

        Row row = ExcelUtils.getRow(sheet429, index);
        return ExcelUtils.getCell(row, ExcelConfig.Sheet429TimeCellIndex).getDateCellValue();
    }
}
