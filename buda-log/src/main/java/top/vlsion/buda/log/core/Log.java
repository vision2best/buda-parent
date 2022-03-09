package top.vlsion.buda.log.core;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 10:51
 */
public interface Log {

    /**
     * 是否可 fatal
     *
     * @return true 是  false 否
     */
    boolean isFatalEnable();

    /**
     * 是否可 error
     *
     * @return true 是  false 否
     */
    boolean isErrorEnable();

    /**
     * 是否可 warn
     *
     * @return true 是  false 否
     */
    boolean isWarnEnable();

    /**
     * 是否可 info
     *
     * @return true 是  false 否
     */
    boolean isInfoEnable();

    /**
     * 是否可 debug
     *
     * @return true 是  false 否
     */
    boolean isDebugEnable();

    /**
     * 是否可 trace
     *
     * @return true 是  false 否
     */
    boolean isTraceEnable();


    /**
     * 打印 fatal 级别信息
     *
     * @param s 字符串
     * @param e 异常信息
     */
    void fatal(String s, Throwable e);

    /**
     * 打印 fatal 级别信息
     *
     * @param s 字符串
     */
    void fatal(String s);

    /**
     * 打印 error 级别信息
     *
     * @param s 字符串
     * @param e 异常
     */
    void error(String s, Throwable e);

    /**
     * 打印 error 级别信息
     *
     * @param s 字符串
     */
    void error(String s);

    /**
     * 打印 debug 级别信息
     *
     * @param s 字符串
     */
    void debug(String s);

    /**
     * 打印 trace 级别信息
     *
     * @param s 字符串
     */
    void trace(String s);

    /**
     * 打印 warn 级别信息
     *
     * @param s 字符串
     */
    void warn(String s);

    /**
     * 打印 info 级别信息
     *
     * @param s 字符串
     */
    void info(String s);


    /**
     * 打印 fatal 级别信息
     *
     * @param s      字符串
     * @param e      异常信息
     * @param params 可变参数
     */
    void fatal(String s, Throwable e, Object... params);

    /**
     * 打印 fatal 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    void fatal(String s, Object... params);

    /**
     * 打印 error 级别信息
     *
     * @param s      字符串
     * @param e      异常
     * @param params 可变参数
     */
    void error(String s, Throwable e, Object... params);

    /**
     * 打印 error 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    void error(String s, Object... params);

    /**
     * 打印 debug 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    void debug(String s, Object... params);

    /**
     * 打印 trace 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    void trace(String s, Object... params);

    /**
     * 打印 warn 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    void warn(String s, Object... params);

    /**
     * 打印 info 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    void info(String s, Object... params);

}
