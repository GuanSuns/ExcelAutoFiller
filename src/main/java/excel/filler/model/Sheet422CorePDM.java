package excel.filler.model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/22/2017.
 */
public class Sheet422CorePDM extends Sheet422PersonalPDM{
    private String tsName4;
    private Float tsTotalSpace4;
    private Float tsUsedSpace4;
    private Float tsUsage4;

    private String tsName5;
    private Float tsTotalSpace5;
    private Float tsUsedSpace5;
    private Float tsUsage5;

    public Sheet422CorePDM() {
        super();
        this.tsName4 = "";
        this.tsTotalSpace4 = 0f;
        this.tsUsedSpace4 = 0f;
        this.tsUsage4 = 0f;

        this.tsName5 = "";
        this.tsTotalSpace5 = 0f;
        this.tsUsedSpace5 = 0f;
        this.tsUsage5 = 0f;
    }

    public Sheet422CorePDM(Timestamp date, Long order1, String batch, String province
            , String tsName2, Float tsTotalSpace2, Float tsUsedSpace2
            , Float tsUsage2, String tsName3, Float tsTotalSpace3
            , Float tsUsedSpace3, Float tsUsage3, String tsName4
            , Float tsTotalSpace4, Float tsUsedSpace4, Float tsUsage4
            , String tsName5, Float tsTotalSpace5, Float tsUsedSpace5, Float tsUsage5) {
        super(date, order1, batch, province, tsName2, tsTotalSpace2
                , tsUsedSpace2, tsUsage2, tsName3, tsTotalSpace3, tsUsedSpace3, tsUsage3);
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

    public void setTsName4(String tsName4) {
        this.tsName4 = tsName4;
    }

    public Float getTsTotalSpace4() {
        return tsTotalSpace4;
    }

    public void setTsTotalSpace4(Float tsTotalSpace4) {
        this.tsTotalSpace4 = tsTotalSpace4;
    }

    public Float getTsUsedSpace4() {
        return tsUsedSpace4;
    }

    public void setTsUsedSpace4(Float tsUsedSpace4) {
        this.tsUsedSpace4 = tsUsedSpace4;
    }

    public Float getTsUsage4() {
        return tsUsage4;
    }

    public void setTsUsage4(Float tsUsage4) {
        this.tsUsage4 = tsUsage4;
    }

    public String getTsName5() {
        return tsName5;
    }

    public void setTsName5(String tsName5) {
        this.tsName5 = tsName5;
    }

    public Float getTsTotalSpace5() {
        return tsTotalSpace5;
    }

    public void setTsTotalSpace5(Float tsTotalSpace5) {
        this.tsTotalSpace5 = tsTotalSpace5;
    }

    public Float getTsUsedSpace5() {
        return tsUsedSpace5;
    }

    public void setTsUsedSpace5(Float tsUsedSpace5) {
        this.tsUsedSpace5 = tsUsedSpace5;
    }

    public Float getTsUsage5() {
        return tsUsage5;
    }

    public void setTsUsage5(Float tsUsage5) {
        this.tsUsage5 = tsUsage5;
    }
}
