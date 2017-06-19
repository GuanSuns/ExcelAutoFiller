import config.ExcelConfig;
import config.FileDestination;
import model.Sheet411PDM;
import sheet.filler.Sheet411Filler;

public class Main {

    public static void main(String[] args) {
        try{
            Sheet411PDM sheet411PDM = new Sheet411PDM(null, 1L
                    , 0.112F, null, null
                    , 1F, 0.1233F, 1F
                    , 1F, 1F, 1F, 1F
                    , 1F, 1F, 1F, 1F);

            Sheet411Filler.fill(sheet411PDM, FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
