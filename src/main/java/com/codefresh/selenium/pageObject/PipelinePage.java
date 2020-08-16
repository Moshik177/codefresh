package com.codefresh.selenium.pageObject;

import com.codefresh.selenium.actions.SeleniumActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PipelinePage {

    @FindBy(name ="pipelineName")
    public WebElement pipeLineName;

    @FindBy(xpath ="//button[contains(@id,'create-pipeline-modal__footer__cancel-btn')]/following-sibling::button[1]")
    public WebElement create;

    @FindBy(xpath="//cf-on-off[contains(@class,'create-pipeline-modal__cf-on-off--component')]")
    public WebElement gitRepositorySwitch;

    public void createNewPipeLine(SeleniumActions actions,String pipelineName){
        setPipeLineName(actions,pipelineName);
        clickOnGitRepositorySwitch(actions);
        clickOnCreateButton(actions);
    }

    private void clickOnCreateButton(SeleniumActions actions){
        actions.clickOnElement(create,"create");
    }

    private void clickOnGitRepositorySwitch(SeleniumActions actions){
        actions.clickOnElement(gitRepositorySwitch,"gitRepositorySwitch");
    }

    private void setPipeLineName(SeleniumActions actions,String pipelineName){
        actions.enterTextToElement(pipeLineName,"pipeLineName",pipelineName);
    }
}
