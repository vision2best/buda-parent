package top.vlsion.buda.encryption.annotation;

import org.springframework.context.annotation.Import;
import top.vlsion.buda.encryption.advice.BudaDecryptionRequestBodyAdvice;
import top.vlsion.buda.encryption.advice.BudaEncryptionResponseBodyAdvice;

import java.lang.annotation.*;

/**
 * @author : zhanghuang
 * @date : 2022-01-24 17:20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({BudaDecryptionRequestBodyAdvice.class,
        BudaEncryptionResponseBodyAdvice.class})
public @interface EnableBudaEncrypt {

}
