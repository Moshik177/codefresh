package com.codefresh.selenium.generators;

import com.codefresh.utils.OsUtils;
import com.codefresh.utils.ResourceUtils;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.IOException;

public class FirefoxWebDriverGenerator implements WebDriverGenerator{
    @Override
    public WebDriver getWebDriver(OsUtils.OS os, String browserType, String driverFolderName) throws InvalidArgumentException, IOException {

        String driverPath = ResourceUtils.getDriverPath(os,browserType);
        String firefoxDriverPath = ResourceUtils.copyFileFromResource(driverPath,driverFolderName);
        System.setProperty("webdriver.gecko.driver",firefoxDriverPath);
        return new FirefoxDriver();
    }
}
