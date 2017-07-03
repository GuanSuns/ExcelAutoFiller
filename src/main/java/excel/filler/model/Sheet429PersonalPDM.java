package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/23/2017.
 */
public class Sheet429PersonalPDM {
    protected Timestamp date;
    protected Long order;
    protected String batch;
    protected String province;

    protected String heartBeat1;

    public Sheet429PersonalPDM() {
        this.order = 0L;
        this.batch = "";
        this.province = "";
        this.heartBeat1 = "";
    }

    public Sheet429PersonalPDM(Timestamp date, Long order
            , String batch, String province, String heartBeat1) {
        this.date = date;
        this.order = order;
        this.batch = batch;
        this.province = province;
        this.heartBeat1 = heartBeat1;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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

    public String getHeartBeat1() {
        return heartBeat1;
    }

    public void setHeartBeat1(String heartBeat1) {
        this.heartBeat1 = heartBeat1;
    }
}
