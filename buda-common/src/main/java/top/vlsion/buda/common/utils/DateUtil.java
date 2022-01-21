package top.vlsion.buda.common.utils;

/**
 * @author : zhanghuang
 * @date : 2022-01-14 15:41
 */
public class DateUtil {
    /**
     * 获取系统当前时间戳 13位
     *
     * @return ex.1642146214308
     */
    public static String getCurrentTimeStr() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取系统当前时间戳long型 13位
     *
     * @return ex.1642146214308
     */
    public static long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }
}
