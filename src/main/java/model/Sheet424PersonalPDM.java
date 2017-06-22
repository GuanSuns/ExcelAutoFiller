package model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet424PersonalPDM {
    private Long order1;
    private String batch;
    private String province;

    private Timestamp collectTime2;
    private String tempStatus2;

    private Timestamp collectTime3;
    private String tempStatus3;

    public Sheet424PersonalPDM() {
        this.order1 = 0L;
        this.batch = "";
        this.province = "广东";
        this.collectTime2 = null;
        this.tempStatus2 = "";
        this.collectTime3 = null;
        this.tempStatus3 = "";
    }

    public Sheet424PersonalPDM(Long order1, String batch, String province
            , Timestamp collectTime2, String tempStatus2
            , Timestamp collectTime3, String tempStatus3) {
        this.order1 = order1;
        this.batch = batch;
        this.province = province;
        this.collectTime2 = collectTime2;
        this.tempStatus2 = tempStatus2;
        this.collectTime3 = collectTime3;
        this.tempStatus3 = tempStatus3;
    }

    public Long getOrder1() {
        return order1;
    }

    public String getBatch() {
        return batch;
    }

    public String getProvince() {
        return province;
    }

    public Timestamp getCollectTime2() {
        return collectTime2;
    }

    public String getTempStatus2() {
        return tempStatus2;
    }

    public Timestamp getCollectTime3() {
        return collectTime3;
    }

    public String getTempStatus3() {
        return tempStatus3;
    }

    public void setOrder1(Long order1) {
        this.order1 = order1;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCollectTime2(Timestamp collectTime2) {
        this.collectTime2 = collectTime2;
    }

    public void setTempStatus2(String tempStatus2) {
        this.tempStatus2 = tempStatus2;
    }

    public void setCollectTime3(Timestamp collectTime3) {
        this.collectTime3 = collectTime3;
    }

    public void setTempStatus3(String tempStatus3) {
        this.tempStatus3 = tempStatus3;
    }
}
