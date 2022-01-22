package top.vlsion.buda.encryption;

import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author : zhanghuang
 * @date : 2022-01-22 09:47
 */
public class AESUtil {
    private static final String ALGORITHM = "AES";
    private static final String PADDING = "AES/ECB/PKCS5Padding";
    private static final int LENGTH = 16;

    /**
     * @param input      原始值
     * @param encryptKey 密钥 长度==16
     * @return 加密值
     */
    public static String encrypt(String input, String encryptKey) {
        if (null == input || null == encryptKey) {
            throw new IllegalArgumentException("input or key can not be null");
        }
        if (encryptKey.length() != LENGTH) {
            throw new IllegalArgumentException("encryptKey length !=16");
        }
        try {
            SecretKeySpec desKeySpec = new SecretKeySpec(encryptKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, desKeySpec);
            return BASE64Util.encrypt(cipher.doFinal(input.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param input      加密值
     * @param decryptKey 密钥 长度==16
     * @return 原始值
     */
    public static String decrypt(String input, String decryptKey) {
        if (null == input || null == decryptKey) {
            throw new IllegalArgumentException("input or key can not be null");
        }
        if (decryptKey.length() != LENGTH) {
            throw new IllegalArgumentException("decryptKey length !=16");
        }
        try {
            SecretKeySpec desKeySpec = new SecretKeySpec(decryptKey.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE, desKeySpec);
            return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(input)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
