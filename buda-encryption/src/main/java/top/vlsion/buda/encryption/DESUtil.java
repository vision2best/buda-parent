package top.vlsion.buda.encryption;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;


/**
 * @author : zhanghuang
 * @date : 2022-01-22 09:46
 */
public class DESUtil {
    private static final String ALGORITHM = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";
    private static final int LENGTH = 8;

    /**
     * @param input      原始值
     * @param encryptKey 密钥 长度>=8
     * @return 加密值
     */
    public static String encrypt(String input, String encryptKey) {
        if (null == input || null == encryptKey) {
            throw new IllegalArgumentException("input or key can not be null");
        }
        if (encryptKey.length() < LENGTH) {
            throw new IllegalArgumentException("encryptKey length >=8");
        }
        try {
            DESKeySpec desKeySpec = new DESKeySpec(encryptKey.getBytes(StandardCharsets.UTF_8)); //指定一个 DES 密钥。
            SecretKeyFactory des = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = des.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String(cipher.doFinal(input.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param input      加密值
     * @param decryptKey 密钥 长度>=8
     * @return 原始值
     */
    public static String decrypt(String input, String decryptKey) {
        if (null == input || null == decryptKey) {
            throw new IllegalArgumentException("input or key can not be null");
        }
        if (decryptKey.length() < LENGTH) {
            throw new IllegalArgumentException("decryptKey length >=8");
        }
        try {
            DESKeySpec desKeySpec = new DESKeySpec(decryptKey.getBytes(StandardCharsets.UTF_8)); //指定一个 DES 密钥。
            SecretKeyFactory des = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = des.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(input.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String encrypt = encrypt("123", "12345678");
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt,"12345678"));
    }

}
