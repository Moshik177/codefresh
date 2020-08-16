package com.codefresh.log;

import java.util.Set;

public class LoggingProperties {

    private final String name;
    private final Set<LogWriter> loggers;

    public LoggingProperties(String name ,Set<LogWriter> loggers ){
        this.name = name;
        this.loggers = loggers;
    }

    public Set<LogWriter> getLoggers() {
        return loggers;
    }
}
