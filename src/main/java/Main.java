import excel.filler.config.FileDestination;
import excel.filler.generator.*;
import excel.filler.model.*;
import excel.filler.filler.*;
import org.suns.database.utils.controller.Sheet411Controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        try{
            Sheet421CorePDM sheet421PersonalPDM = new Sheet421CorePDM(null, null,null, null
                    , 0.23F, 0.23f, 0.23F
                    , 0.34F, 0.4554F, null
                    , 0.56F, 0.5432F, null,  0.34F, 0.4554F, null);


            Sheet423PersonalPDM sheet423PersonalPDM = new Sheet423PersonalPDM(null, null, ""
                    , null, "ASM1", 1024, 1022
                    , 5.23f, "ASM 2", 512
                    , 512, 10.2f);

            Sheet423CorePDM sheet423CorePDM = new Sheet423CorePDM(null, null, ""
                    , null, "ASM1", 1024, 1022
                    , 5.23f, "ASM 2", 512
                    , 512, 10.2f, "ASM 2", 512
                    , 512, 10.2f, "ASM 2", 512
                    , 512, 10.2f);

            Sheet424PersonalPDM sheet424PersonalPDM = new Sheet424PersonalPDM(null, null, null, null
                    , null,"status 2"
                    , null, "status 3");

            Sheet424CorePDM sheet424CorePDM = new Sheet424CorePDM(null, null, null, null
                    , null,"status 2"
                    , null, "status 3"
                    , null, "status 4"
                    , null, "status 5");


            //Sheet429Filler.fill( sheet429PersonalPDM, FileDestination.Personal);
            //Sheet411Generator.generateCore();
            //Sheet411Generator.generatePersonal();
            //Sheet421Generator.generateCore();
            //Sheet421Generator.generatePersonal();
            //Sheet422Generator.generateCore();
            //Sheet422Generator.generatePersonal();
            //Sheet423Generator.generateCore();
            //Sheet423Generator.generatePersonal();
            //Sheet424Generator.generateCore();
            //Sheet424Generator.generatePersonal();
            //Sheet429Generator.generateCore();
            //Sheet429Generator.generatePersonal();
            //Sheet428Generator.generateCore();
            //Sheet428Generator.generatePersonal();
            Sheet426Generator.generatePersonal();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
