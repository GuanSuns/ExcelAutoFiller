package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 7/4/2017.
 */
public class Sheet426PersonalPDM {
    protected Timestamp date;
    protected Long order1;
    protected String batch;
    protected String province;

    protected String error2;
    protected String log20;
    protected String log21;

    public Sheet426PersonalPDM() {
        this.date = null;
        this.order1 = 0L;
        this.batch = "";
        this.province = "";

        this.error2 = "无";
        this.log20 = "";
        this.log21 = "";
    }

    public Sheet426PersonalPDM(Timestamp date, Long order1
            , String batch, String province, String error2
            , String log20, String log21) {
        this.date = date;
        this.order1 = order1;
        this.batch = batch;
        this.province = province;
        this.error2 = error2;
        this.log20 = log20;
        this.log21 = log21;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getOrder1() {
        return order1;
    }

    public void setOrder1(Long order1) {
        this.order1 = order1;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getError2() {
        return error2;
    }

    public void setError2(String error2) {
        this.error2 = error2;
    }

    public String getLog20() {
        return log20;
    }

    public void setLog20(String log20) {
        this.log20 = log20;
    }

    public String getLog21() {
        return log21;
    }

    public void setLog21(String log21) {
        this.log21 = log21;
    }
}
