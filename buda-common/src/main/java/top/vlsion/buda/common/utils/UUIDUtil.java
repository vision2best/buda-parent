package top.vlsion.buda.common.utils;

import java.util.UUID;

/**
 * UUID
 * 太长不适合做主键性能太差，可以作为文件名这些
 *
 * @author : zhanghuang
 * @date : 2022-01-14 15:24
 */
public class UUIDUtil {

    /**
     * 生成UUID 32bit
     *
     * @return ex.9bcb879c-1779-4c49-b75c-3666a8a4b077
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成没有分割符的UUID 36bit
     *
     * @return ex.9bcb879c-1779-4c49-b75c-3666a8a4b077
     */
    public static String getUUIDNoSplit() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成没有分割符的UUID 12bit
     *
     * @return ex.3666a8a4b077
     */
    public static String getUUID12bit() {
        String[] split = UUID.randomUUID().toString().split("-");
        return split[split.length - 1];
    }
}
