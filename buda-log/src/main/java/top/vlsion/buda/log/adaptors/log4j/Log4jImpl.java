package top.vlsion.buda.log.adaptors.log4j;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import top.vlsion.buda.log.core.Log;
import top.vlsion.buda.log.util.LogUtil;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 15:09
 */
public class Log4jImpl implements Log {

    private static final String FQCN = Log4jImpl.class.getName();

    private Logger log;

    public Log4jImpl(String clazz) {
        log = Logger.getLogger(clazz);
    }

    @Override
    public boolean isDebugEnable() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isErrorEnable() {
        return log.isEnabledFor(Level.ERROR);
    }

    @Override
    public boolean isFatalEnable() {
        return log.isEnabledFor(Level.FATAL);
    }

    @Override
    public boolean isInfoEnable() {
        return log.isEnabledFor(Level.INFO);
    }

    @Override
    public boolean isTraceEnable() {
        return log.isTraceEnabled();
    }

    @Override
    public boolean isWarnEnable() {
        return log.isEnabledFor(Level.WARN);
    }

    @Override
    public void fatal(String s, Throwable e) {
        log.log(FQCN, Level.FATAL, s, e);
    }

    @Override
    public void fatal(String s) {
        log.log(FQCN, Level.FATAL, s, null);
    }

    @Override
    public void error(String s, Throwable e) {
        log.log(FQCN, Level.ERROR, s, e);
    }

    @Override
    public void error(String s) {
        log.log(FQCN, Level.ERROR, s, null);
    }

    @Override
    public void debug(String s) {
        log.log(FQCN, Level.DEBUG, s, null);
    }

    @Override
    public void trace(String s) {
        log.log(FQCN, Level.TRACE, s, null);
    }

    @Override
    public void warn(String s) {
        log.log(FQCN, Level.WARN, s, null);
    }

    @Override
    public void info(String s) {
        log.log(FQCN, Level.INFO, s, null);
    }

    //region 可变参数
    @Override
    public void fatal(String s, Throwable e, Object... params) {
        log.log(FQCN, Level.FATAL, LogUtil.getLogInfo(s, params), e);
    }

    @Override
    public void fatal(String s, Object... params) {
        log.log(FQCN, Level.FATAL, LogUtil.getLogInfo(s, params), null);
    }

    @Override
    public void error(String s, Throwable e, Object... params) {
        log.log(FQCN, Level.ERROR, LogUtil.getLogInfo(s, params), e);
    }

    @Override
    public void error(String s, Object... params) {
        log.log(FQCN, Level.ERROR, LogUtil.getLogInfo(s, params), null);
    }

    @Override
    public void debug(String s, Object... params) {
        log.log(FQCN, Level.DEBUG, LogUtil.getLogInfo(s, params), null);
    }

    @Override
    public void trace(String s, Object... params) {
        log.log(FQCN, Level.TRACE, LogUtil.getLogInfo(s, params), null);
    }

    @Override
    public void warn(String s, Object... params) {
        log.log(FQCN, Level.WARN, LogUtil.getLogInfo(s, params), null);
    }

    @Override
    public void info(String s, Object... params) {
        log.log(FQCN, Level.INFO, LogUtil.getLogInfo(s, params), null);
    }
    //endregion
}
