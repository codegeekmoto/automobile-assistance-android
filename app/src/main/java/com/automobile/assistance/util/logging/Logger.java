package com.automobile.assistance.util.logging;

public abstract class Logger {
    public static final int NONE = 7;
    public static final int WTF = 6;
    public static final int ERROR = 5;
    public static final int WARN = 4;
    public static final int INFO = 3;
    public static final int DEBUG = 2;
    public static final int VERBOSE = 1;
    public static final int ALL = 0;

    protected int level;
    protected String name;

    public abstract void debug(String format, Object... args);

    public abstract void debug(Throwable t, String format, Object... args);

    public abstract void error(String format, Object... args);

    public abstract void error(Throwable t, String format, Object... args);

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public abstract void info(String format, Object... args);

    public abstract void info(Throwable t, String format, Object... args);

    public boolean isDebugEnabled() {
        return (this.level <= Logger.DEBUG);
    }

    public boolean isErrorEnabled() {
        return (this.level <= Logger.ERROR);
    }

    public boolean isInfoEnabled() {
        return (this.level <= Logger.INFO);
    }

    public boolean isVerboseEnabled() {
        return (this.level <= Logger.VERBOSE);
    }

    public boolean isWarnEnabled() {
        return (this.level <= Logger.WARN);
    }

    public boolean isWtfEnabled() {
        return (this.level <= Logger.WTF);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract void verbose(String format, Object... args);

    public abstract void verbose(Throwable t, String format, Object... args);

    public abstract void warn(String format, Object... args);

    public abstract void warn(Throwable t, String format, Object... args);

    public abstract void wtf(String format, Object... args);

    public abstract void wtf(Throwable t, String format, Object... args);
}
