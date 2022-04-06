package top.vlsion.buda.log.adaptors.jdk14;

import top.vlsion.buda.log.core.Log;
import top.vlsion.buda.log.util.ExceptionUtil;
import top.vlsion.buda.log.util.LogUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 13:47
 */
public class Jdk14LogImpl implements Log {

    private final Logger log;

    public Jdk14LogImpl(String clazz) {
        this.log = Logger.getLogger(clazz);
    }

    /**
     * 是否可 fatal
     *
     * @return true 是  false 否
     */
    @Override
    public boolean isFatalEnable() {
        return false;
    }

    /**
     * 是否可 error
     *
     * @return true 是  false 否
     */
    @Override
    public boolean isErrorEnable() {
        return log.isLoggable(Level.SEVERE);
    }

    /**
     * 是否可 warn
     *
     * @return true 是  false 否
     */
    @Override
    public boolean isWarnEnable() {
        return log.isLoggable(Level.WARNING);
    }

    /**
     * 是否可 info
     *
     * @return true 是  false 否
     */
    @Override
    public boolean isInfoEnable() {
        return log.isLoggable(Level.INFO);
    }

    /**
     * 是否可 debug
     *
     * @return true 是  false 否
     */
    @Override
    public boolean isDebugEnable() {
        return log.isLoggable(Level.FINE);
    }

    /**
     * 是否可 trace
     *
     * @return true 是  false 否
     */
    @Override
    public boolean isTraceEnable() {
        return log.isLoggable(Level.FINER);
    }

    /**
     * 打印 fatal 级别信息
     *
     * @param s 字符串
     * @param e 异常信息
     */
    @Override
    public void fatal(String s, Throwable e) {
        ExceptionUtil.throwUnsupportedOperationException();
    }

    /**
     * 打印 fatal 级别信息
     *
     * @param s 字符串
     */
    @Override
    public void fatal(String s) {
        ExceptionUtil.throwUnsupportedOperationException();
    }

    /**
     * 打印 error 级别信息
     *
     * @param s 字符串
     * @param e 异常
     */
    @Override
    public void error(String s, Throwable e) {
        log.log(Level.SEVERE, s, e);
    }

    /**
     * 打印 error 级别信息
     *
     * @param s 字符串
     */
    @Override
    public void error(String s) {
        log.log(Level.SEVERE, s);
    }

    /**
     * 打印 debug 级别信息
     *
     * @param s 字符串
     */
    @Override
    public void debug(String s) {
        log.log(Level.FINE, s);
    }

    /**
     * 打印 trace 级别信息
     *
     * @param s 字符串
     */
    @Override
    public void trace(String s) {
        log.log(Level.FINER, s);
    }

    /**
     * 打印 warn 级别信息
     *
     * @param s 字符串
     */
    @Override
    public void warn(String s) {
        log.log(Level.WARNING, s);
    }

    /**
     * 打印 info 级别信息
     *
     * @param s 字符串
     */
    @Override
    public void info(String s) {
        log.log(Level.INFO, s);
    }

    /**
     * 打印 fatal 级别信息
     *
     * @param s      字符串
     * @param e      异常信息
     * @param params 可变参数
     */
    @Override
    public void fatal(String s, Throwable e, Object... params) {
        ExceptionUtil.throwUnsupportedOperationException();
    }

    /**
     * 打印 fatal 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    @Override
    public void fatal(String s, Object... params) {
        ExceptionUtil.throwUnsupportedOperationException();
    }

    /**
     * 打印 error 级别信息
     *
     * @param s      字符串
     * @param e      异常
     * @param params 可变参数
     */
    @Override
    public void error(String s, Throwable e, Object... params) {
        log.log(Level.SEVERE, LogUtil.getLogInfo(s, params), e);
    }

    /**
     * 打印 error 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    @Override
    public void error(String s, Object... params) {
        log.log(Level.SEVERE, LogUtil.getLogInfo(s, params));
    }

    /**
     * 打印 debug 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    @Override
    public void debug(String s, Object... params) {
        log.log(Level.FINE, LogUtil.getLogInfo(s, params));
    }

    /**
     * 打印 trace 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    @Override
    public void trace(String s, Object... params) {
        log.log(Level.FINER, LogUtil.getLogInfo(s, params));
    }

    /**
     * 打印 warn 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    @Override
    public void warn(String s, Object... params) {
        log.log(Level.WARNING, LogUtil.getLogInfo(s, params));
    }

    /**
     * 打印 info 级别信息
     *
     * @param s      字符串
     * @param params 可变参数
     */
    @Override
    public void info(String s, Object... params) {
        log.log(Level.INFO, LogUtil.getLogInfo(s, params));
    }
}
