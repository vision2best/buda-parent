package top.vlsion.buda.log.adaptors.slf4j;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.spi.LocationAwareLogger;
import top.vlsion.buda.log.core.Log;
import top.vlsion.buda.log.core.LogFactory;
import top.vlsion.buda.log.util.ExceptionUtil;
import top.vlsion.buda.log.util.LogUtil;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 15:48
 */
public class Slf4jLocationAwareLoggerImpl implements Log {
    private static final Marker MARKER = MarkerFactory.getMarker(LogFactory.MARKER);

    private static final String FQCN = Slf4jImpl.class.getName();

    private final LocationAwareLogger logger;

    Slf4jLocationAwareLoggerImpl(LocationAwareLogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean isDebugEnable() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isErrorEnable() {
        return logger.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnable() {
        return false;
    }

    @Override
    public boolean isInfoEnable() {
        return logger.isInfoEnabled();
    }

    @Override
    public boolean isTraceEnable() {
        return logger.isTraceEnabled();
    }

    @Override
    public boolean isWarnEnable() {
        return logger.isWarnEnabled();
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
        logger.log(MARKER, FQCN, LocationAwareLogger.ERROR_INT, s, null, e);
    }

    @Override
    public void error(String s) {
        logger.log(MARKER, FQCN, LocationAwareLogger.ERROR_INT, s, null, null);
    }

    @Override
    public void debug(String s) {
        logger.log(MARKER, FQCN, LocationAwareLogger.DEBUG_INT, s, null, null);
    }

    @Override
    public void trace(String s) {
        logger.log(MARKER, FQCN, LocationAwareLogger.TRACE_INT, s, null, null);
    }

    @Override
    public void warn(String s) {
        logger.log(MARKER, FQCN, LocationAwareLogger.WARN_INT, s, null, null);
    }

    @Override
    public void info(String s) {
        logger.log(MARKER, FQCN, LocationAwareLogger.INFO_INT, s, null, null);
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
        logger.log(MARKER, FQCN, LocationAwareLogger.ERROR_INT, LogUtil.getLogInfo(s, params), null, e);
    }

    @Override
    public void error(String s, Object... params) {
        logger.log(MARKER, FQCN, LocationAwareLogger.ERROR_INT, LogUtil.getLogInfo(s, params), null, null);
    }

    @Override
    public void debug(String s, Object... params) {
        logger.log(MARKER, FQCN, LocationAwareLogger.DEBUG_INT, LogUtil.getLogInfo(s, params), null, null);
    }

    @Override
    public void trace(String s, Object... params) {
        logger.log(MARKER, FQCN, LocationAwareLogger.TRACE_INT, LogUtil.getLogInfo(s, params), null, null);
    }

    @Override
    public void warn(String s, Object... params) {
        logger.log(MARKER, FQCN, LocationAwareLogger.WARN_INT, LogUtil.getLogInfo(s, params), null, null);
    }

    @Override
    public void info(String s, Object... params) {
        logger.log(MARKER, FQCN, LocationAwareLogger.INFO_INT, LogUtil.getLogInfo(s, params), null, null);
    }

}
