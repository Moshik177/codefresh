package com.codefresh.selenium.generators;

import com.codefresh.utils.OsUtils;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

public interface WebDriverGenerator {

    WebDriver getWebDriver(OsUtils.OS os, String browserType, String driverFolderName) throws InvalidArgumentException, IOException;
}
