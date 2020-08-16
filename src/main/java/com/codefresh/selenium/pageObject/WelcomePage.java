package com.codefresh.selenium.pageObject;

import com.codefresh.selenium.actions.SeleniumActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage{

    @FindBy(xpath ="//div[contains(text(),'\n" +
            "                        Pipelines\n" +
            "                        ')]")


    public WebElement pipelinesButton;

    @FindBy(xpath ="//button[contains(@class,'add-button')]")
    public WebElement addPipeline;


    public void addPipeline(SeleniumActions actions){
        actions.clickOnElement(pipelinesButton,"pipelinesButton");
        actions.clickOnElement(addPipeline,"addPipeline");
    }
}
