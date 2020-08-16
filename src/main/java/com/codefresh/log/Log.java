package com.codefresh.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import ch.qos.logback.classic.Level;

public class Log {

    private static final LogDispatcher logDispatcher = new LogDispatcher();

    public static void info(Class c, String msg) {
        logDispatcher.writeLine(c, Level.INFO, msg);
    }

    public static void warn(Class c, String msg) {
        logDispatcher.writeLine(c, Level.WARN, msg);
    }

    public static void trace(Class c, String msg) {
        logDispatcher.writeLine(c, Level.TRACE, msg);
    }

    public static void error(Class c, String msg) {
        logDispatcher.writeLine(c, Level.ERROR, msg);
    }

    public void debug(Class c, String msg) {
        logDispatcher.writeLine(c, Level.DEBUG, msg);
    }

    public static void print(Class c, String msg) {
        String format = "%-16s%-20s %s%n";
        System.out.printf(format, new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date()), c.getSimpleName(), msg);
        info(c,msg);
    }
}