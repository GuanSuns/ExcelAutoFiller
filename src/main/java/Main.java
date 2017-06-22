import config.FileDestination;
import model.*;
import sheet.filler.*;

public class Main {

    public static void main(String[] args) {
        try{
            Sheet421CorePDM sheet421PersonalPDM = new Sheet421CorePDM(null, null,null, null
                    , 0.23F, 0.23f, 0.23F
                    , 0.34F, 0.4554F, null
                    , 0.56F, 0.5432F, null,  0.34F, 0.4554F, null);

            Sheet422CorePDM sheet422PersonalPDM = new Sheet422CorePDM(null, null, null
                    , "ts name2", "324.32", "234.23", "85"
                    , "ts name3", "53.32", "265.23", "23"
                    , "ts name3", "53.32", "265.23", "23"
                    , "ts name3", "53.32", "265.23", "23");

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

            Sheet424PersonalPDM sheet424PersonalPDM = new Sheet424PersonalPDM(null, null, null
                    , null,"status 2"
                    , null, "status 3");

            Sheet424CorePDM sheet424CorePDM = new Sheet424CorePDM(null, null, null
                    , null,"status 2"
                    , null, "status 3"
                    , null, "status 4"
                    , null, "status 5");

            Sheet424Filler.fill(sheet424CorePDM, FileDestination.Core);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
