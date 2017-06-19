import config.ExcelConfig;
import config.FileDestination;
import model.Sheet411PDM;
import sheet.filler.Sheet411Filler;

public class Main {

    public static void main(String[] args) {
        try{
            Sheet411Filler.fill(new Sheet411PDM(), FileDestination.Personal);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
