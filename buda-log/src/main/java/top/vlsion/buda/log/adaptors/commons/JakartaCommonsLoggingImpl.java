package top.vlsion.buda.log.adaptors.commons;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import top.vlsion.buda.log.util.LogUtil;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 14:47
 */
public class JakartaCommonsLoggingImpl implements top.vlsion.buda.log.core.Log {

    private Log log;

    public JakartaCommonsLoggingImpl(String clazz) {
        log = LogFactory.getLog(clazz);
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

    //region varParam
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
