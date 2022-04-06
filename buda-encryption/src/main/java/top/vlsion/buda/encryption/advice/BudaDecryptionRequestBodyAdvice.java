package top.vlsion.buda.encryption.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import top.vlsion.buda.encryption.annotation.BudaEncryption;
import top.vlsion.buda.encryption.condition.EnableEncryptionCondition;
import top.vlsion.buda.encryption.type.EncryptionTypeEnum;
import top.vlsion.buda.encryption.utils.AESUtil;
import top.vlsion.buda.encryption.utils.DESUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 前置解密处理逻辑
 *
 * @author : zhanghuang
 * @date : 2022-01-24 13:26
 */
@ControllerAdvice
@Conditional(value = {EnableEncryptionCondition.class})
public class BudaDecryptionRequestBodyAdvice implements RequestBodyAdvice {

    @Value("${buda.decrypt.secret:}")
    private String secret;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        BudaEncryption methodAnnotation = methodParameter.getMethodAnnotation(BudaEncryption.class);
        return null != methodAnnotation && methodAnnotation.decode();
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        EncryptionTypeEnum encryptionTypeEnum = EncryptionTypeEnum.AES;
        BudaEncryption methodAnnotation = methodParameter.getMethodAnnotation(BudaEncryption.class);
        if (methodAnnotation != null && !StringUtils.isEmpty(methodAnnotation.decryptionKey())) {
            secret = methodAnnotation.decryptionKey();
            encryptionTypeEnum = methodAnnotation.decryptionType();
        }
        if (StringUtils.isEmpty(secret)) {
            return httpInputMessage;
        } else {
            HttpHeaders headers = httpInputMessage.getHeaders();
            String body;
            String bodyStr = StreamUtils.copyToString(httpInputMessage.getBody(), StandardCharsets.UTF_8);
            if (encryptionTypeEnum == EncryptionTypeEnum.AES) {
                body = AESUtil.decrypt(bodyStr, secret);
            } else if (encryptionTypeEnum == EncryptionTypeEnum.DES) {
                body = DESUtil.decrypt(bodyStr, secret);
            } else {
                return httpInputMessage;
            }
            return new MyHttpInputMessage(headers, body != null ? body.getBytes(StandardCharsets.UTF_8) : new byte[0]);
        }
    }


    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    public static class MyHttpInputMessage implements HttpInputMessage {

        private final HttpHeaders headers;

        private final byte[] body;

        public MyHttpInputMessage(HttpHeaders headers, byte[] body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(body);
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
