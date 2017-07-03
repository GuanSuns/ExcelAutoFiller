package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet428CorePDM extends Sheet428PersonalPDM {
    private String status5;

    public Sheet428CorePDM() {
        this.status5 = "";
    }

    public Sheet428CorePDM(Timestamp date, Long order, String batch
            , String province, String status1, String status2
            , String status3, String status4, String status5) {
        super(date, order, batch, province, status1, status2, status3, status4);
        this.status5 = status5;
    }

    public String getStatus5() {
        return status5;
    }

    public void setStatus5(String status5) {
        this.status5 = status5;
    }
}
