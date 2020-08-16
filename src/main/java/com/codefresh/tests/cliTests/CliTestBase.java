package com.codefresh.tests.cliTests;

import com.codefresh.tests.TestBase;
import com.codefresh.utils.OsUtils;
import com.codefresh.utils.ResourceUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

public abstract class CliTestBase extends TestBase {

    protected String testFolderName;
    protected OsUtils.OS os;

    @BeforeMethod
    public void setup(ITestContext testContext, ITestResult Result) {

        try {
            testFolderName = getTestFolderName(testContext);
            os = OsUtils.getOS();
            String CliPath = ResourceUtils.getCliPath(os);
            ResourceUtils.copyFileFromResource(CliPath, testFolderName);
            ResourceUtils.copyFileFromResource("/spec.yaml", testFolderName);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("The cli can't created,error: " + e.getStackTrace());
        }
    }
}
