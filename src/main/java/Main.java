import config.FileDestination;
import model.Sheet411CorePDM;
import model.Sheet411PersonalPDM;
import model.Sheet421PersonalPDM;
import sheet.filler.Sheet411Filler;
import sheet.filler.Sheet421Filler;

public class Main {

    public static void main(String[] args) {
        try{
            Sheet421PersonalPDM sheet421PersonalPDM = new Sheet421PersonalPDM(null, null, null
                    , 0.23F, 0.23f, 0.23F
                    , 0.34F, 0.4554F, 0.112F
                    , 0.56F, 0.5432F, 0.0012F);

            Sheet421Filler.fill(sheet421PersonalPDM, FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
