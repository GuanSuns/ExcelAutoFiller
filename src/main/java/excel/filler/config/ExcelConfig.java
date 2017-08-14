package excel.filler.config;

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

    //Some global settings
    public static String dateFormat = "yyyy/MM/dd HH:mm";
    public static String fileDateFormat = "yyMMdd";
    public static int daysRecentInstances = 31;

    //Default value for null
    public static String nullValue = "";

    //Sheet 4.1.1 Setting
    public static int Sheet411PersonalStart = 4;
    public static int Sheet411PersonalEnd = 16;
    public static int Sheet411CoreStart = 4;
    public static int Sheet411CoreEnd = 18;
    public static String Sheet411DefaultProvince = "广东";
    public static int Sheet411RecordStartRow = 3;
    public static int Sheet411TimeCellIndex = 0;
    public static int Sheet411Order1CellIndex = 1;
    public static int Sheet411BatchCellIndex = 2;
    public static int Sheet411ProvinceCellIndex = 3;
    public static int Sheet411PersonalRemarkIndex = 16;
    public static int Sheet411CoreRemarkIndex = 18;
    
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
    public static int Sheet422InspectTimeCellIndex = 0;
    public static int Sheet422RecordStartRow = 3;
    public static int Sheet422OrderCellIndex = 1;
    public static int Sheet422BatchCellIndex = 2;
    public static int Sheet422ProvinceCellIndex = 3;
    public static int Sheet422PersonalStart = 4;
    public static int Sheet422PersonalEnd = 5;
    public static int Sheet422PersonalBlankStart = 5;
    public static int Sheet422PersonalBlankEnd = 8;
    public static int Sheet422CoreStart = 4;
    public static int Sheet422CoreEnd = 8;
    public static int Sheet422CoreBlankStart = 8;
    public static int Sheet422CoreBlankEnd = 9;
    public static int Sheet422DataDistance = 4;
    public static String descriptionHasOverloadedTable = "是";
    public static String descriptionNoOverloadedTable = "否";

    //Sheet 4.2.3 Setting
    public static String Sheet423DefaultProvince = "广东";
    public static int Sheet423TimeCellIndex = 0;
    public static int Sheet423RecordStartRow = 3;
    public static int Sheet423OrderCellIndex = 1;
    public static int Sheet423BatchCellIndex = 2;
    public static int Sheet423ProvinceCellIndex = 3;
    public static int Sheet423PersonalStart = 4;
    public static int Sheet423PersonalEnd = 8;
    public static int Sheet423PersonalBlankStart = 8;
    public static int Sheet423PersonalBlankEnd = 13;
    public static int Sheet423CoreStart = 4;
    public static int Sheet423CoreEnd = 16;
    public static int Sheet423CoreBlankStart = 16;
    public static int Sheet423CoreBlankEnd = 21;
    public static int Sheet423DataDistance = 4;

    //Sheet 4.2.4 Setting
    public static String Sheet424DefaultProvince = "广东";
    public static int Sheet424TimeCellIndex = 0;
    public static int Sheet424RecordStartRow = 3;
    public static int Sheet424OrderCellIndex = 1;
    public static int Sheet424BatchCellIndex = 2;
    public static int Sheet424ProvinceCellIndex = 3;
    public static int Sheet424Start = 4;
    public static int Sheet424PersonalBlankStart = 6;
    public static int Sheet424PersonalBlankEnd = 9;
    public static int Sheet424CoreBlankStart = 10;
    public static int Sheet424CoreBlankEnd = 13;
    public static int Sheet424DataDistance = 2;

    //Sheet 4.2.6 Setting
    public static String Sheet426DefaultProvince = "广东";
    public static int Sheet426TimeCellIndex = 0;
    public static int Sheet426RecordStartRow = 3;
    public static int Sheet426OrderCellIndex = 1;
    public static int Sheet426BatchCellIndex = 2;
    public static int Sheet426ProvinceCellIndex = 3;
    public static int Sheet426Start = 4;
    public static int Sheet426PersonalBlankStart = 5;
    public static int Sheet426PersonalBlankEnd = 7;
    public static int Sheet426CoreBlankStart = 8;
    public static int Sheet426CoreBlankEnd = 9;
    public static String Sheet426ErrorString = "有";
    public static String Sheet426NoErrorString = "无";
    public static float Sheet426LogCellHeight = 90f;
    public static int Sheet426LogCellWidth = 256*30;
    public static int Sheet426Pic1X1 = 307;
    public static int Sheet426Pic1X2 = 716;
    public static int Sheet426Pic20X1 = 68;
    public static int Sheet426Pic20X2 = 477;
    public static int Sheet426Pic21X1 = 545;
    public static int Sheet426Pic21X2 = 954;
    public static int Sheet426PicY1 = 25;
    public static int Sheet426PicY2 = 230;
    public static String Sheet426LogIcon = "/iconLog.png";

    
    //Sheet 4.2.8 Setting
    public static String Sheet428DefaultProvince = "广东";
    public static int Sheet428TimeCellIndex = 0;
    public static int Sheet428RecordStartRow = 3;
    public static int Sheet428OrderCellIndex = 1;
    public static int Sheet428BatchCellIndex = 2;
    public static int Sheet428ProvinceCellIndex = 3;
    public static int Sheet428Start = 4;
    public static int Sheet428PersonalBlankStart = 11;
    public static int Sheet428PersonalBlankEnd = 11;
    public static int Sheet428CoreBlankStart = 8;
    public static int Sheet428CoreBlankEnd = 11;

    //Sheet 4.2.9 Setting
    public static String Sheet429DefaultProvince = "广东";
    public static int Sheet429TimeCellIndex = 0;
    public static int Sheet429RecordStartRow = 4;
    public static int Sheet429OrderCellIndex = 1;
    public static int Sheet429BatchCellIndex = 2;
    public static int Sheet429ProvinceCellIndex = 3;
    public static int Sheet429Start = 4;
    public static int Sheet429PersonalBlankStart = 5;
    public static int Sheet429PersonalBlankEnd = 6;
    public static int Sheet429CoreBlankStart = 7;
    public static int Sheet429CoreBlankEnd = 8;
}
