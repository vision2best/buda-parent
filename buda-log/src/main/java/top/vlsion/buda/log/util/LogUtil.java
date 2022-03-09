package top.vlsion.buda.log.util;

import top.vlsion.buda.log.support.placeholder.impl.DefaultPlaceholderConnector;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 14:15
 */
public final class LogUtil {
    private LogUtil() {
    }

    public static String getLogInfo(String s, Object[] params) {
        return DefaultPlaceholderConnector.getInstance().connect(s, params);
    }
}
