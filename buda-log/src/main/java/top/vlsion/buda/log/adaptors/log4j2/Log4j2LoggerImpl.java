package top.vlsion.buda.log.adaptors.log4j2;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import top.vlsion.buda.log.core.Log;
import top.vlsion.buda.log.core.LogFactory;
import top.vlsion.buda.log.util.LogUtil;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 15:14
 */
public class Log4j2LoggerImpl implements Log {
    private static Marker MARKER = MarkerManager.getMarker(LogFactory.MARKER);

    private Logger log;

    public Log4j2LoggerImpl(Logger logger) {
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
        return log.isFatalEnabled();
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
        return log.isWarnEnabled();
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
        log.error(MARKER, s, e);
    }

    @Override
    public void error(String s) {
        log.error(MARKER, s);
    }

    @Override
    public void debug(String s) {
        log.debug(MARKER, s);
    }

    @Override
    public void trace(String s) {
        log.trace(MARKER, s);
    }

    @Override
    public void warn(String s) {
        log.warn(MARKER, s);
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
        log.error(MARKER, LogUtil.getLogInfo(s, params), e);
    }

    @Override
    public void error(String s, Object... params) {
        log.error(MARKER, LogUtil.getLogInfo(s, params));
    }

    @Override
    public void debug(String s, Object... params) {
        log.debug(MARKER, LogUtil.getLogInfo(s, params));
    }

    @Override
    public void trace(String s, Object... params) {
        log.trace(MARKER, LogUtil.getLogInfo(s, params));
    }

    @Override
    public void warn(String s, Object... params) {
        log.warn(MARKER, LogUtil.getLogInfo(s, params));
    }

    @Override
    public void info(String s, Object... params) {
        log.info(LogUtil.getLogInfo(s, params));
    }

}
