package com.codefresh.log;

import ch.qos.logback.classic.Level;

interface LogWriter {
    void writeLine(Class c,Level level,String message);
    void startBlock();
    void endBlock();
    void finalizeLog();
}

