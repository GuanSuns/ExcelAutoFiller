package model;

import utils.ExcelUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by guanl on 6/23/2017.
 */
public class Sheet429DataPDM {
    public Integer instID;
    public Timestamp btime;
    public Long intSize;
    public String mrtricName;
    public Integer numInterval;
    public Double minVal;
    public Double maxVal;
    public Double std;
    public Long sumSquare;

    public Sheet429DataPDM(Integer instID, Timestamp btime
            , Long intSize, String mrtricName
            , Integer numInterval, Double minVal
            , Double maxVal, Double std, Long sumSquare) {
        this.instID = instID;
        this.btime = btime;
        this.intSize = intSize;
        this.mrtricName = mrtricName;
        this.numInterval = numInterval;
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.std = std;
        this.sumSquare = sumSquare;
    }

    public Sheet429DataPDM() {
        this.instID = 0;
        this.btime = null;
        this.intSize = 0L;
        this.mrtricName = "";
        this.numInterval = 0;
        this.minVal = 0d;
        this.maxVal = 0d;
        this.std = 0d;
        this.sumSquare = 0L;
    }

    public String getSQLResult() throws Exception{
        if(btime == null){
            btime = new Timestamp(new Date().getTime());
        }

        return "" + instID
                + "\n" + ExcelUtils.timestampToString(btime)
                + "\n" + intSize
                + "\n" + mrtricName
                + "\n" + numInterval
                + "\n" + minVal
                + "\n" + maxVal
                + "\n" + std
                + "\n" + sumSquare;
    }
}
