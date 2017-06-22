package model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet423PersonalPDM {
    private Timestamp date;
    private Long order1;
    private String batchID;
    private String province;

    private String asmName2;
    private Integer totalSpace2;
    private Integer remainSpace2;
    private Float usage2;

    private String asmName3;
    private Integer totalSpace3;
    private Integer remainSpace3;
    private Float usage3;

    public Sheet423PersonalPDM() {
        this.date = null;
        this.order1 = 0L;
        this.batchID = "";
        this.province = "广东";

        this.asmName2 = "";
        this.totalSpace2 = 0;
        this.remainSpace2 = 0;
        this.usage2 = 0F;

        this.asmName3 = "";
        this.totalSpace3 = 0;
        this.remainSpace3 = 0;
        this.usage3 = 0F;
    }

    public Sheet423PersonalPDM(Timestamp date, Long order1, String batchID, String province
            , String asmName2, Integer totalSpace2, Integer remainSpace2, Float usage2
            , String asmName3, Integer totalSpace3, Integer remainSpace3, Float usage3) {
        this.date = date;
        this.order1 = order1;
        this.batchID = batchID;
        this.province = province;
        this.asmName2 = asmName2;
        this.totalSpace2 = totalSpace2;
        this.remainSpace2 = remainSpace2;
        this.usage2 = usage2;
        this.asmName3 = asmName3;
        this.totalSpace3 = totalSpace3;
        this.remainSpace3 = remainSpace3;
        this.usage3 = usage3;
    }

    public Timestamp getDate() {
        return date;
    }

    public Long getOrder1() {
        return order1;
    }

    public String getBatchID() {
        return batchID;
    }

    public String getProvince() {
        return province;
    }

    public String getAsmName2() {
        return asmName2;
    }

    public Integer getTotalSpace2() {
        return totalSpace2;
    }

    public Integer getRemainSpace2() {
        return remainSpace2;
    }

    public Float getUsage2() {
        return usage2;
    }

    public String getAsmName3() {
        return asmName3;
    }

    public Integer getTotalSpace3() {
        return totalSpace3;
    }

    public Integer getRemainSpace3() {
        return remainSpace3;
    }

    public Float getUsage3() {
        return usage3;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setOrder1(Long order1) {
        this.order1 = order1;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setAsmName2(String asmName2) {
        this.asmName2 = asmName2;
    }

    public void setTotalSpace2(Integer totalSpace2) {
        this.totalSpace2 = totalSpace2;
    }

    public void setRemainSpace2(Integer remainSpace2) {
        this.remainSpace2 = remainSpace2;
    }

    public void setUsage2(Float usage2) {
        this.usage2 = usage2;
    }

    public void setAsmName3(String asmName3) {
        this.asmName3 = asmName3;
    }

    public void setTotalSpace3(Integer totalSpace3) {
        this.totalSpace3 = totalSpace3;
    }

    public void setRemainSpace3(Integer remainSpace3) {
        this.remainSpace3 = remainSpace3;
    }

    public void setUsage3(Float usage3) {
        this.usage3 = usage3;
    }
}
