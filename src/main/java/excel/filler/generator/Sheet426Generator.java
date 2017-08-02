package excel.filler.generator;

import excel.filler.config.ExcelConfig;
import excel.filler.config.FileDestination;
import excel.filler.config.POIConfig;
import excel.filler.filler.Sheet426Filler;
import excel.filler.model.Sheet426CorePDM;
import excel.filler.model.Sheet426PersonalPDM;
import excel.filler.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.suns.database.utils.controller.Sheet426Controller;
import org.suns.database.utils.model.Sheet426CoreModel;
import org.suns.database.utils.model.Sheet426PersonalModel;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guanl on 7/4/2017.
 */
public class Sheet426Generator {
    public static void generatePersonal() throws Exception{
        Date latestDate = null;
        try{
            latestDate = getLatestTime(FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        ArrayList<Sheet426PersonalModel> sheet426Models =
                Sheet426Controller.getRecentInstancesPersonal(ExcelConfig.daysRecentInstances);
        if(sheet426Models == null || sheet426Models.isEmpty()){
            return;
        }

        if(latestDate != null){
            for(Sheet426PersonalModel sheet426Model : sheet426Models){
                if(sheet426Model.getDate().getTime() > latestDate.getTime()){
                    Sheet426PersonalPDM sheet426PersonalPDM = new Sheet426PersonalPDM();
                    fillPersonalPdmFromModel(sheet426PersonalPDM, sheet426Model);
                    Sheet426Filler.fill(sheet426PersonalPDM, FileDestination.Personal);
                }
            }
        }else{
            for(Sheet426PersonalModel sheet426Model : sheet426Models){
                Sheet426PersonalPDM sheet426PersonalPDM = new Sheet426PersonalPDM();
                fillPersonalPdmFromModel(sheet426PersonalPDM, sheet426Model);
                Sheet426Filler.fill(sheet426PersonalPDM, FileDestination.Personal);
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

        ArrayList<Sheet426CoreModel> sheet426Models =
                Sheet426Controller.getRecentInstancesCore(ExcelConfig.daysRecentInstances);
        if(sheet426Models == null || sheet426Models.isEmpty()){
            return;
        }

        if(latestDate != null){
            for(Sheet426CoreModel sheet426Model : sheet426Models){
                if(sheet426Model.getDate().getTime() > latestDate.getTime()){
                    Sheet426CorePDM sheet426CorePDM = new Sheet426CorePDM();
                    fillCorePdmFromModel(sheet426CorePDM, sheet426Model);
                    Sheet426Filler.fill(sheet426CorePDM, FileDestination.Core);
                }
            }
        }else{
            for(Sheet426CoreModel sheet426Model : sheet426Models){
                Sheet426CorePDM sheet426CorePDM = new Sheet426CorePDM();
                fillCorePdmFromModel(sheet426CorePDM, sheet426Model);
                Sheet426Filler.fill(sheet426CorePDM, FileDestination.Core);
            }
        }
    }

    private static void fillPersonalPdmFromModel(Sheet426PersonalPDM pdm, Sheet426PersonalModel model){
        pdm.setDate(model.getDate());
        pdm.setBatch("");
        pdm.setOrder1(null);
        pdm.setProvince(null);

        if(model.getErrorInfo20() == 1 || model.getErrorInfo21() == 1){
            pdm.setError2(ExcelConfig.Sheet426ErrorString);
        }else{
            pdm.setError2(ExcelConfig.Sheet426NoErrorString);
        }

        if(model.getLog20() == null){
            pdm.setLog20("");
        }else{
            pdm.setLog20(model.getLog20().replaceAll("\n", "\r\n"));
        }

        if(model.getLog21() == null){
            pdm.setLog21("");
        }else{
            pdm.setLog21(model.getLog21().replaceAll("\n", "\r\n"));
        }
    }

    private static void fillCorePdmFromModel(Sheet426CorePDM pdm, Sheet426CoreModel model){
        fillPersonalPdmFromModel(pdm, model);

        if(model.getErrorInfo3() == 1){
            pdm.setError3(ExcelConfig.Sheet426ErrorString);
        }else{
            pdm.setError3(ExcelConfig.Sheet426NoErrorString);
        }

        if(model.getLog3() == null){
            pdm.setLog3("");
        }else{
            pdm.setLog3(model.getLog3().replaceAll("\n", "\r\n"));
        }

        if(model.getErrorInfo40() == 1 || model.getErrorInfo41() == 1){
            pdm.setError4(ExcelConfig.Sheet426ErrorString);
        }else{
            pdm.setError4(ExcelConfig.Sheet426NoErrorString);
        }

        if(model.getLog40() == null){
            pdm.setLog40("");
        }else{
            pdm.setLog40(model.getLog40().replaceAll("\n", "\r\n"));
        }

        if(model.getLog41() == null){
            pdm.setLog41("");
        }else{
            pdm.setLog41(model.getLog41().replaceAll("\n", "\r\n"));
        }

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
        Sheet sheet426 = wb.getSheet(ExcelConfig.SheetName426);
        if(sheet426 == null){
            throw  new Exception("No corresponding sheet "
                    + ExcelConfig.SheetName426);
        }

        int index = ExcelUtils.getLastRow(sheet426
                , ExcelConfig.Sheet426RecordStartRow
                , ExcelConfig.Sheet426TimeCellIndex);

        if(index <= ExcelConfig.Sheet426RecordStartRow) return null;

        Row row = ExcelUtils.getRow(sheet426, index);
        return ExcelUtils.getCell(row, ExcelConfig.Sheet426TimeCellIndex).getDateCellValue();
    }
}
