package com.codefresh.tests;

import org.testng.ITestContext;
import java.io.File;

public abstract class TestBase {

    protected String getTestFolderName(ITestContext testContext) {
        String testName = testContext.getCurrentXmlTest().getName();
        String testsFolder = testContext.getCurrentXmlTest().getParameter("testFolder");
        return testsFolder + File.separator + testName;
    }
}
