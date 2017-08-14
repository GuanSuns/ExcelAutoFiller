package excel.filler.model;

import java.sql.Timestamp;
import java.util.Date;

public class DailyDBInspectionPDM {
    private Timestamp inspectTime;
    private String clusterName;
    private Float cpuUsage;
    private Float memoryUsage;
    private Float archiveUsage;
    private String hasLongTermLock;
    private String hasOverloadedTable;
    private String hasErrorLog;
    private Float diskBusy;

    public DailyDBInspectionPDM() {
        this.inspectTime = new Timestamp(new Date().getTime());
        this.clusterName = "";
        this.cpuUsage = 0f;
        this.memoryUsage = 0f;
        this.archiveUsage = 0f;
        this.hasLongTermLock = "";
        this.hasOverloadedTable = "";
        this.hasErrorLog = "";
        this.diskBusy = 0f;
    }

    public Timestamp getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Timestamp inspectTime) {
        this.inspectTime = inspectTime;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Float getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Float getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(Float memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public Float getArchiveUsage() {
        return archiveUsage;
    }

    public void setArchiveUsage(Float archiveUsage) {
        this.archiveUsage = archiveUsage;
    }

    public String getHasLongTermLock() {
        return hasLongTermLock;
    }

    public void setHasLongTermLock(String hasLongTermLock) {
        this.hasLongTermLock = hasLongTermLock;
    }

    public String getHasOverloadedTable() {
        return hasOverloadedTable;
    }

    public void setHasOverloadedTable(String hasOverloadedTable) {
        this.hasOverloadedTable = hasOverloadedTable;
    }

    public String getHasErrorLog() {
        return hasErrorLog;
    }

    public void setHasErrorLog(String hasErrorLog) {
        this.hasErrorLog = hasErrorLog;
    }

    public Float getDiskBusy() {
        return diskBusy;
    }

    public void setDiskBusy(Float diskBusy) {
        this.diskBusy = diskBusy;
    }

    @Override
    public String toString() {
        return "DailyDBInspectionPDM{" +
                "inspectTime=" + inspectTime +
                ", clusterName='" + clusterName + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", archiveUsage=" + archiveUsage +
                ", hasLongTermLock='" + hasLongTermLock + '\'' +
                ", hasOverloadedTable='" + hasOverloadedTable + '\'' +
                ", hasErrorLog='" + hasErrorLog + '\'' +
                ", diskBusy=" + diskBusy +
                '}';
    }
}
