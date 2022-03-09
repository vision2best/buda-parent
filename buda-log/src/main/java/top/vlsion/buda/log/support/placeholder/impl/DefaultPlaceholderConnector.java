package top.vlsion.buda.log.support.placeholder.impl;

import top.vlsion.buda.log.support.placeholder.PlaceholderConnector;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 14:20
 */
public class DefaultPlaceholderConnector extends AbstractPlaceholderConnector {

    /**
     * 占位符
     */
    public static final String PLACEHOLDER = "{}";

    private static final PlaceholderConnector INSTANCE = new DefaultPlaceholderConnector();

    public static PlaceholderConnector getInstance() {
        return INSTANCE;
    }

    /**
     * 信息链接
     *
     * @param format 格式化
     * @param params 参数
     * @return 结果
     */
    @Override
    public String connect(String format, Object[] params) {
        if (!isContainsPlaceholder(format)) {
            return format;
        }
        if (params == null || params.length == 0) {
            return format;
        }
        return buildString(format, params);
    }


    /**
     * 是否包含占位符
     *
     * @param format str
     * @return boolean
     */
    private boolean isContainsPlaceholder(final String format) {
        if (format == null || format.length() == 0) {
            return false;
        }
        return format.contains(PLACEHOLDER);
    }


    /**
     * 构建字符串
     * "1 {} 2 {} " TO "1 %s 2 %s"
     *
     * @param format 格式化
     * @param params 参数列表
     * @return 构建的结果
     */
    private String buildString(String format, Object[] params) {
        String stringFormat = format;
        for (int i = 0; i < params.length; i++) {
            stringFormat = stringFormat.replaceFirst("\\{}", "%s");
        }
        return String.format(stringFormat, params);
    }

}
