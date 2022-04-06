package top.vlsion.buda.log.core;

import top.vlsion.buda.log.adaptors.commons.JakartaCommonsLoggingImpl;
import top.vlsion.buda.log.adaptors.jdk14.Jdk14LogImpl;
import top.vlsion.buda.log.adaptors.log4j2.Log4j2Impl;
import top.vlsion.buda.log.adaptors.slf4j.Slf4jImpl;
import top.vlsion.buda.log.exception.LogRuntimeException;

import java.lang.reflect.Constructor;

/**
 * @author : zhanghuang
 * @date : 2022-03-09 10:51
 */
public class LogFactory {

    public static final String MARKER = "BUDA-LOG";

    private static Constructor<? extends Log> logConstructor;

    //顺序
    static {
        useSlf4jLogging();
        useLog4J2Logging();
        useCommonsLogging();
        useJdkLogging();
    }

    private LogFactory() {
    }

    private static void useSlf4jLogging() {
        setImplementation(Slf4jImpl.class);
    }

    private static void useLog4J2Logging() {
        setImplementation(Log4j2Impl.class);
    }

    private static void useCommonsLogging() {
        setImplementation(JakartaCommonsLoggingImpl.class);
    }

    public static void useJdkLogging() {
        setImplementation(Jdk14LogImpl.class);
    }

    public static Log getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    private  static Log getLog(String logger) {
        try {
            return logConstructor.newInstance(logger);
        } catch (Exception e) {
            throw new LogRuntimeException("Error creating logger for logger " + logger + ".  Cause: " + e, e);
        }

    }

    private static void setImplementation(Class<? extends Log> implClass) {
        try {
            //如果已经实现，则直接跳过
            if (logConstructor != null) {
                return;
            }

            Constructor<? extends Log> candidate = implClass.getConstructor(String.class);
            Log log = candidate.newInstance(LogFactory.class.getName());
            log.debug("Logging initialized using '" + implClass + "' adapter.");
            logConstructor = candidate;
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
