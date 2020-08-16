package com.codefresh.log;

import ch.qos.logback.classic.Level;
import java.util.Set;

public class LogDispatcher implements LogWriter {

    @Override
    public void writeLine(Class c, Level level, String message) {
        currentThreadListeners().forEach(thread ->thread.writeLine(c, level, message));
    }

    @Override
    public void startBlock() {
        currentThreadListeners().forEach(LogWriter::startBlock);
    }

    @Override
    public void endBlock() {
        currentThreadListeners().forEach(LogWriter::endBlock);
    }

    @Override
    public void finalizeLog() {
        currentThreadListeners().forEach(LogWriter::finalizeLog);
    }

    private Set<LogWriter> currentThreadListeners() {

        Thread currentThread = Thread.currentThread();
        String currentThreadId = String.valueOf(currentThread.getId());
        LoggingProperties loggingProperties= LoggingThreads.getInstance().get(currentThreadId);
        return loggingProperties.getLoggers();
    }
}