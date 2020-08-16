package com.codefresh.tests.uiTests;

import com.codefresh.selenium.actions.SeleniumActions;
import com.codefresh.selenium.createInstance.BrowserType;
import com.codefresh.selenium.createInstance.WebDriverFactory;
import com.codefresh.tests.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class UiTestBase extends TestBase {

    protected WebDriver webDriver;
    protected SeleniumActions actions;

    @BeforeMethod
    public void setup(ITestContext testContext, ITestResult Result){

        try {
            String testFolderName = getTestFolderName(testContext);
            webDriver = WebDriverFactory.createDriver(BrowserType.CHROME_DRIVER.getBrowserType(),testFolderName);
            actions = new SeleniumActions(webDriver);
        } catch(Exception e){
            Assert.fail("The driver can't created,error: " + e.getMessage());
        }
    }

    @AfterMethod
    public void teardown() {
        webDriver.quit();
    }
}