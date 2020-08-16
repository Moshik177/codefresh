package com.codefresh.selenium.pageObject;

import com.codefresh.selenium.actions.SeleniumActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BuildPage {

    @FindBy(className ="status completed")
    public WebElement statusCompleted;

    public boolean isStatusCompleted(SeleniumActions actions){
        return actions.waitForVisibilityWithTime(statusCompleted,"statusComplated",30);
    }
}
