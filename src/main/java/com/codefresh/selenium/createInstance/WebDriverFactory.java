package com.codefresh.selenium.createInstance;

import com.codefresh.selenium.generators.WebDriverGenerator;
import com.codefresh.selenium.generators.ChromeWebDriverGenerator;
import com.codefresh.selenium.generators.FirefoxWebDriverGenerator;
import com.codefresh.selenium.generators.InternetExplorerWebDriverGenerator;
import com.codefresh.utils.OsUtils;
import com.codefresh.utils.TestFolderUtils;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class WebDriverFactory {

    public static WebDriver createDriver(String browserType,String driverFolderName) throws IOException, InvalidArgumentException {
        WebDriverGenerator generator = createGenerator(browserType);
        OsUtils.OS os = OsUtils.getOS();
        return generator.getWebDriver(os,browserType,driverFolderName);
    }

    private static WebDriverGenerator createGenerator(String browserType){

        if (browserType.equals(BrowserType.CHROME_DRIVER.getBrowserType())) {
            return new ChromeWebDriverGenerator();
        } else if ((browserType.equals(BrowserType.FIREFOX_DRIVER.getBrowserType()))) {
            return new FirefoxWebDriverGenerator();
        } else if ((browserType.equals(BrowserType.INTERNET_EXPLORER_DRIVER.getBrowserType()))) {
            return new InternetExplorerWebDriverGenerator();
        } else {
            throw new InvalidArgumentException("WebDriver type " + browserType + " is either unrecognized or not supported");
        }
    }
}