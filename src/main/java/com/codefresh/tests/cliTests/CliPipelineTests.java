package com.codefresh.tests.cliTests;

import com.codefresh.cli.actions.AuthenticationAction;
import com.codefresh.cli.actions.BuildAction;
import com.codefresh.cli.actions.PipelineAction;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CliPipelineTests extends CliTestBase {

    @Test
    public void cliPipelineTest() {

        AuthenticationAction authenticationAction = new
                AuthenticationAction(testFolderName,os);
        authenticationAction.authentication();

        PipelineAction pipelineAction =
                new PipelineAction(testFolderName,os);

        final String pipelineName = "Test";
        final String pipelineProject = "default";

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MMddhhmmss");
        String dateAsString = simpleDateFormat.format(new Date());
        String pipeFullName = pipelineProject + " " + pipelineName + dateAsString;

        pipelineAction.createPipeline(pipeFullName);
        pipelineAction.runPipeline(pipeFullName);

        BuildAction buildAction = new BuildAction(testFolderName,os);
        buildAction.verifyBuildStatusSuccess(pipeFullName);
    }
}
