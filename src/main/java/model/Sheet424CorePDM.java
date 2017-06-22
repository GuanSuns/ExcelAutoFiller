package model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet424CorePDM extends Sheet424PersonalPDM {
    private Timestamp collectTime4;
    private String tempStatus4;

    private Timestamp collectTime5;
    private String tempStatus5;

    public Sheet424CorePDM() {
        super();
        this.collectTime4 = null;
        this.tempStatus4 = "";
        this.collectTime5 = null;
        this.tempStatus5 = "";
    }

    public Sheet424CorePDM(Long order1, String batch, String province
            , Timestamp collectTime2, String tempStatus2
            , Timestamp collectTime3, String tempStatus3
            , Timestamp collectTime4, String tempStatus4
            , Timestamp collectTime5, String tempStatus5) {
        super(order1, batch, province, collectTime2, tempStatus2
                , collectTime3, tempStatus3);
        this.collectTime4 = collectTime4;
        this.tempStatus4 = tempStatus4;
        this.collectTime5 = collectTime5;
        this.tempStatus5 = tempStatus5;
    }

    public Timestamp getCollectTime4() {
        return collectTime4;
    }

    public String getTempStatus4() {
        return tempStatus4;
    }

    public Timestamp getCollectTime5() {
        return collectTime5;
    }

    public String getTempStatus5() {
        return tempStatus5;
    }

    public void setCollectTime4(Timestamp collectTime4) {
        this.collectTime4 = collectTime4;
    }

    public void setTempStatus4(String tempStatus4) {
        this.tempStatus4 = tempStatus4;
    }

    public void setCollectTime5(Timestamp collectTime5) {
        this.collectTime5 = collectTime5;
    }

    public void setTempStatus5(String tempStatus5) {
        this.tempStatus5 = tempStatus5;
    }
}
