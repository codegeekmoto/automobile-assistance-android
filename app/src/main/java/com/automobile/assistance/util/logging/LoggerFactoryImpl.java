package com.automobile.assistance.util.logging;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class LoggerFactoryImpl implements ILoggerFactory {
    private static final String ROOT = "\t";
    private Map<String, Integer> loglevels;

    public LoggerFactoryImpl() {
        loglevels = new HashMap<String, Integer>();

        try {
            Class<?> initClass = Class.forName("com.automobile.assistance.util.logging.AndroidLoggingInit");
            if (initClass == null) {
                return;
            }
            IAndroidLoggingInit init = (IAndroidLoggingInit) initClass.newInstance();
            loglevels.put(ROOT, init.getRootLevel());
            loglevels.putAll(init.getLogLevels());
        } catch (Exception e) {
            Log.e("Android.Logger", "Unable to init configuration", e);
        }
    }

    public Logger getLogger(String logname) {
        Logger logger = new AndroidLogger(logname);
        StringTokenizer tok = new StringTokenizer(logname, ".");
        StringBuilder prefix = new StringBuilder();
        boolean delim = false;
        Integer level;
        while (tok.hasMoreTokens()) {
            if (delim) {
                prefix.append('.');
            }
            prefix.append(tok.nextToken());
            level = loglevels.get(prefix.toString());
            if (level != null) {
                logger.setLevel(level);
            }
            delim = true;

            //Disable logging on release builds
//            if (AppConstants.DISABLE_LOGGING) {
//                logger.setLevel(Logger.NONE);
//            }
        }
        return logger;
    }
}
