package top.vlsion.buda.encryption.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import top.vlsion.buda.encryption.annotation.EnableBudaEncrypt;

/**
 * @author : zhanghuang
 * @date : 2022-01-24 14:58
 */
public class EnableEncryptionCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        if (null == beanFactory) {
            return false;
        }
        String[] names = beanFactory.getBeanNamesForAnnotation(EnableBudaEncrypt.class);
        return names.length > 0;
    }
}