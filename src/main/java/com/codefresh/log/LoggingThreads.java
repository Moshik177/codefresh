package com.codefresh.log;

import java.util.HashMap;

public class LoggingThreads {

    private static final HashMap<String, LoggingProperties> threadIdToLoggingProperties = new HashMap<>();

    public static HashMap<String, LoggingProperties> getInstance() {
        return threadIdToLoggingProperties;
    }

    public static void addLog(String logName, LoggingProperties loggingProperties) {
        threadIdToLoggingProperties.put(logName,loggingProperties);
    }
}
