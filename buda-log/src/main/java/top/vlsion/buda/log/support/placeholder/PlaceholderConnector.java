package top.vlsion.buda.log.support.placeholder;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 14:18
 */
public interface PlaceholderConnector {

    /**
     * 信息链接
     * @param format 格式化
     * @param params 参数
     * @return 结果
     */
    String connect(final String format, Object[] params);
}
