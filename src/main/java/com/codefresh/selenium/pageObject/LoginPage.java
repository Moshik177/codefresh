package com.codefresh.selenium.pageObject;

import com.codefresh.selenium.actions.SeleniumActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(xpath = "//button[contains(@class,'bitbucket')]")
    public WebElement bitbucketButton;

    @FindBy(id ="username")
    public WebElement emailInput;

    @FindBy(id ="password")
    public WebElement passwordInput;

    @FindBy(id ="login-submit")
    public WebElement continueButton;

    public void enterToCodefreshAccountByBitbucket(SeleniumActions actions,String email,String password) {
        actions.waitForPageLoad();
        actions.clickOnElement(bitbucketButton, "bitbucketButton");
        actions.enterTextToElement(emailInput, "emailInput", email + Keys.ENTER);
        actions.enterTextToElement(passwordInput, "password", password);
        actions.clickOnElement(continueButton, "continueButton");
    }
}
