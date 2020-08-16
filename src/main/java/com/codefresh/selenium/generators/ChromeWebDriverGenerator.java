package com.codefresh.selenium.generators;

import com.codefresh.utils.OsUtils;
import com.codefresh.utils.ResourceUtils;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;

public class ChromeWebDriverGenerator implements WebDriverGenerator {

    @Override
    public WebDriver getWebDriver(OsUtils.OS os, String browserType, String driverFolderName) throws IOException,InvalidArgumentException {

        ChromeOptions options = new ChromeOptions();
        String driverPath = ResourceUtils.getDriverPath(os,browserType);
        String chromeDriverPath = ResourceUtils.copyFileFromResource(driverPath,driverFolderName);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        options.addArguments("disable-infobars");
        return new ChromeDriver(options);
    }
}
