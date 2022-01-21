package top.vlsion.buda.common.utils;

/**
 * @author : zhanghuang
 * @date : 2022-01-14 15:26
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(UUIDUtil.getUUID());
        System.out.println(UUIDUtil.getUUIDNoSplit());
        System.out.println(UUIDUtil.getUUID12bit());
        System.out.println(DateUtil.getCurrentTimeStr());
        System.out.println( (-1L ^ (-1L << 12)));
    }
}
