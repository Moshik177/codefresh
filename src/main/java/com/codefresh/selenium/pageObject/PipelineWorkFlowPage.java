package com.codefresh.selenium.pageObject;

import com.codefresh.selenium.actions.SeleniumActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.IntStream;

public class PipelineWorkFlowPage {

    @FindBy(xpath = "//button[contains(@class,'buttons__save')]")
    public WebElement saveButton;

    @FindBy(xpath = "//button[contains(@class,'button-run')]")
    public WebElement runButton;

    @FindBy(xpath = "//span[@class='cm-m-yaml cm-string' and text()[contains(.,'ls')]]")
    public WebElement lsCommandYaml;

    @FindBy(xpath = "//span[contains(@class,'cm-atom')]")
    public List<WebElement> commands;

    @FindBy(xpath ="//button[contains(@id,'trigger-modal-run-button')]")
    public WebElement runButtonPopup;

    @FindBy(xpath ="//div[contains(@class,'modal-content')]")
    public WebElement triggerPopup;

    @FindBy(xpath ="//div[contains(@class,'pipeline-workflow__column')]")
    public WebElement pipelineWorkflow;

    public void runPipeLine(SeleniumActions actions, String command) {

        waitForLoaderToFinish(actions);
        setLinesAsComment(actions);
        editLsCommand(actions, command);
        clickOnSaveButton(actions);
        clickOnRunButton(actions);
        clickOnRunOnPopUpWindow(actions);
    }

    private void setLinesAsComment(SeleniumActions actions) {

        int buildIndex = IntStream.range(0, commands.size())
                .filter(x -> commands.get(x).getText().contains("build"))
                .findFirst()
                .orElse(-1);

        int testIndex = IntStream.range(0, commands.size())
                .filter(x -> commands.get(x).getText().contains("test"))
                .findFirst()
                .orElse(-1);

        IntStream.range(buildIndex, testIndex)
                .boxed()
                .map(x -> commands.get(x))
                .forEach(e -> {
                    actions.moveToElement(e);
                    String originText = actions.getElementValue(e,"");
                    actions.enterTextToUnEditElement(e, "editCommand", "#" + originText);
                });
    }

    private void waitForLoaderToFinish(SeleniumActions actions) {
        actions.waitForPageLoad();
        actions.waitForVisibility(pipelineWorkflow,"pipelineWorkflow");
    }

    private void editLsCommand(SeleniumActions actions, String command) {
        actions.moveToElement(lsCommandYaml);
        actions.enterTextToUnEditElement(lsCommandYaml, "editCommand", command);
    }

    private void clickOnSaveButton(SeleniumActions actions) {
        actions.clickOnElement(saveButton, "saveButton");
        actions.waitForCondition("saveButton", ExpectedConditions.attributeContains(saveButton,"disabled","disabled"));
    }

    private void clickOnRunOnPopUpWindow(SeleniumActions actions) {
        actions.waitForVisibility(triggerPopup,"runButtonPopUp");
        actions.clickOnElement(runButtonPopup, "runButtonPopup");
    }

    private void clickOnRunButton(SeleniumActions actions) {
        actions.clickOnElement(runButton, "runButton");
    }
}