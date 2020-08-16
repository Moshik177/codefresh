package com.codefresh.tests.uiTests;

import com.codefresh.selenium.pageObject.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UiPipelineTests extends UiTestBase {

    private final String codefreshSite = "https://codefresh.io";

    @Test
    public void seleniumPipelineTest() {
            actions.openWebSite(codefreshSite);

            DashboardPage dashboardPage = PageFactory.initElements(webDriver, DashboardPage.class);
            dashboardPage.clickOnSignIn(actions);

            LoginPage loginPage =  PageFactory.initElements(webDriver, LoginPage.class);
            loginPage.enterToCodefreshAccountByBitbucket(actions,"moshikkalash@gmail.com","mk6171846");

            WelcomePage welcomePage =PageFactory.initElements(webDriver, WelcomePage.class);
            welcomePage.addPipeline(actions);

            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("MMddhhmmss");
            String dateAsString = simpleDateFormat.format(new Date());

            PipelinePage pipelinePage = PageFactory.initElements(webDriver, PipelinePage.class);
            pipelinePage.createNewPipeLine(actions,"Test" + dateAsString);

            PipelineWorkFlowPage pipelineWorkFlowPage = PageFactory.initElements(webDriver, PipelineWorkFlowPage.class);
            pipelineWorkFlowPage.runPipeLine(actions,"echo hello world");

            BuildPage buildPage = PageFactory.initElements(webDriver, BuildPage.class);
            Assert.assertTrue(buildPage.isStatusCompleted(actions), "the build faild or not complated");
    }
}