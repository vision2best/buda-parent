package top.vlsion.buda.log.error;

import org.jetbrains.annotations.PropertyKey;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author : zhanghuang
 * @date : 2022-04-06 11:03
 */
public class ErrorMessages {
    private static final String BUNDLE_FQN = "ErrorMessages-zh_CN";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_FQN, new Locale("zh", "CN"));

    public static String message(@PropertyKey(resourceBundle = BUNDLE_FQN) String key, Object... params) {
        if (RESOURCE_BUNDLE.containsKey(key)) {
            String value = RESOURCE_BUNDLE.getString(key);
            final FormattingTuple tuple = MessageFormatter.arrayFormat(value, params);
            return key + " - " + tuple.getMessage();
        } else {
            return MessageFormatter.arrayFormat(key, params).getMessage();
        }
    }
}
