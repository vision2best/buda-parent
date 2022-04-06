package top.vlsion.buda.encryption.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5加密的特点主要有以下几点：
 * <p>
 * 针对不同长度待加密的数据、字符串等等，其都可以返回一个固定长度的MD5加密字符串。（通常32位的16进制字符串）；
 * 其加密过程几乎不可逆，除非维护一个庞大的Key-Value数据库来进行碰撞破解，否则几乎无法解开。
 * 运算简便，且可实现方式多样，通过一定的处理方式也可以避免碰撞算法的破解。
 * 对于一个固定的字符串。数字等等，MD5加密后的字符串是固定的，也就是说不管MD5加密多少次，都是同样的结果。
 * ————————————————
 *
 * @author : zhanghuang
 * @date : 2022-01-22 09:46
 */
public class MD5Util {

    /**
     * @param input 加密值
     * @return 不加盐 32位 小写
     */
    public static String encrypt32Lower(String input) {
        if (null == input || "".equals(input)) {
            throw new IllegalArgumentException("input can not be null or empty");
        }
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            result = new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param input 加密值
     * @param slat  盐
     * @return 加盐 32位 小写
     */
    public static String encrypt32Lower(String input, String slat) {
        if (null == input || "".equals(input) || null == slat || "".equals(slat)) {
            throw new IllegalArgumentException("input or slat can not be null or empty");
        }
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            md.update(input.getBytes());
            byte[] digest = md.digest();
            result = new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param input 加密值
     * @return 不加盐 16位 小写
     */
    public static String encrypt16Lower(String input) {
        String str = encrypt32Lower(input);
        return null == str ? null : str.substring(8, 24);
    }

    /**
     * @param input 加密值
     * @param slat  盐
     * @return 加盐 16位 小写
     */
    public static String encrypt16Lower(String input, String slat) {
        String str = encrypt32Lower(input, slat);
        return null == str ? null : str.substring(8, 24);
    }

    /**
     * @param input 加密值
     * @return 不加盐 16位 大写
     */
    public static String encrypt16Upper(String input) {
        String str = encrypt32Lower(input);
        return null == str ? null : str.substring(8, 24).toUpperCase();
    }

    /**
     * @param input 加密值
     * @param slat  盐
     * @return 加盐 16位 大写
     */
    public static String encrypt16Upper(String input, String slat) {
        String str = encrypt32Lower(input, slat);
        return null == str ? null : str.substring(8, 24).toUpperCase();
    }


    /**
     * @param input 加密值
     * @return 不加盐 32位 大写
     */
    public static String encrypt32Upper(String input) {
        String str = encrypt32Lower(input);
        return null == str ? null : str.toUpperCase();
    }

    /**
     * @param input 加密值
     * @param slat  盐
     * @return 加盐 32位 大写
     */
    public static String encrypt32Upper(String input, String slat) {
        String str = encrypt32Lower(input, slat);
        return null == str ? null : str.toUpperCase();
    }
}
