package top.vlsion.buda.encryption;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

/**
 * 报文交互都需要使用Base64对明文进行转码
 * 第1步，将待转换的字符串每三个字节分为一组，每个字节占8bit，那么共有24个二进制位。
 * 第2步，将上面的24个二进制位每6个一组，共分为4组。
 * 第3步，在每组前面添加两个0，每组由6个变为8个二进制位，总共32个二进制位，即四个字节。
 * 第4步，根据Base64编码对照表（见下图）获得对应的值。
 *
 * @author : zhanghuang
 * @date : 2022-01-22 09:46
 */
public class BASE64Util {

    /**
     * @param input 原始串
     * @return 加密串
     */
    public static String encrypt(String input) {
        if (null == input || "".equals(input)) {
            throw new IllegalArgumentException("input can not be null or empty");
        }
        return encrypt(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param input byte[]
     * @return 加密串
     */
    public static String encrypt(byte[] input) {
        if (null == input) {
            throw new IllegalArgumentException("input can not be null or empty");
        }
        return Base64.encodeBase64String(input);
    }

    /**
     * @param input 加密串
     * @return 解密值
     */
    public static String decrypt(String input) {
        if (null == input || "".equals(input)) {
            throw new IllegalArgumentException("input can not be null or empty");
        }
        return new String(Base64.decodeBase64(input.getBytes(StandardCharsets.UTF_8)));
    }

    public static void main(String[] args) {
        System.out.println(encrypt("123"));
        System.out.println(decrypt(encrypt("123")));
    }
}
