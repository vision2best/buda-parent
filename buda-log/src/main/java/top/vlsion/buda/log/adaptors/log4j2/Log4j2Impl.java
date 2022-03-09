package top.vlsion.buda.log.adaptors.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.AbstractLogger;
import top.vlsion.buda.log.core.Log;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 15:13
 */
public class Log4j2Impl implements Log {
    private Log log;

    public Log4j2Impl(String clazz) {
        Logger logger = LogManager.getLogger(clazz);

        if (logger instanceof AbstractLogger) {
            log = new Log4j2AbstractLoggerImpl((AbstractLogger) logger);
        } else {
            log = new Log4j2LoggerImpl(logger);
        }
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
        log.fatal(s, e, params);
    }

    @Override
    public void fatal(String s, Object... params) {
        log.fatal(s, params);
    }

    @Override
    public void error(String s, Throwable e, Object... params) {
        log.error(s, e, params);
    }

    @Override
    public void error(String s, Object... params) {
        log.error(s, params);
    }

    @Override
    public void debug(String s, Object... params) {
        log.debug(s, params);
    }

    @Override
    public void trace(String s, Object... params) {
        log.trace(s, params);
    }

    @Override
    public void warn(String s, Object... params) {
        log.warn(s, params);
    }

    @Override
    public void info(String s, Object... params) {
        log.info(s, params);
    }

}
