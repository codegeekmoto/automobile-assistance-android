package com.automobile.assistance.util.logging;

import java.util.HashMap;
import java.util.Map;

public class LoggerFactory {
    private static ILoggerFactory factoryimpl = null;
    private static Map<String, Logger> loggers = new HashMap<String, Logger>();

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String logname) {
        Logger logger = loggers.get(logname);
        // Found instantiated logger already, use it.
        if (logger != null) {
            return logger;
        }

        // Need Factory Impl
        if (factoryimpl == null) {
            try {
                Class<?> implClass = Class.forName(LoggerFactory.class.getName() + "Impl");
                factoryimpl = (ILoggerFactory) implClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }

        logger = factoryimpl.getLogger(logname);
        loggers.put(logname, logger);
        return logger;
    }
}
