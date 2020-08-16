package com.codefresh.selenium.pageObject;

import com.codefresh.selenium.actions.SeleniumActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    @FindBy(xpath = "//a[contains(@class,'nav-item-login')]")
    public WebElement signIn;

    public void clickOnSignIn(SeleniumActions actions) {
        actions.clickOnElement(signIn, "signIn");
    }
}