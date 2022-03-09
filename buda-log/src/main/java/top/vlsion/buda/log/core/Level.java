package top.vlsion.buda.log.core;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 日志级别
 *
 * @author : zhanghuang
 * @date : 2022-03-09 10:51
 */
public final class Level implements Comparable<Level>, Serializable {

    private static final Level OFF;
    private static final Level FATAL;
    private static final Level ERROR;
    private static final Level WARN;
    private static final Level INFO;
    private static final Level DEBUG;
    private static final Level TRACE;
    private static final Level ALL;

    private static final ConcurrentMap<String, Level> LEVELS = new ConcurrentHashMap<>();

    static {
        OFF = new Level("OFF", Integer.MIN_VALUE);
        FATAL = new Level("FATAL", 100);
        ERROR = new Level("ERROR", 200);
        WARN = new Level("WARN", 300);
        INFO = new Level("INFO", 400);
        DEBUG = new Level("DEBUG", 500);
        TRACE = new Level("TRACE", 600);
        ALL = new Level("ALL", Integer.MAX_VALUE);
    }

    private final String name;
    private final int level;


    public Level(String name, int level) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Illegal null or empty Level name.");
        }
        this.name = name;
        this.level = level;
        if (LEVELS.putIfAbsent(name, this) != null) {
            throw new IllegalStateException("Level " + name + " has already been defined.");
        }
    }

    @Override
    public int compareTo(final Level o) {
        return Integer.compare(level, o.level);
    }
}
