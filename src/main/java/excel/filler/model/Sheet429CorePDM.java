package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/23/2017.
 */
public class Sheet429CorePDM extends Sheet429PersonalPDM{
    private String heartBeat2;
    private String heartBeat3;

    public Sheet429CorePDM() {
        this.heartBeat2 = "";
        this.heartBeat3 = "";
    }

    public Sheet429CorePDM(Timestamp date, Long order, String batch
            , String province, String heartBeat1
            , String heartBeat2, String heartBeat3) {
        super(date, order, batch, province, heartBeat1);
        this.heartBeat2 = heartBeat2;
        this.heartBeat3 = heartBeat3;
    }

    public String getHeartBeat2() {
        return heartBeat2;
    }

    public void setHeartBeat2(String heartBeat2) {
        this.heartBeat2 = heartBeat2;
    }

    public String getHeartBeat3() {
        return heartBeat3;
    }

    public void setHeartBeat3(String heartBeat3) {
        this.heartBeat3 = heartBeat3;
    }
}
