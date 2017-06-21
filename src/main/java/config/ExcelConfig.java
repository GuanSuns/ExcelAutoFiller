package config;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * Created by guanl on 6/19/2017.
 */
public class ExcelConfig {
    //Sheet names settings
    public static String SheetName411 = "4.1.1应用OS文件系统目录使用率";
    public static String SheetName421 = "4.2.1数据库OS文件系统目录使用率";
    public static String SheetName422 = "4.2.2表空间使用率检查";
    public static String SheetName423 = "4.2.3ASM共享磁盘检查";
    public static String SheetName424 = "4.2.4统计信息收集检查";
    public static String SheetName425 = "4.2.5ogg检查";
    public static String SheetName426 = "4.2.6alert日志检查";
    public static String SheetName427 = "4.2.7定时任务检查";
    public static String SheetName428 = "4.2.8时钟同步检查";
    public static String SheetName429 = "4.2.9集群心跳检查";

    //Default cell style
    public static boolean cellBorder = true;
    public static boolean centralAlignment = true;
    public static String font = "等线";
    public static short fontSize = 220;
    public static BorderStyle borderStyle = BorderStyle.THIN;
    public static short borderColor = IndexedColors.BLACK.index;

    //Default value for null
    public static String nullValue = "无";

    //Sheet 4.1.1 Setting
    public static int Sheet411PersonalStart = 3;
    public static int Sheet411PersonalEnd = 15;
    public static int Sheet411CoreStart = 3;
    public static int Sheet411CoreEnd = 17;
    public static String Sheet411DefaultProvince = "广东";
    public static int Sheet411RecordStartRow = 3;
    public static int Sheet411TimeCellIndex = 0;
    public static int Sheet411Order1CellIndex = 1;
    public static int Sheet411ProvinceCellIndex = 2;
    public static int Sheet411PersonalRemarkIndex = 15;
    public static int Sheet411CoreRemarkIndex = 17;

}
