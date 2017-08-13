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

    private static int AppRecordStartRow = 2;
    private static int AppDayCellIndex = 0;
    private static int AppTimeCellIndex = 1;
    private static int AppHostNamesIndex = 2;
    private static int AppStart = 3;
    private static int AppEnd = 9;

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

    public static String[] getInspectHostNames() {
        return inspectHostNames;
    }

    public static void setInspectHostNames(String[] inspectHostNames) {
        DailyAppExcelConfig.inspectHostNames = inspectHostNames;
    }

    public static String[] getInspectHostPrintedNames() {
        return inspectHostPrintedNames;
    }

    public static void setInspectHostPrintedNames(String[] inspectHostPrintedNames) {
        DailyAppExcelConfig.inspectHostPrintedNames = inspectHostPrintedNames;
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
