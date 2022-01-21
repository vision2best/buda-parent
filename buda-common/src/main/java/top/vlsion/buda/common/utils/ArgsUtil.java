package top.vlsion.buda.common.utils;

/**
 * 参数校验工具
 *
 * @author : zhanghuang
 * @date : 2022-01-11 13:40
 */
public class ArgsUtil {

    /**
     * 对象不是null
     *
     * @param obj     判断的对象
     * @param argName 输出的参数名称
     */
    public static void notNull(Object obj, String argName) {
        if (null == obj) {
            throw new IllegalArgumentException(argName + "can not be null");
        }
    }

    /**
     * 对象不是null
     *
     * @param obj     判断的对象
     * @param argName 输出的参数名称
     * @param errMsg  指定输出信息
     */
    public static void notNull(Object obj, String argName, String errMsg) {
        if (null == obj) {
            String errorInfo = String.format("%s %s", argName, errMsg);
            throw new IllegalArgumentException(errorInfo);
        }
    }

    /**
     * 字符不是null
     *
     * @param string
     * @param name
     */
    public static void notEmpty(String string, String name) {
        if (null == string || "".equals(string)) {
            throw new IllegalArgumentException(name + " can not be null!");
        }
    }

    /**
     * 非负整数
     *
     * @param number
     * @param paramName
     */
    public static void notNegative(final int number, final String paramName) {
        if (number < 0) {
            throw new IllegalArgumentException(paramName + " must be >= 0!");
        }
    }

}
