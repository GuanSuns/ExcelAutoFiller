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
    public static String nullValue = "";

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
    
    //Sheet 4.2.1 Setting
    public static int Sheet421PersonalStart = 3;
    public static int Sheet421PersonalEnd = 9;
    public static int Sheet421PersonalBlankStart = 9;
    public static int Sheet421PersonalBlankEnd = 13;
    public static int Sheet421CoreStart = 4;
    public static int Sheet421CoreEnd = 16;
    public static int Sheet421CoreBlankStart = 16;
    public static int Sheet421CoreBlankEnd = 17;
    public static String Sheet421DefaultProvince = "广东";
    public static int Sheet421RecordStartRow = 3;
    public static int Sheet421TimeCellIndex = 0;
    public static int Sheet421Order1CellIndex = 1;
    public static int Sheet421CoreBatchCellIndex = 2;
    public static int Sheet421PersonalProvinceCellIndex = 2;
    public static int Sheet421CoreProvinceCellIndex = 3;

    //Sheet 4.2.2 Setting
    public static String Sheet422DefaultProvince = "广东";
    public static int Sheet422RecordStartRow = 3;
    public static int Sheet422OrderCellIndex = 0;
    public static int Sheet422BatchCellIndex = 1;
    public static int Sheet422ProvinceCellIndex = 2;
    public static int Sheet422PersonalStart = 3;
    public static int Sheet422PersonalEnd = 11;
    public static int Sheet422PersonalBlankStart = 11;
    public static int Sheet422PersonalBlankEnd = 20;
    public static int Sheet422CoreStart = 3;
    public static int Sheet422CoreEnd = 19;
    public static int Sheet422CoreBlankStart = 19;
    public static int Sheet422CoreBlankEnd = 20;

    //Sheet 4.2.3 Setting
    public static String Sheet423DefaultProvince = "广东";
    public static int Sheet423RecordStartRow = 3;
    public static int Sheet423OrderCellIndex = 0;
    public static int Sheet423BatchCellIndex = 1;
    public static int Sheet423ProvinceCellIndex = 2;
    public static int Sheet423PersonalStart = 3;
    public static int Sheet423PersonalEnd = 11;
    public static int Sheet423PersonalBlankStart = 11;
    public static int Sheet423PersonalBlankEnd = 12;
    public static int Sheet423CoreStart = 3;
    public static int Sheet423CoreEnd = 19;
    public static int Sheet423CoreBlankStart = 19;
    public static int Sheet423CoreBlankEnd = 20;
    public static int Sheet423DataDistance = 4;

    //Sheet 4.2.4 Setting
    public static String Sheet424DefaultProvince = "广东";
    public static int Sheet424RecordStartRow = 3;
    public static int Sheet424OrderCellIndex = 0;
    public static int Sheet424BatchCellIndex = 1;
    public static int Sheet424ProvinceCellIndex = 2;
    public static int Sheet424Start = 3;
    public static int Sheet424PersonalBlankStart = 7;
    public static int Sheet424PersonalBlankEnd = 8;
    public static int Sheet424CoreBlankStart = 11;
    public static int Sheet424CoreBlankEnd = 12;

    //Sheet 4.2.9 Setting
    public static String Sheet429DefaultProvince = "广东";
    public static int Sheet429RecordStartRow = 4;
    public static int Sheet429OrderCellIndex = 0;
    public static int Sheet429BatchCellIndex = 1;
    public static int Sheet429ProvinceCellIndex = 2;
    public static int Sheet429Start = 3;
    public static int Sheet429PersonalBlankStart = 4;
    public static int Sheet429PersonalBlankEnd = 5;
    public static int Sheet429CoreBlankStart = 6;
    public static int Sheet429CoreBlankEnd = 7;
}
