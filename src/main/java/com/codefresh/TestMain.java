package com.codefresh;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.codefresh.listener.ResultOutputListener;
import com.codefresh.orchestration.ArgumentsParser;
import com.codefresh.orchestration.TestsSelector;
import com.codefresh.utils.TestFolderUtils;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.lang.reflect.Method;
import java.util.*;

public class TestMain {
    public static void main(String... args) {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.detachAndStopAllAppenders();

        CommandLine commandLine = new CommandLine(new ArgumentsParser());
        commandLine.execute(args);
        HashMap hashMap = commandLine.getExecutionResult();

        ArrayList<Method> methodsNames = new ArrayList<>();
        if (hashMap.containsKey("test")) {
            List<String> testNames= Arrays.asList(((String) hashMap.get("test")).split(","));
            methodsNames.addAll(TestsSelector.getTestsToRunFromTestsNames(testNames));
        } else throw new IllegalArgumentException("Test name not supply.");
        Map<String, List<String>> testClassToMethods = TestsSelector.getTestClassToMethods(methodsNames);

        TestFolderUtils.createFolderForLogs();
        String testFolder = TestFolderUtils.createTestsFolder();
        new TestRunner(testFolder, Collections.singletonList(ResultOutputListener.class)).runTests(testClassToMethods);
    }
}