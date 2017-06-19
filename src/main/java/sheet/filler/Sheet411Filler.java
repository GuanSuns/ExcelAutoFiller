package sheet.filler;

import config.ExcelConfig;
import config.FileDestination;
import config.POIConfig;
import model.Sheet411PDM;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import utils.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by guanl on 6/19/2017.
 */
public class Sheet411Filler {
    public static void fill(Sheet411PDM sheet411PDM
            , FileDestination destination) throws Exception{
        if(sheet411PDM == null || destination == null){
            throw new Exception("Invalid null arguments");
        }

        File destFile = null;
        if(destination.equals(FileDestination.Core)){
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.CoreSystemFile);
        }else{
            destFile = new File(POIConfig.RootDirectory
                    + POIConfig.PersonalSystemFile);
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

        System.out.println(sheet411.getLastRowNum());

        is.close();
        wb.close();
    }

    //Insert a new instance of data into the sheet
    private static void insert(Sheet sheet411
            , Sheet411PDM sheet411PDM
            , FileDestination destination) throws Exception{

        //Get the row index for the new data
        int index = sheet411.getLastRowNum() + 1;

        Row row = ExcelUtils.getRow(sheet411, index);

        ArrayList<Float> data = new ArrayList<>();
        



    }
}
