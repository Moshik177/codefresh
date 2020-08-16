package com.codefresh.selenium.actions;

import com.codefresh.log.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.function.Function;

public class SeleniumActions {

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final Actions action;
    private final int timeToWait = 30;

    public SeleniumActions(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, timeToWait);
        this.action = new Actions(driver);
    }

    public void openWebSite(String url) {

        driver.get(url);
        driver.manage().window().maximize();
        waitForPageLoad();
        Log.info(SeleniumActions.class, "Open the website " + url);
    }

    public void waitForPageLoad() {

        Function<WebDriver, Boolean> function = driver -> {
            Log.info(SeleniumActions.class, "Current Window State: "
                    + ((JavascriptExecutor) driver).executeScript("return document.readyState"));
            return String
                    .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                    .equals("complete");
        };
    }


    public void clickOnElement(WebElement element, String description) {

        try {
            waitForVisibility(element, description);
            element.click();
            Log.info(SeleniumActions.class, "Clicking on the element: " + description);
        } catch (Exception e) {
            Log.error(SeleniumActions.class, "Exception: the element " + description + " not found " + e.getMessage());
            Assert.fail();
        }
    }

    public void enterTextToElement(WebElement element, String description, String text) {

        try {
            waitForVisibility(element, description);
            element.sendKeys(text);
            Log.info(SeleniumActions.class, "Enter text to the element: " + description);
        } catch (Exception e) {
            Log.error(SeleniumActions.class, "Exception element not found " + e.getMessage());
            Assert.fail();
        }
    }

    public void enterTextToUnEditElement(WebElement element, String description, String text) {

        try {
            waitForVisibility(element, description);
            ((JavascriptExecutor)driver).executeScript("arguments[0].innerText = '"+text+"'", element);
            Log.info(SeleniumActions.class, "Enter text to the element: " + description);
        } catch (Exception e) {
            Log.error(SeleniumActions.class, "Exception element not found " + e.getMessage());
            Assert.fail();
        }
    }

    public String getElementValue(WebElement element, String description) {

        try {
            waitForVisibility(element, description);
            String text = element.getText();
            Log.info(SeleniumActions.class, "Get text from element: " + description + ": " + text);
            return text;
        } catch (Exception e) {
            Log.error(SeleniumActions.class, "Exception element not found " + e.getMessage());
            Assert.fail();
            return null;
        }
    }

    public void waitForCondition(String description, ExpectedCondition<Boolean> expectedConditions) {
        try {
            webDriverWait.until(expectedConditions);
        } catch (TimeoutException e) {
            Log.error(SeleniumActions.class, "Time out, the element " + description + " is not display.");
            Assert.fail();
        }
    }

    public boolean waitForVisibility(WebElement element, String description) {
        boolean isElementVisible = false;
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            isElementVisible = true;
        } catch (TimeoutException e) {
            Log.error(SeleniumActions.class, "Time out, the element " + description + " is not display.");
            Assert.fail();
        } catch (NoSuchElementException e) {
            Log.error(SeleniumActions.class, "The element " + description + " is not visible." + e.getMessage());
            Assert.fail();
        }
        return isElementVisible;
    }

    public Boolean waitForVisibilityWithTime(WebElement element, String description, long time) {

        webDriverWait.withTimeout((Duration.ofSeconds(time)));
        boolean isElementSeen = waitForVisibility(element, description);
        webDriverWait.withTimeout((Duration.ofSeconds(timeToWait)));
        return isElementSeen;
    }

    public void moveToElement(WebElement element) {
        action.moveToElement(element);
    }
}
