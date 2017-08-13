package excel.filler.generator;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.filler.Sheet428Filler;
import excel.filler.model.Sheet428CorePDM;
import excel.filler.model.Sheet428PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.suns.database.utils.controller.Sheet428Controller;
import org.suns.database.utils.model.Sheet428CoreModel;
import org.suns.database.utils.model.Sheet428PersonalModel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet428Generator {
    public static void generatePersonal() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet428PersonalModel> sheet428Models =
                Sheet428Controller.getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);
        if(sheet428Models == null || sheet428Models.isEmpty()){
            return;
        }

        if(latestDate != null){

            for(Sheet428PersonalModel sheet428Model : sheet428Models){
                if(sheet428Model.getDate().getTime() > latestDate.getTime()){
                    Sheet428PersonalPDM sheet428PersonalPDM = new Sheet428PersonalPDM();
                    fillPersonalPdmFromModel(sheet428PersonalPDM, sheet428Model);
                    Sheet428Filler.fill(sheet428PersonalPDM, FileDestination.Personal);
                }
            }
        }else{
            for(Sheet428PersonalModel sheet428Model : sheet428Models){
                Sheet428PersonalPDM sheet428PersonalPDM = new Sheet428PersonalPDM();
                fillPersonalPdmFromModel(sheet428PersonalPDM, sheet428Model);
                Sheet428Filler.fill(sheet428PersonalPDM, FileDestination.Personal);
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

        ArrayList<Sheet428CoreModel> sheet428Models =
                Sheet428Controller.getRecentInstancesCore(ExcelConfig.daysRecentInstances);
        if(sheet428Models == null || sheet428Models.isEmpty()){
            return;
        }

        if(latestDate != null){
            for(Sheet428CoreModel sheet428Model : sheet428Models){
                if(sheet428Model.getDate().getTime() > latestDate.getTime()){
                    Sheet428CorePDM sheet428CorePDM = new Sheet428CorePDM();
                    fillCorePdmFromModel(sheet428CorePDM, sheet428Model);
                    Sheet428Filler.fill(sheet428CorePDM, FileDestination.Core);
                }
            }
        }else{
            for(Sheet428CoreModel sheet428Model : sheet428Models){
                Sheet428CorePDM sheet428CorePDM = new Sheet428CorePDM();
                fillCorePdmFromModel(sheet428CorePDM, sheet428Model);
                Sheet428Filler.fill(sheet428CorePDM, FileDestination.Core);
            }
        }
    }

    private static void fillPersonalPdmFromModel(Sheet428PersonalPDM pdm, Sheet428PersonalModel model){
        pdm.setDate(model.getDate());
        pdm.setBatch("");
        pdm.setOrder(null);
        pdm.setProvince(null);
        pdm.setStatus1(model.getStatus1());
        pdm.setStatus2(model.getStatus2());
        pdm.setStatus3(model.getStatus3());
        pdm.setStatus4(model.getStatus4());
    }

    private static void fillCorePdmFromModel(Sheet428CorePDM pdm, Sheet428CoreModel model){
        fillPersonalPdmFromModel(pdm, model);
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
        Sheet sheet428 = wb.getSheet(ExcelConfig.SheetName428);
        if(sheet428 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName428);
        }

        int index = ExcelUtils.getLastRow(sheet428
                , ExcelConfig.Sheet428RecordStartRow
                , ExcelConfig.Sheet428TimeCellIndex);

        if(index <= ExcelConfig.Sheet428RecordStartRow) return null;

        Row row = ExcelUtils.getRow(sheet428, index);
        return ExcelUtils.getCell(row, ExcelConfig.Sheet428TimeCellIndex).getDateCellValue();
    }
}
