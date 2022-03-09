package top.vlsion.buda.log.adaptors.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.spi.LocationAwareLogger;
import top.vlsion.buda.log.core.Log;
import top.vlsion.buda.log.util.LogUtil;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 15:48
 */
public class Slf4jImpl implements Log {
    private Log log;

    public Slf4jImpl(String clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);

        try {
            // check for slf4j >= 1.6 method signature
            logger.getClass().getMethod("log", Marker.class, String.class, int.class, String.class, Object[].class, Throwable.class);
            log = new Slf4jLocationAwareLoggerImpl((LocationAwareLogger) logger);
            return;
        } catch (SecurityException | NoSuchMethodException e) {
            // fail-back to Slf4jLoggerImpl
        }
        if (logger instanceof LocationAwareLogger) {
            // do nothing...
        }

        // Logger is not LocationAwareLogger or slf4j version < 1.6
        log = new Slf4jLoggerImpl(logger);
    }

    @Override
    public boolean isDebugEnable() {
        return log.isDebugEnable();
    }

    @Override
    public boolean isErrorEnable() {
        return log.isErrorEnable();
    }

    @Override
    public boolean isFatalEnable() {
        return log.isFatalEnable();
    }

    @Override
    public boolean isInfoEnable() {
        return log.isInfoEnable();
    }

    @Override
    public boolean isTraceEnable() {
        return log.isTraceEnable();
    }

    @Override
    public boolean isWarnEnable() {
        return log.isWarnEnable();
    }

    @Override
    public void fatal(String s, Throwable e) {
        log.fatal(s, e);
    }

    @Override
    public void fatal(String s) {
        log.fatal(s);
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
        log.fatal(LogUtil.getLogInfo(s, params), e);
    }

    @Override
    public void fatal(String s, Object... params) {
        log.fatal(LogUtil.getLogInfo(s, params));
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
