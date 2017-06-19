package model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/19/2017.
 */
public class Sheet411PDM {
    private Timestamp date;
    private String province;
    private Long order1;
    private Float usage2;
    private Float weblogicUsage2;
    private Float usage3;
    private Float weblogicUsage3;
    private Float usage4;
    private Float weblogicUsage4;
    private Float usage5;
    private Float weblogicUsage5;
    private Float usage6;
    private Float weblogicUsage6;
    private Float usage7;
    private Float weblogicUsage7;
    private Float usage8;
    private Float weblogicUsage8;

    public Sheet411PDM(){
        this.date = null;
        this.province = "广东";
        this.order1 = 0L;
        this.usage2 = 0F;
        this.weblogicUsage2 = 0F;
        this.usage3 = 0F;
        this.weblogicUsage3 = 0F;
        this.usage4 = 0F;
        this.weblogicUsage4 = 0F;
        this.usage5 = 0F;
        this.weblogicUsage5 = 0F;
        this.usage6 = 0F;
        this.weblogicUsage6 = 0F;
        this.usage7 = 0F;
        this.weblogicUsage7 = 0F;
        this.usage8 = 0F;
        this.weblogicUsage8 = 0F;
    }

    public Sheet411PDM(Timestamp date, Long order1
            , Float usage2, Float weblogicUsage2
            , Float usage3, Float weblogicUsage3
            , Float usage4, Float weblogicUsage4
            , Float usage5, Float weblogicUsage5
            , Float usage6, Float weblogicUsage6
            , Float usage7, Float weblogicUsage7
            , Float usage8, Float weblogicUsage8) {
        this.date = date;
        this.order1 = order1;
        this.usage2 = usage2;
        this.weblogicUsage2 = weblogicUsage2;
        this.usage3 = usage3;
        this.weblogicUsage3 = weblogicUsage3;
        this.usage4 = usage4;
        this.weblogicUsage4 = weblogicUsage4;
        this.usage5 = usage5;
        this.weblogicUsage5 = weblogicUsage5;
        this.usage6 = usage6;
        this.weblogicUsage6 = weblogicUsage6;
        this.usage7 = usage7;
        this.weblogicUsage7 = weblogicUsage7;
        this.usage8 = usage8;
        this.weblogicUsage8 = weblogicUsage8;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getProvince() {
        return province;
    }

    public Long getOrder1() {
        return order1;
    }

    public Float getUsage2() {
        return usage2;
    }

    public Float getWeblogicUsage2() {
        return weblogicUsage2;
    }

    public Float getUsage3() {
        return usage3;
    }

    public Float getWeblogicUsage3() {
        return weblogicUsage3;
    }

    public Float getUsage4() {
        return usage4;
    }

    public Float getWeblogicUsage4() {
        return weblogicUsage4;
    }

    public Float getUsage5() {
        return usage5;
    }

    public Float getWeblogicUsage5() {
        return weblogicUsage5;
    }

    public Float getUsage6() {
        return usage6;
    }

    public Float getWeblogicUsage6() {
        return weblogicUsage6;
    }

    public Float getUsage7() {
        return usage7;
    }

    public Float getWeblogicUsage7() {
        return weblogicUsage7;
    }

    public Float getUsage8() {
        return usage8;
    }

    public Float getWeblogicUsage8() {
        return weblogicUsage8;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setOrder1(Long order1) {
        this.order1 = order1;
    }

    public void setUsage2(Float usage2) {
        this.usage2 = usage2;
    }

    public void setWeblogicUsage2(Float weblogicUsage2) {
        this.weblogicUsage2 = weblogicUsage2;
    }

    public void setUsage3(Float usage3) {
        this.usage3 = usage3;
    }

    public void setWeblogicUsage3(Float weblogicUsage3) {
        this.weblogicUsage3 = weblogicUsage3;
    }

    public void setUsage4(Float usage4) {
        this.usage4 = usage4;
    }

    public void setWeblogicUsage4(Float weblogicUsage4) {
        this.weblogicUsage4 = weblogicUsage4;
    }

    public void setUsage5(Float usage5) {
        this.usage5 = usage5;
    }

    public void setWeblogicUsage5(Float weblogicUsage5) {
        this.weblogicUsage5 = weblogicUsage5;
    }

    public void setUsage6(Float usage6) {
        this.usage6 = usage6;
    }

    public void setWeblogicUsage6(Float weblogicUsage6) {
        this.weblogicUsage6 = weblogicUsage6;
    }

    public void setUsage7(Float usage7) {
        this.usage7 = usage7;
    }

    public void setWeblogicUsage7(Float weblogicUsage7) {
        this.weblogicUsage7 = weblogicUsage7;
    }

    public void setUsage8(Float usage8) {
        this.usage8 = usage8;
    }

    public void setWeblogicUsage8(Float weblogicUsage8) {
        this.weblogicUsage8 = weblogicUsage8;
    }
}
