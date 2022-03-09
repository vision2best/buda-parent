package top.vlsion.buda.log.adaptors.slf4j;

import org.slf4j.Logger;
import top.vlsion.buda.log.core.Log;
import top.vlsion.buda.log.util.ExceptionUtil;
import top.vlsion.buda.log.util.LogUtil;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 15:49
 */
public class Slf4jLoggerImpl implements Log {

    private Logger log;

    public Slf4jLoggerImpl(Logger logger) {
        log = logger;
    }

    @Override
    public boolean isDebugEnable() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isErrorEnable() {
        return log.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnable() {
        return false;
    }

    @Override
    public boolean isInfoEnable() {
        return log.isInfoEnabled();
    }

    @Override
    public boolean isTraceEnable() {
        return log.isTraceEnabled();
    }

    @Override
    public boolean isWarnEnable() {
        return false;
    }

    @Override
    public void fatal(String s, Throwable e) {
        ExceptionUtil.throwUnsupportedOperationException();
    }

    @Override
    public void fatal(String s) {
        ExceptionUtil.throwUnsupportedOperationException();
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s, e);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void debug(String s) {
        log.debug(s);
    }

    @Override
    public void trace(String s) {
        log.trace(s);
    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }

    @Override
    public void info(String s) {
        log.info(s);
    }

    @Override
    public void fatal(String s, Throwable e, Object... params) {
        ExceptionUtil.throwUnsupportedOperationException();
    }

    @Override
    public void fatal(String s, Object... params) {
        ExceptionUtil.throwUnsupportedOperationException();
    }

    @Override
    public void error(String s, Throwable e, Object... params) {
        log.error(LogUtil.getLogInfo(s, params), e);
    }

    @Override
    public void error(String s, Object... params) {
        log.error(LogUtil.getLogInfo(s, params));
    }

    @Override
    public void debug(String s, Object... params) {
        log.debug(LogUtil.getLogInfo(s, params));
    }

    @Override
    public void trace(String s, Object... params) {
        log.trace(LogUtil.getLogInfo(s, params));
    }

    @Override
    public void warn(String s, Object... params) {
        log.warn(LogUtil.getLogInfo(s, params));
    }

    @Override
    public void info(String s, Object... params) {
        log.info(LogUtil.getLogInfo(s, params));
    }

}
