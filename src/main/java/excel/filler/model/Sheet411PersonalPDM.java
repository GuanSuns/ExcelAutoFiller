package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/19/2017.
 */
public class Sheet411PersonalPDM {
    protected Timestamp date;
    protected String province;
    protected Long order1;
    protected Float usage2;
    protected Float weblogicUsage2;
    protected Float usage3;
    protected Float weblogicUsage3;
    protected Float usage4;
    protected Float weblogicUsage4;
    protected Float usage5;
    protected Float weblogicUsage5;
    protected Float usage6;
    protected Float weblogicUsage6;
    protected Float usage7;
    protected Float weblogicUsage7;


    public Sheet411PersonalPDM(){
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
    }

    public Sheet411PersonalPDM(Timestamp date, String province, Long order1
            , Float usage2, Float weblogicUsage2
            , Float usage3, Float weblogicUsage3
            , Float usage4, Float weblogicUsage4
            , Float usage5, Float weblogicUsage5
            , Float usage6, Float weblogicUsage6
            , Float usage7, Float weblogicUsage7) {
        this.date = date;
        this.province = province;
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

}
