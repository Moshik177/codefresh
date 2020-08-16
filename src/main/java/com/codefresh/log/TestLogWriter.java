package com.codefresh.log;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;

public class TestLogWriter implements LogWriter {

    private final String loggerName;
    private final String outputFolder;
    private final String testName;

    public TestLogWriter(String loggerName, String outputFolder, String testName) {
        this.loggerName = loggerName;
        this.outputFolder = outputFolder;
        this.testName = testName;
        initLogAppender();
    }

    @Override
    public void writeLine(Class c, Level level, String message) {
        log(c, level, message, loggerName);
    }

    @Override
    public void startBlock() { }

    @Override
    public void endBlock() { }

    @Override
    public void finalizeLog() { }

    private void log(Class c, Level level, String message, String name) {

        if (Level.INFO.equals(level)) {
            getLogger(name).info(getMsg(c, message));
        } else if (Level.TRACE.equals(level)) {
            getLogger(name).trace(getMsg(c, message));
        } else if (Level.ERROR.equals(level)) {
            getLogger(name).error(getMsg(c, message));
        } else if (Level.DEBUG.equals(level)) {
            getLogger(name).debug(getMsg(c, message));
        }
    }

    private Logger getLogger(String str) {
       return (Logger) LoggerFactory.getLogger(str);
    }

    private String getMsg(Class c, String message)
    {
        String className = c.getSimpleName().replace(" $ ", " ");
        return className + " " + message;
    }

    private void initLogAppender() {

        LoggerContext context=(LoggerContext)LoggerFactory.getILoggerFactory();
        Logger logger = (Logger) LoggerFactory.getLogger(loggerName);
        logger.detachAndStopAllAppenders();

        PatternLayoutEncoder layoutEncoder=new PatternLayoutEncoder();
        layoutEncoder.setPattern("%d{MM-dd HH:mm:ss} [%t] %-5p %msg%throwable%n");
        layoutEncoder.setContext(context);
        layoutEncoder.setCharset(Charset.defaultCharset());
        layoutEncoder.start();

        FileAppender fileAppender =new FileAppender();
        fileAppender.setFile(Paths.get(outputFolder,testName,"testLog.log").toString());
        fileAppender.setEncoder(layoutEncoder);
        fileAppender.setContext(context);
        fileAppender.start();

        logger.addAppender(fileAppender);
        logger.setLevel(Level.INFO);
        }
}