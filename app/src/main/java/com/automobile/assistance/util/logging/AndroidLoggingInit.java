package com.automobile.assistance.util.logging;

import java.util.HashMap;
import java.util.Map;

public class AndroidLoggingInit implements IAndroidLoggingInit {
    @Override
    public Map<String, Integer> getLogLevels() {
//        Map<String, Integer> levels = new HashMap<String, Integer>();
//        levels.put("com.iversecomics.bundle", Logger.WARN);
//        levels.put(FetchRequest.class.getName(), Logger.WARN);
//        levels.put(LateBitmapView.class.getName(), Logger.WARN);
//        levels.put(BatchInserts.class.getName(), Logger.WARN);
//        return levels;

        return new HashMap<>();
    }

    @Override
    public int getRootLevel() {
        return Logger.DEBUG;
    }
}
