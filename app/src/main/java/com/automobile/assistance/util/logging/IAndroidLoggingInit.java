package com.automobile.assistance.util.logging;

import java.util.Map;

public interface IAndroidLoggingInit {
    Map<String, Integer> getLogLevels();

    int getRootLevel();
}
