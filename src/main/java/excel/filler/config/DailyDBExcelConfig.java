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
        time2.set(Calendar.HOUR_OF_DAY, 11);
        time2.set(Calendar.MINUTE, 0);
        inspectTimes[1] = time2;

        Calendar time3 = Calendar.getInstance();
        time3.set(Calendar.HOUR_OF_DAY, 17);
        time3.set(Calendar.MINUTE, 0);
        inspectTimes[2] = time3;

        return inspectTimes;
    }

    private static String[] inspectHostNames = {"hxqd", "mhwb_wfbs", "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static String[] inspectHostPrintedNames = {"hxqd", "mhwb_wfbs", "hxhd_dsrw"
            , "tycx_jnap", "hxwf_mhap_jcpt"
            , "kcpt", "osb"};

    private static int DBRecordStartRow = 2;
    private static int DBDayCellIndex = 0;
    private static int DBTimeCellIndex = 1;
    private static int DBHostNamesIndex = 2;
    private static int DBStart = 3;
    private static int DBEnd = 9;

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

    public static String[] getInspectHostNames() {
        return inspectHostNames;
    }

    public static void setInspectHostNames(String[] inspectHostNames) {
        DailyDBExcelConfig.inspectHostNames = inspectHostNames;
    }

    public static String[] getInspectHostPrintedNames() {
        return inspectHostPrintedNames;
    }

    public static void setInspectHostPrintedNames(String[] inspectHostPrintedNames) {
        DailyDBExcelConfig.inspectHostPrintedNames = inspectHostPrintedNames;
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
