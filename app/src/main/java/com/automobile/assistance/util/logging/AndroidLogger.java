package com.automobile.assistance.util.logging;

import android.util.Log;

public class AndroidLogger extends Logger {
    private String tag;

    public AndroidLogger(String logName) {
        super.name = logName;
        int idx = logName.lastIndexOf('.');
        if (idx > 0) {
            this.tag = logName.substring(idx + 1);
            int idxInner = this.tag.lastIndexOf('$');
            if (idxInner > 0) {
                this.tag = this.tag.substring(idxInner + 1);
            }
        } else {
            this.tag = logName;
        }
        // TODO: ensure tag is 23 characters (or less)
    }

    @Override
    public void debug(String format, Object... args) {
        if (isDebugEnabled()) {
            Log.d(tag, String.format(format, args));
        }
    }

    @Override
    public void debug(Throwable t, String format, Object... args) {
        if (isDebugEnabled()) {
            Log.d(tag, String.format(format, args), t);
        }
    }

    @Override
    public void error(String format, Object... args) {
        if (isErrorEnabled()) {
            Log.e(tag, String.format(format, args));
        }
    }

    @Override
    public void error(Throwable t, String format, Object... args) {
        if (isErrorEnabled()) {
            Log.e(tag, String.format(format, args), t);
        }
    }

    @Override
    public void info(String format, Object... args) {
        if (isInfoEnabled()) {
            Log.i(tag, String.format(format, args));
        }
    }

    @Override
    public void info(Throwable t, String format, Object... args) {
        if (isInfoEnabled()) {
            Log.i(tag, String.format(format, args), t);
        }
    }

    @Override
    public void verbose(String format, Object... args) {
        if (isVerboseEnabled()) {
            Log.v(tag, String.format(format, args));
        }
    }

    @Override
    public void verbose(Throwable t, String format, Object... args) {
        if (isVerboseEnabled()) {
            Log.v(tag, String.format(format, args), t);
        }
    }

    @Override
    public void warn(String format, Object... args) {
        if (isWarnEnabled()) {
            Log.w(tag, String.format(format, args));
        }
    }

    @Override
    public void warn(Throwable t, String format, Object... args) {
        if (isWarnEnabled()) {
            Log.w(tag, String.format(format, args), t);
        }
    }

    @Override
    public void wtf(String format, Object... args) {
        if (isWtfEnabled()) {
            Log.wtf(tag, String.format(format, args));
        }
    }

    @Override
    public void wtf(Throwable t, String format, Object... args) {
        if (isWtfEnabled()) {
            Log.wtf(tag, String.format(format, args), t);
        }
    }
}
