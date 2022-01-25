package top.vlsion.buda.encryption.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.vlsion.buda.encryption.annotation.BudaEncryption;
import top.vlsion.buda.encryption.condition.EnableEncryptionCondition;
import top.vlsion.buda.encryption.type.EncryptionTypeEnum;
import top.vlsion.buda.encryption.utils.AESUtil;
import top.vlsion.buda.encryption.utils.DESUtil;

import java.nio.charset.StandardCharsets;

/**
 * 后置加密处理逻辑
 *
 * @author : zhanghuang
 * @date : 2022-01-24 13:31
 */
@ControllerAdvice
@Conditional(value = {EnableEncryptionCondition.class})
public class BudaEncryptionResponseBodyAdvice implements ResponseBodyAdvice {

    @Value("${buda.decrypt.secret:}")
    private String secret;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        BudaEncryption methodAnnotation = methodParameter.getMethodAnnotation(BudaEncryption.class);
        return null != methodAnnotation && methodAnnotation.encode();
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        EncryptionTypeEnum encryptionTypeEnum = EncryptionTypeEnum.AES;
        BudaEncryption methodAnnotation = methodParameter.getMethodAnnotation(BudaEncryption.class);
        if (methodAnnotation != null && !StringUtils.isEmpty(methodAnnotation.encryptionKey())) {
            secret = methodAnnotation.decryptionKey();
            encryptionTypeEnum = methodAnnotation.encryptionType();
        }
        if (StringUtils.isEmpty(secret)) {
            return o;
        }else {
            String bodyStr = JSONObject.toJSONString(o);
            String body;
            if (encryptionTypeEnum == EncryptionTypeEnum.AES) {
                body = AESUtil.encrypt(bodyStr, secret);
            } else if (encryptionTypeEnum == EncryptionTypeEnum.DES) {
                body = DESUtil.encrypt(bodyStr, secret);
            } else {
                return o;
            }
            return body;
        }
    }
}