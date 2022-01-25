package top.vlsion.buda.encryption.annotation;

import top.vlsion.buda.encryption.type.EncryptionTypeEnum;

import java.lang.annotation.*;

/**
 * 加解密注解
 *
 * @author : zhanghuang
 * @date : 2022-01-24 13:19
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BudaEncryption {

    /**
     * 请求参数是否解密
     */
    boolean decode() default true;

    /**
     * 解密方式 默认AES
     */
    EncryptionTypeEnum decryptionType() default EncryptionTypeEnum.AES;

    /**
     * 解密key
     */
    String decryptionKey() default "";

    /**
     * 响应结果是否加密
     */
    boolean encode() default true;

    /**
     * 加密方式 默认AES
     */
    EncryptionTypeEnum encryptionType() default EncryptionTypeEnum.AES;

    /**
     * 加密key
     */
    String encryptionKey() default "";

}
