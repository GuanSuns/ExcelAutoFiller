import excel.filler.generator.*;
import org.junit.Test;
import org.suns.database.utils.config.DBConfig;

/**
 * Created by guanl on 7/18/2017.
 */
public class TestAutoFill {

    @Test
    public void testAutoFill(){
        try{
            DBConfig.setConfigToMySQL();

            Sheet411Generator.generateCore();
            Sheet411Generator.generatePersonal();
            Sheet421Generator.generateCore();
            Sheet421Generator.generatePersonal();
            Sheet422Generator.generateCore();
            Sheet422Generator.generatePersonal();
            Sheet423Generator.generateCore();
            Sheet423Generator.generatePersonal();
            Sheet424Generator.generateCore();
            Sheet424Generator.generatePersonal();
            Sheet429Generator.generateCore();
            Sheet429Generator.generatePersonal();
            Sheet428Generator.generateCore();
            Sheet428Generator.generatePersonal();
            Sheet426Generator.generatePersonal();
            Sheet426Generator.generateCore();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
