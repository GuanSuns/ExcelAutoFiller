package model;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet422PersonalPDM {
    private Long order1;
    private String batch;
    private String province;

    private String tsName2;
    private String tsTotalSpace2;
    private String tsUsedSpace2;
    private String tsUsage2;

    private String tsName3;
    private String tsTotalSpace3;
    private String tsUsedSpace3;
    private String tsUsage3;

    public Sheet422PersonalPDM() {
        this.batch = "";
        this.order1 = 0L;
        this.province = "广东";
        this.tsName2 = "";
        this.tsTotalSpace2 = "";
        this.tsUsage2 = "";
        this.tsUsedSpace2 = "";

        this.tsName3 = "";
        this.tsTotalSpace3 = "";
        this.tsUsedSpace3 = "";
        this.tsUsage3 = "";
    }

    public Sheet422PersonalPDM(Long order1, String batch, String province
            , String tsName2, String tsTotalSpace2
            , String tsUsedSpace2, String tsUsage2
            , String tsName3, String tsTotalSpace3
            , String tsUsedSpace3, String tsUsage3) {
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

    public Long getOrder1() {
        return order1;
    }

    public String getBatch() {
        return batch;
    }

    public String getProvince() {
        return province;
    }

    public String getTsName2() {
        return tsName2;
    }

    public String getTsTotalSpace2() {
        return tsTotalSpace2;
    }

    public String getTsUsedSpace2() {
        return tsUsedSpace2;
    }

    public String getTsUsage2() {
        return tsUsage2;
    }

    public String getTsName3() {
        return tsName3;
    }

    public String getTsTotalSpace3() {
        return tsTotalSpace3;
    }

    public String getTsUsedSpace3() {
        return tsUsedSpace3;
    }

    public String getTsUsage3() {
        return tsUsage3;
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

    public void setTsName2(String tsName2) {
        this.tsName2 = tsName2;
    }

    public void setTsTotalSpace2(String tsTotalSpace2) {
        this.tsTotalSpace2 = tsTotalSpace2;
    }

    public void setTsUsedSpace2(String tsUsedSpace2) {
        this.tsUsedSpace2 = tsUsedSpace2;
    }

    public void setTsUsage2(String tsUsage2) {
        this.tsUsage2 = tsUsage2;
    }

    public void setTsName3(String tsName3) {
        this.tsName3 = tsName3;
    }

    public void setTsTotalSpace3(String tsTotalSpace3) {
        this.tsTotalSpace3 = tsTotalSpace3;
    }

    public void setTsUsedSpace3(String tsUsedSpace3) {
        this.tsUsedSpace3 = tsUsedSpace3;
    }

    public void setTsUsage3(String tsUsage3) {
        this.tsUsage3 = tsUsage3;
    }
}
