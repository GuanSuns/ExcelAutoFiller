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
    private Integer hoggingThreadCnt;
    private String jdbcState;
    private Integer jdbcConnectionCnt;

    public DailyAppInspectionPDM() {
        this.inspectTime = new Timestamp(new Date().getTime());
        this.clusterName = "";
        this.cpuUsage = 0f;
        this.memoryUsage = 0f;
        this.softwareDirectoryUsage = 0f;
        this.svrState = "";
        this.hoggingThreadCnt = 0;
        this.jdbcState = "";
        this.jdbcConnectionCnt = 0;
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

    public Integer getHoggingThreadCnt() {
        return hoggingThreadCnt;
    }

    public void setHoggingThreadCnt(Integer hoggingThreadCnt) {
        this.hoggingThreadCnt = hoggingThreadCnt;
    }

    public String getJdbcState() {
        return jdbcState;
    }

    public void setJdbcState(String jdbcState) {
        this.jdbcState = jdbcState;
    }

    public Integer getJdbcConnectionCnt() {
        return jdbcConnectionCnt;
    }

    public void setJdbcConnectionCnt(Integer jdbcConnectionCnt) {
        this.jdbcConnectionCnt = jdbcConnectionCnt;
    }
}
