package top.vlsion.buda.log.adaptors.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;
import top.vlsion.buda.log.core.Log;
import top.vlsion.buda.log.core.LogFactory;
import top.vlsion.buda.log.util.LogUtil;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 15:14
 */
public class Log4j2AbstractLoggerImpl implements Log {
    private static final Marker MARKER = MarkerManager.getMarker(LogFactory.MARKER);

    private static final String FQCN = Log4j2Impl.class.getName();

    private final ExtendedLoggerWrapper log;

    public Log4j2AbstractLoggerImpl(AbstractLogger abstractLogger) {
        log = new ExtendedLoggerWrapper(abstractLogger, abstractLogger.getName(), abstractLogger.getMessageFactory());
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
        log.logIfEnabled(FQCN, Level.FATAL, MARKER, s, e);
    }

    @Override
    public void fatal(String s) {
        log.logIfEnabled(FQCN, Level.FATAL, MARKER, s, (Throwable) null);
    }

    @Override
    public void error(String s, Throwable e) {
        log.logIfEnabled(FQCN, Level.ERROR, MARKER, s, e);
    }

    @Override
    public void error(String s) {
        Throwable throwable = null;
        log.logIfEnabled(FQCN, Level.ERROR, MARKER, s, (Throwable) null);
    }

    @Override
    public void debug(String s) {
        Throwable throwable = null;
        log.logIfEnabled(FQCN, Level.DEBUG, MARKER, s, (Throwable) null);
    }

    @Override
    public void trace(String s) {
        Throwable throwable = null;
        log.logIfEnabled(FQCN, Level.TRACE, MARKER, s, (Throwable) null);
    }

    @Override
    public void warn(String s) {
        Throwable throwable = null;
        log.logIfEnabled(FQCN, Level.WARN, MARKER, s, (Throwable) null);
    }

    @Override
    public void info(String s) {
        Throwable throwable = null;
        log.logIfEnabled(FQCN, Level.INFO, MARKER, s, (Throwable) null);
    }

    @Override
    public void fatal(String s, Throwable e, Object... params) {
        log.logIfEnabled(FQCN, Level.FATAL, MARKER, LogUtil.getLogInfo(s, params), e);
    }

    @Override
    public void fatal(String s, Object... params) {
        log.logIfEnabled(FQCN, Level.FATAL, MARKER, LogUtil.getLogInfo(s, params), (Throwable) null);
    }

    @Override
    public void error(String s, Throwable e, Object... params) {
        log.logIfEnabled(FQCN, Level.ERROR, MARKER, LogUtil.getLogInfo(s, params), e);
    }

    @Override
    public void error(String s, Object... params) {
        log.logIfEnabled(FQCN, Level.ERROR, MARKER, LogUtil.getLogInfo(s, params), (Throwable) null);
    }

    @Override
    public void debug(String s, Object... params) {
        log.logIfEnabled(FQCN, Level.DEBUG, MARKER, LogUtil.getLogInfo(s, params), (Throwable) null);
    }

    @Override
    public void trace(String s, Object... params) {
        log.logIfEnabled(FQCN, Level.TRACE, MARKER, LogUtil.getLogInfo(s, params), (Throwable) null);
    }

    @Override
    public void warn(String s, Object... params) {
        log.logIfEnabled(FQCN, Level.WARN, MARKER, LogUtil.getLogInfo(s, params), (Throwable) null);
    }

    @Override
    public void info(String s, Object... params) {
        log.logIfEnabled(FQCN, Level.INFO, MARKER, LogUtil.getLogInfo(s, params), (Throwable) null);
    }
}
