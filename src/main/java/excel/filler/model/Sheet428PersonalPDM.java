package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 7/3/2017.
 */
public class Sheet428PersonalPDM {
    protected Timestamp date;

    protected Long order;
    protected String batch;
    protected String province;

    protected String status1;
    protected String status2;
    protected String status3;
    protected String status4;

    public Sheet428PersonalPDM() {
        this.date = null;
        this.order = 0L;
        this.batch = "";
        this.province = "";
        this.status1 = "";
        this.status2 = "";
        this.status3 = "";
        this.status4 = "";
    }

    public Sheet428PersonalPDM(Timestamp date, Long order, String batch, String province
            , String status1, String status2, String status3, String status4) {
        this.date = date;
        this.order = order;
        this.batch = batch;
        this.province = province;
        this.status1 = status1;
        this.status2 = status2;
        this.status3 = status3;
        this.status4 = status4;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getStatus3() {
        return status3;
    }

    public void setStatus3(String status3) {
        this.status3 = status3;
    }

    public String getStatus4() {
        return status4;
    }

    public void setStatus4(String status4) {
        this.status4 = status4;
    }
}
