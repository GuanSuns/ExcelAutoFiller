package model;

import java.sql.Timestamp;

/**
 * Created by guanl on 6/21/2017.
 */
public class Sheet411CorePDM extends Sheet411PersonalPDM {
    private Float usage8;
    private Float weblogicUsage8;

    public Sheet411CorePDM(Float usage8, Float weblogicUsage8) {
        super();
        this.usage8 = usage8;
        this.weblogicUsage8 = weblogicUsage8;
    }

    public Sheet411CorePDM(Timestamp date, String province, Long order1
            , Float usage2, Float weblogicUsage2
            , Float usage3, Float weblogicUsage3
            , Float usage4, Float weblogicUsage4
            , Float usage5, Float weblogicUsage5
            , Float usage6, Float weblogicUsage6
            , Float usage7, Float weblogicUsage7
            , Float usage8, Float weblogicUsage8
    ) {
        super(date, province, order1, usage2, weblogicUsage2, usage3, weblogicUsage3, usage4, weblogicUsage4, usage5, weblogicUsage5, usage6, weblogicUsage6, usage7, weblogicUsage7);
        this.usage8 = usage8;
        this.weblogicUsage8 = weblogicUsage8;
    }

    public Float getUsage8() {
        return usage8;
    }

    public Float getWeblogicUsage8() {
        return weblogicUsage8;
    }

    public void setUsage8(Float usage8) {
        this.usage8 = usage8;
    }

    public void setWeblogicUsage8(Float weblogicUsage8) {
        this.weblogicUsage8 = weblogicUsage8;
    }
}
