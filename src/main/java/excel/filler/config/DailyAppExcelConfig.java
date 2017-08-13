package excel.filler.config;

import java.util.Calendar;

public class DailyAppExcelConfig {
    private static String sheetName = "应用检查结果记录";

    private static Calendar[] inspectTimes = initInspectTimes();

    private static Calendar[] initInspectTimes(){
        Calendar[] inspectTimes = new Calendar[3];

        Calendar time1 = Calendar.getInstance();
        time1.set(Calendar.HOUR_OF_DAY, 8);
        time1.set(Calendar.MINUTE, 0);
        inspectTimes[0] = time1;

        Calendar time2 = Calendar.getInstance();
        time2.set(Calendar.HOUR_OF_DAY, 10);
        time2.set(Calendar.MINUTE, 0);
        inspectTimes[1] = time2;

        Calendar time3 = Calendar.getInstance();
        time3.set(Calendar.HOUR_OF_DAY, 12);
        time3.set(Calendar.MINUTE, 0);
        inspectTimes[2] = time3;

        return inspectTimes;
    }

    private static String[] personalInspectClustersNames = {"hxqd", "mhwb_wfbs", "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static String[] personalInspectClustersPrintedNames = {"hxqd", "mhwb_wfbs", "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static String[] coreInspectClustersNames = {"hxqd", "mhwb_wfbs", "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static String[] coreInspectClustersPrintedNames = {"hxqd", "mhwb_wfbs", "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static int AppRecordStartRow = 2;
    private static int AppDayCellIndex = 0;
    private static int AppTimeCellIndex = 1;
    private static int AppHostNamesIndex = 2;
    private static int AppStart = 3;
    private static int AppEnd = 10;

    private static int cpuUsageIndex = 3;
    private static int memoryUsageIndex = 4;
    private static int softwareDirectoryUsageIndex = 5;
    private static int svrStateIndex = 6;
    private static int hoggingThreadCntIndex = 7;
    private static int jdbcStateIndex = 8;
    private static int jdbcConnectionCntIndex = 9;

    private static String descriptionIsRunning = "是";
    private static String descriptionNotRunning = "否";

    public static int getCpuUsageIndex() {
        return cpuUsageIndex;
    }

    public static int getMemoryUsageIndex() {
        return memoryUsageIndex;
    }

    public static int getSoftwareDirectoryUsageIndex() {
        return softwareDirectoryUsageIndex;
    }

    public static int getSvrStateIndex() {
        return svrStateIndex;
    }

    public static int getHoggingThreadCntIndex() {
        return hoggingThreadCntIndex;
    }

    public static int getJdbcStateIndex() {
        return jdbcStateIndex;
    }

    public static int getJdbcConnectionCntIndex() {
        return jdbcConnectionCntIndex;
    }

    public static String getDescriptionIsRunning() {
        return descriptionIsRunning;
    }

    public static String getDescriptionNotRunning() {
        return descriptionNotRunning;
    }

    public static String[] getCoreInspectClustersNames() {
        return coreInspectClustersNames;
    }

    public static void setCoreInspectClustersNames(String[] coreInspectClustersNames) {
        DailyAppExcelConfig.coreInspectClustersNames = coreInspectClustersNames;
    }

    public static String[] getCoreInspectClustersPrintedNames() {
        return coreInspectClustersPrintedNames;
    }

    public static void setCoreInspectClustersPrintedNames(String[] coreInspectClustersPrintedNames) {
        DailyAppExcelConfig.coreInspectClustersPrintedNames = coreInspectClustersPrintedNames;
    }

    public static String getSheetName() {
        return sheetName;
    }

    public static void setSheetName(String sheetName) {
        DailyAppExcelConfig.sheetName = sheetName;
    }

    public static Calendar[] getInspectTimes() {
        return inspectTimes;
    }

    public static void setInspectTimes(Calendar[] inspectTimes) {
        DailyAppExcelConfig.inspectTimes = inspectTimes;
    }

    public static String[] getPersonalInspectClustersNames() {
        return personalInspectClustersNames;
    }

    public static void setPersonalInspectClustersNames(String[] personalInspectClustersNames) {
        DailyAppExcelConfig.personalInspectClustersNames = personalInspectClustersNames;
    }

    public static String[] getPersonalInspectClustersPrintedNames() {
        return personalInspectClustersPrintedNames;
    }

    public static void setPersonalInspectClustersPrintedNames(String[] personalInspectClustersPrintedNames) {
        DailyAppExcelConfig.personalInspectClustersPrintedNames = personalInspectClustersPrintedNames;
    }

    public static int getAppRecordStartRow() {
        return AppRecordStartRow;
    }

    public static int getAppDayCellIndex() {
        return AppDayCellIndex;
    }

    public static int getAppTimeCellIndex() {
        return AppTimeCellIndex;
    }

    public static int getAppHostNamesIndex() {
        return AppHostNamesIndex;
    }

    public static int getAppStart() {
        return AppStart;
    }

    public static int getAppEnd() {
        return AppEnd;
    }
}
