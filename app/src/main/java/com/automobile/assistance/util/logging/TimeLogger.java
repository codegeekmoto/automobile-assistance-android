package com.automobile.assistance.util.logging;

/**
 */
public class TimeLogger {
    private static final Logger LOG = LoggerFactory.getLogger(TimeLogger.class);


    public static TimePoint newTimePoint(String name) {
        return new TimePoint(name);
    }

    public static class TimePoint {
        private final long startTime;
        private final String operation;

        TimePoint(String operation) {
            this.startTime = System.currentTimeMillis();
            this.operation = operation;
        }

        public void logStep(String step) {
            LOG.info("Operation <%s>, step <%s> time is <%d> millis.", operation, step, System.currentTimeMillis() - startTime);
        }

        public void logEnd() {
            LOG.info("Operation <%s> took <%d> millis.", operation, System.currentTimeMillis() - startTime);
        }
    }
}
