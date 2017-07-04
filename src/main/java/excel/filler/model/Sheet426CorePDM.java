package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 7/4/2017.
 */
public class Sheet426CorePDM extends Sheet426PersonalPDM {

    private String error3;
    private String log3;

    private String error4;
    private String log40;
    private String log41;

    public Sheet426CorePDM() {
        super();
        this.error3 = "无";
        this.log3 = "";

        this.error4 = "无";
        this.log40 = "";
        this.log41 = "";
    }

    public Sheet426CorePDM(Timestamp date, Long order1, String batch
            , String province, String error2, String log20, String log21
            , String error3, String log3, String error4
            , String log40, String log41) {
        super(date, order1, batch, province, error2, log20, log21);
        this.error3 = error3;
        this.log3 = log3;
        this.error4 = error4;
        this.log40 = log40;
        this.log41 = log41;
    }

    public String getError3() {
        return error3;
    }

    public void setError3(String error3) {
        this.error3 = error3;
    }

    public String getLog3() {
        return log3;
    }

    public void setLog3(String log3) {
        this.log3 = log3;
    }

    public String getError4() {
        return error4;
    }

    public void setError4(String error4) {
        this.error4 = error4;
    }

    public String getLog40() {
        return log40;
    }

    public void setLog40(String log40) {
        this.log40 = log40;
    }

    public String getLog41() {
        return log41;
    }

    public void setLog41(String log41) {
        this.log41 = log41;
    }
}
