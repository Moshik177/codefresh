package com.codefresh.selenium.generators;

import com.codefresh.utils.OsUtils;
import com.codefresh.utils.ResourceUtils;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import java.io.IOException;

public class InternetExplorerWebDriverGenerator implements WebDriverGenerator {
    @Override
    public WebDriver getWebDriver(OsUtils.OS os, String browserType, String driverFolderName) throws InvalidArgumentException, IOException {

        String driverPath = ResourceUtils.getDriverPath(os,browserType);
        String internetExplorerPath = ResourceUtils.copyFileFromResource(driverPath,driverFolderName);
        System.setProperty("webdriver.ie.driver", internetExplorerPath);
        InternetExplorerOptions ops = new InternetExplorerOptions();
        ops.introduceFlakinessByIgnoringSecurityDomains();

        return new InternetExplorerDriver(ops);
    }
}

