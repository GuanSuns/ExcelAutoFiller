package excel.filler.config;


public class DailyExcelConfig {

    private static String dayDateFormat = "yyyy/MM/dd";
    private static String timeDateFormat = "HH:mm";

    public static String getDayDateFormat() {
        return dayDateFormat;
    }

    public static String getTimeDateFormat() {
        return timeDateFormat;
    }
}
