package com.codefresh.listener;

import com.codefresh.log.Log;
import com.codefresh.log.LoggingProperties;
import com.codefresh.log.LoggingThreads;
import com.codefresh.log.TestLogWriter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Collections;

public class ResultOutputListener implements ITestListener {

    @Override
    public void onFinish(ITestContext Result) {

    }

    @Override
    public void onStart(ITestContext Result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult Result) {
        Log.print(ResultOutputListener.class,"The test:" + Result.getName() + " failed"+
                " and the error is: " +Result.getThrowable());
        Result.setStatus(ITestResult.FAILURE);
    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult Result) {
        Log.print(ResultOutputListener.class,"The test:" + Result.getName() + " skipped");
                Result.setStatus(ITestResult.SKIP);
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result) {

        try {
            String testName = Result.getTestContext().getCurrentXmlTest().getName();
            String outputFolder = Result.getTestContext().getCurrentXmlTest().getParameter("testFolder");
            TestLogWriter testLogWriter = new TestLogWriter(String.valueOf(Thread.currentThread().getId()),
                    outputFolder, testName);

            LoggingProperties loggingProperties = new LoggingProperties(String.valueOf(Thread.currentThread().getId()), Collections.singleton(testLogWriter));
            LoggingThreads.addLog(String.valueOf(Thread.currentThread().getId()), loggingProperties);
            Log.info(ResultOutputListener.class,Result.getName() + " test case started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult Result) {
        Log.print(ResultOutputListener.class,"The test:" + Result.getName() + " passed.");
        Result.setStatus(ITestResult.SUCCESS);
    }

}
