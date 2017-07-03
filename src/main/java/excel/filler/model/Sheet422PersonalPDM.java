package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet422PersonalPDM {
    private Timestamp date;

    private Long order1;
    private String batch;
    private String province;

    private String tsName2;
    private Float tsTotalSpace2;
    private Float tsUsedSpace2;
    private Float tsUsage2;

    private String tsName3;
    private Float tsTotalSpace3;
    private Float tsUsedSpace3;
    private Float tsUsage3;

    public Sheet422PersonalPDM() {
        this.date = null;
        this.batch = "";
        this.order1 = 0L;
        this.province = "广东";
        this.tsName2 = "";
        this.tsTotalSpace2 = 0f;
        this.tsUsage2 = 0f;
        this.tsUsedSpace2 = 0f;

        this.tsName3 = "";
        this.tsTotalSpace3 = 0f;
        this.tsUsedSpace3 = 0f;
        this.tsUsage3 = 0f;
    }

    public Sheet422PersonalPDM(Timestamp date, Long order1, String batch, String province
            , String tsName2, Float tsTotalSpace2, Float tsUsedSpace2
            , Float tsUsage2, String tsName3, Float tsTotalSpace3,
                               Float tsUsedSpace3, Float tsUsage3) {
        this.date = date;
        this.order1 = order1;
        this.batch = batch;
        this.province = province;
        this.tsName2 = tsName2;
        this.tsTotalSpace2 = tsTotalSpace2;
        this.tsUsedSpace2 = tsUsedSpace2;
        this.tsUsage2 = tsUsage2;
        this.tsName3 = tsName3;
        this.tsTotalSpace3 = tsTotalSpace3;
        this.tsUsedSpace3 = tsUsedSpace3;
        this.tsUsage3 = tsUsage3;
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

    public String getTsName2() {
        return tsName2;
    }

    public void setTsName2(String tsName2) {
        this.tsName2 = tsName2;
    }

    public Float getTsTotalSpace2() {
        return tsTotalSpace2;
    }

    public void setTsTotalSpace2(Float tsTotalSpace2) {
        this.tsTotalSpace2 = tsTotalSpace2;
    }

    public Float getTsUsedSpace2() {
        return tsUsedSpace2;
    }

    public void setTsUsedSpace2(Float tsUsedSpace2) {
        this.tsUsedSpace2 = tsUsedSpace2;
    }

    public Float getTsUsage2() {
        return tsUsage2;
    }

    public void setTsUsage2(Float tsUsage2) {
        this.tsUsage2 = tsUsage2;
    }

    public String getTsName3() {
        return tsName3;
    }

    public void setTsName3(String tsName3) {
        this.tsName3 = tsName3;
    }

    public Float getTsTotalSpace3() {
        return tsTotalSpace3;
    }

    public void setTsTotalSpace3(Float tsTotalSpace3) {
        this.tsTotalSpace3 = tsTotalSpace3;
    }

    public Float getTsUsedSpace3() {
        return tsUsedSpace3;
    }

    public void setTsUsedSpace3(Float tsUsedSpace3) {
        this.tsUsedSpace3 = tsUsedSpace3;
    }

    public Float getTsUsage3() {
        return tsUsage3;
    }

    public void setTsUsage3(Float tsUsage3) {
        this.tsUsage3 = tsUsage3;
    }
}
