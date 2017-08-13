package excel.filler.config;

import java.util.Calendar;

public class DailyDBExcelConfig {
    private static String sheetName = "数据库及ogg检查结果记录";

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

    private static String[] personalInspectClustersNames = {"hxqd", "mhwb_wfbs"
            , "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static String[] personalInspectClustersPrintedNames = {"hxqd"
            , "mhwb_wfbs", "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static String[] coreInspectClustersNames = {"hxqd", "mhwb_wfbs"
            , "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static String[] coreInspectClustersPrintedNames = {"hxqd"
            , "mhwb_wfbs", "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static int DBRecordStartRow = 2;
    private static int DBDayCellIndex = 0;
    private static int DBTimeCellIndex = 1;
    private static int DBHostNamesIndex = 2;
    private static int DBStart = 3;
    private static int DBEnd = 11;

    private static int cpuUsageIndex = 3;
    private static int memoryUsageIndex = 4;
    private static int archiveUsageIndex = 5;
    private static int longTermLockIndex = 6;
    private static int tableSpaceCheckIndex = 7;
    private static int alertLogIndex = 8;
    private static int diskBusyIndex = 9;

    private static String descriptionHasException = "有";
    private static String descriptionNoException = "无";
    private static String descriptionHasOverloadedTable = "是";
    private static String descriptionNoOverloadedTable = "否";

    public static String getDescriptionHasException() {
        return descriptionHasException;
    }

    public static String getDescriptionNoException() {
        return descriptionNoException;
    }

    public static String getDescriptionHasOverloadedTable() {
        return descriptionHasOverloadedTable;
    }

    public static String getDescriptionNoOverloadedTable() {
        return descriptionNoOverloadedTable;
    }

    public static int getCpuUsageIndex() {
        return cpuUsageIndex;
    }

    public static int getMemoryUsageIndex() {
        return memoryUsageIndex;
    }

    public static int getArchiveUsageIndex() {
        return archiveUsageIndex;
    }

    public static int getLongTermLockIndex() {
        return longTermLockIndex;
    }

    public static int getTableSpaceCheckIndex() {
        return tableSpaceCheckIndex;
    }

    public static int getAlertLogIndex() {
        return alertLogIndex;
    }

    public static int getDiskBusyIndex() {
        return diskBusyIndex;
    }

    public static String[] getCoreInspectClustersNames() {
        return coreInspectClustersNames;
    }

    public static void setCoreInspectClustersNames(String[] coreInspectClustersNames) {
        DailyDBExcelConfig.coreInspectClustersNames = coreInspectClustersNames;
    }

    public static String[] getCoreInspectClustersPrintedNames() {
        return coreInspectClustersPrintedNames;
    }

    public static void setCoreInspectClustersPrintedNames(String[] coreInspectClustersPrintedNames) {
        DailyDBExcelConfig.coreInspectClustersPrintedNames = coreInspectClustersPrintedNames;
    }

    public static String getSheetName() {
        return sheetName;
    }

    public static void setSheetName(String sheetName) {
        DailyDBExcelConfig.sheetName = sheetName;
    }

    public static Calendar[] getInspectTimes() {
        return inspectTimes;
    }

    public static void setInspectTimes(Calendar[] inspectTimes) {
        DailyDBExcelConfig.inspectTimes = inspectTimes;
    }

    public static String[] getPersonalInspectClustersNames() {
        return personalInspectClustersNames;
    }

    public static void setPersonalInspectClustersNames(String[] personalInspectClustersNames) {
        DailyDBExcelConfig.personalInspectClustersNames = personalInspectClustersNames;
    }

    public static String[] getPersonalInspectClustersPrintedNames() {
        return personalInspectClustersPrintedNames;
    }

    public static void setPersonalInspectClustersPrintedNames(String[] personalInspectClustersPrintedNames) {
        DailyDBExcelConfig.personalInspectClustersPrintedNames = personalInspectClustersPrintedNames;
    }

    public static int getDBRecordStartRow() {
        return DBRecordStartRow;
    }

    public static int getDBDayCellIndex() {
        return DBDayCellIndex;
    }

    public static int getDBTimeCellIndex() {
        return DBTimeCellIndex;
    }

    public static int getDBHostNamesIndex() {
        return DBHostNamesIndex;
    }

    public static int getDBStart() {
        return DBStart;
    }

    public static int getDBEnd() {
        return DBEnd;
    }
}
