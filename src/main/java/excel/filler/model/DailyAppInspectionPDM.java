package excel.filler.model;

import java.sql.Timestamp;
import java.util.Date;

public class DailyAppInspectionPDM {

    private Timestamp inspectTime;
    private String clusterName;
    private Float cpuUsage;
    private Float memoryUsage;
    private Float softwareDirectoryUsage;
    private String svrState;
    private Float hoggingThreadCnt;
    private String jdbcState;
    private Float jdbcConnectionCnt;

    public DailyAppInspectionPDM() {
        this.inspectTime = new Timestamp(new Date().getTime());
        this.clusterName = "";
        this.cpuUsage = 0f;
        this.memoryUsage = 0f;
        this.softwareDirectoryUsage = 0f;
        this.svrState = "";
        this.hoggingThreadCnt = 0f;
        this.jdbcState = "";
        this.jdbcConnectionCnt = 0f;
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

    public Float getSoftwareDirectoryUsage() {
        return softwareDirectoryUsage;
    }

    public void setSoftwareDirectoryUsage(Float softwareDirectoryUsage) {
        this.softwareDirectoryUsage = softwareDirectoryUsage;
    }

    public String getSvrState() {
        return svrState;
    }

    public void setSvrState(String svrState) {
        this.svrState = svrState;
    }

    public Float getHoggingThreadCnt() {
        return hoggingThreadCnt;
    }

    public void setHoggingThreadCnt(Float hoggingThreadCnt) {
        this.hoggingThreadCnt = hoggingThreadCnt;
    }

    public String getJdbcState() {
        return jdbcState;
    }

    public void setJdbcState(String jdbcState) {
        this.jdbcState = jdbcState;
    }

    public Float getJdbcConnectionCnt() {
        return jdbcConnectionCnt;
    }

    public void setJdbcConnectionCnt(Float jdbcConnectionCnt) {
        this.jdbcConnectionCnt = jdbcConnectionCnt;
    }
}
