package top.vlsion.buda.encryption.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 私钥加密公钥解密这种场景是用来验签：私钥签名后，只能由对应的公钥解密，公钥又是公开的（很多人可持有），所以这些人拿着公钥来解密，解密成功后就能判断出是持有私钥的人做的签名，验证了身份合法性。
 * 公钥加密私钥解密才是加解密：若用公钥加密，那只能由私钥解密，而私钥是私有不公开的，只能由特定的私钥持有人解密，保证的数据的安全性。
 *
 * @author : zhanghuang
 * @date : 2022-01-22 09:48
 */
public class RSAUtil {

    public final static String ALGORITHM = "RSA";
    public static final String PADDING = "RSA/ECB/PKCS1Padding";
    private static final int KEY_SIZE_1024 = 1024;
    private static final int KEY_SIZE_2048 = 2048;
    private static final String CHARSET = "UTF-8";
    /**
     * 签名类型
     */
    private static final String SIGNATURE_TYPE = "SHA256WithRSA";

    /**
     * 签名
     *
     * @param input      内容
     * @param privateKey 私钥
     * @return
     */
    public static String sign(String input, String privateKey) {
        PrivateKey priKey = getPrivateKey(privateKey);
        try {
            Signature signature = Signature.getInstance(SIGNATURE_TYPE);
            signature.initSign(priKey);
            signature.update(input.getBytes(CHARSET));
            return Base64.encodeBase64String(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证签名
     *
     * @param input     内容
     * @param sign      签名
     * @param publicKey 公钥
     * @return
     */
    public static boolean checkSign(String input, String sign, String publicKey) {
        PublicKey pubKey = getPublicKey(publicKey);
        try {
            Signature signature = Signature.getInstance(SIGNATURE_TYPE);
            signature.initVerify(pubKey);
            signature.update(input.getBytes(CHARSET));
            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 公钥加密
     *
     * @param input     密文
     * @param publicKey 公钥
     * @return 原文
     */
    public static String encryptByPublicKey(String input, String publicKey) {
        return encrypt(input, publicKey, true);
    }

    /**
     * 私钥加密
     *
     * @param input      密文
     * @param privateKey 私钥
     * @return 原文
     */
    public static String encryptByPrivateKey(String input, String privateKey) {
        return encrypt(input, privateKey, false);
    }

    /**
     * 公钥解密
     *
     * @param input     密文
     * @param publicKey 公钥
     * @return 原文
     */
    public static String decryptByPublicKey(String input, String publicKey) {
        return decrypt(input, publicKey, true);
    }

    /**
     * 私钥解密
     *
     * @param input      密文
     * @param privateKey 私钥
     * @return 原文
     */
    public static String decryptByPrivateKey(String input, String privateKey) {
        return decrypt(input, privateKey, false);
    }


    /**
     * 加密
     *
     * @param input      内容
     * @param encryptKey 密钥
     * @param isPublic   是否公钥
     * @return 加密内容
     */
    public static String encrypt(String input, String encryptKey, boolean isPublic) {
        Key key;
        if (isPublic) {
            key = getPublicKey(encryptKey);
        } else {
            key = getPrivateKey(encryptKey);
        }
        try {
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.encodeBase64String(cipher.doFinal(input.getBytes(CHARSET)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param input      内容
     * @param encryptKey 密钥
     * @param isPublic   是否公钥
     * @return 解密内容
     */
    public static String decrypt(String input, String encryptKey, boolean isPublic) {
        Key key;
        if (isPublic) {
            key = getPublicKey(encryptKey);
        } else {
            key = getPrivateKey(encryptKey);
        }
        try {
            Cipher cipher = Cipher.getInstance(PADDING);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(Base64.decodeBase64(input.getBytes(CHARSET)));
            return new String(bytes, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成 1024 length 的密匙对
     *
     * @return
     */
    public static KeyPair getKeyPair1024() {
        return getKeyPair(KEY_SIZE_1024);
    }


    /**
     * 生成 2048 length 的密匙对
     *
     * @return
     */
    public static KeyPair getKeyPair2048() {
        return getKeyPair(KEY_SIZE_2048);
    }


    /**
     * @return KeyFactory
     */
    public static KeyFactory getKeyFactory() {
        KeyFactory keyfactory = null;
        try {
            keyfactory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyfactory;
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥
     * @return 公钥
     */
    public static PublicKey getPublicKey(String publicKey) {
        KeyFactory keyFactory = getKeyFactory();
        try {
            byte[] decodedBytes = Base64.decodeBase64(publicKey.getBytes(CHARSET));
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodedBytes);
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥
     * @return 私钥
     */
    public static PrivateKey getPrivateKey(String privateKey) {
        KeyFactory keyFactory = getKeyFactory();
        try {
            byte[] decodedBytes = Base64.decodeBase64(privateKey.getBytes(CHARSET));
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodedBytes);
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取钥匙对
     *
     * @param length 长度
     * @return KeyPair
     */
    public static KeyPair getKeyPair(int length) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGen.initialize(length);
            return keyPairGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取公钥
     *
     * @param pair 钥匙对
     * @return publicKey
     */
    public static String getPublicKey(KeyPair pair) {
        return new String(Base64.encodeBase64(pair.getPublic().getEncoded()));
    }

    /**
     * 获取私钥
     *
     * @param pair 钥匙对
     * @return privateKey
     */
    public static String getPrivateKey(KeyPair pair) {
        return new String(Base64.encodeBase64(pair.getPrivate().getEncoded()));
    }

    public static void main(String[] args) {
        KeyPair keyPair1024 = getKeyPair1024();
        String privateKey = getPrivateKey(keyPair1024);
        String publicKey = getPublicKey(keyPair1024);
        String encrypt = encryptByPrivateKey("123", privateKey);
        System.out.println(encrypt);
        String decrypt = decryptByPublicKey(encrypt, publicKey);
        System.out.println(decrypt);

        String encrypt1 = encryptByPublicKey("123", publicKey);
        System.out.println(encrypt1);
        String decrypt1 = decryptByPrivateKey(encrypt1, privateKey);
        System.out.println(decrypt1);
        String sign = sign("123", privateKey);
        System.out.println(sign);
        System.out.println(checkSign("123", sign, publicKey));
    }
}
