package model;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet422CorePDM extends Sheet422PersonalPDM{
    private String tsName4;
    private String tsTotalSpace4;
    private String tsUsedSpace4;
    private String tsUsage4;

    private String tsName5;
    private String tsTotalSpace5;
    private String tsUsedSpace5;
    private String tsUsage5;

    public Sheet422CorePDM() {
        super();
        this.tsName4 = "";
        this.tsTotalSpace4 = "";
        this.tsUsedSpace4 = "";
        this.tsUsage4 = "";

        this.tsName5 = "";
        this.tsTotalSpace5 = "";
        this.tsUsedSpace5 = "";
        this.tsUsage5 = "";
    }

    public Sheet422CorePDM(Long order1, String batch, String province
            , String tsName2, String tsTotalSpace2
            , String tsUsedSpace2, String tsUsage2
            , String tsName3, String tsTotalSpace3
            , String tsUsedSpace3, String tsUsage3
            , String tsName4, String tsTotalSpace4
            , String tsUsedSpace4, String tsUsage4
            , String tsName5, String tsTotalSpace5
            , String tsUsedSpace5, String tsUsage5) {
        super(order1, batch, province, tsName2, tsTotalSpace2, tsUsedSpace2, tsUsage2, tsName3, tsTotalSpace3, tsUsedSpace3, tsUsage3);
        this.tsName4 = tsName4;
        this.tsTotalSpace4 = tsTotalSpace4;
        this.tsUsedSpace4 = tsUsedSpace4;
        this.tsUsage4 = tsUsage4;
        this.tsName5 = tsName5;
        this.tsTotalSpace5 = tsTotalSpace5;
        this.tsUsedSpace5 = tsUsedSpace5;
        this.tsUsage5 = tsUsage5;
    }

    public String getTsName4() {
        return tsName4;
    }

    public String getTsTotalSpace4() {
        return tsTotalSpace4;
    }

    public String getTsUsedSpace4() {
        return tsUsedSpace4;
    }

    public String getTsUsage4() {
        return tsUsage4;
    }

    public String getTsName5() {
        return tsName5;
    }

    public String getTsTotalSpace5() {
        return tsTotalSpace5;
    }

    public String getTsUsedSpace5() {
        return tsUsedSpace5;
    }

    public String getTsUsage5() {
        return tsUsage5;
    }

    public void setTsName4(String tsName4) {
        this.tsName4 = tsName4;
    }

    public void setTsTotalSpace4(String tsTotalSpace4) {
        this.tsTotalSpace4 = tsTotalSpace4;
    }

    public void setTsUsedSpace4(String tsUsedSpace4) {
        this.tsUsedSpace4 = tsUsedSpace4;
    }

    public void setTsUsage4(String tsUsage4) {
        this.tsUsage4 = tsUsage4;
    }

    public void setTsName5(String tsName5) {
        this.tsName5 = tsName5;
    }

    public void setTsTotalSpace5(String tsTotalSpace5) {
        this.tsTotalSpace5 = tsTotalSpace5;
    }

    public void setTsUsedSpace5(String tsUsedSpace5) {
        this.tsUsedSpace5 = tsUsedSpace5;
    }

    public void setTsUsage5(String tsUsage5) {
        this.tsUsage5 = tsUsage5;
    }
}
