package com.codefresh.tests.apiTests;

import com.codefresh.api.actions.BuildAction;
import com.codefresh.api.actions.PipelineAction;
import com.codefresh.api.models.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiPipelineTests {

    @Test
    public void apiPipelineTest() {

        final String codeFreshBaseUrl = "https://g.codefresh.io/api";
        PipelineAction pipelineAction =
                new PipelineAction(codeFreshBaseUrl);
        PipelineRequest pipelineRequest = createPipeLine();
        PipelineResponse pipelineResponse = pipelineAction.createPipeline(pipelineRequest);

        RunPipelineRequest runPipelineRequest = createRunRequest(pipelineResponse);
        String runId = pipelineAction.runPipeline(runPipelineRequest, pipelineRequest.getMetadata().getName());

        BuildAction buildAction = new BuildAction(codeFreshBaseUrl);
        boolean isSuccess = buildAction.verifyBuildStatusSuccess(runId , 5,5);

        Assert.assertTrue(isSuccess,"The run is failed or incomplete.");
    }

    public RunPipelineRequest createRunRequest(PipelineResponse pipelineResponse) {

        RunPipelineRequest runPipelineRequest = new RunPipelineRequest();
        runPipelineRequest.setIsYamlService(true);
        runPipelineRequest.setServiceId(pipelineResponse.getMetadata().getAccountId());
        runPipelineRequest.setServiceName(pipelineResponse.getMetadata().getName());
        runPipelineRequest.setType("build");

        return runPipelineRequest;
    }

    public PipelineRequest createPipeLine() {

        final String pipelineName = "Test";
        final String pipelineProject = "default";
        final String accountId = "5f2c0e1cbce9533a2a6b3425";

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MMddhhmmss");
        String dateAsString = simpleDateFormat.format(new Date());

        PipelineRequest pipelineRequest = new PipelineRequest();
        Metadata metadata = new Metadata();
        Spec spec = new Spec();
        pipelineRequest.setMetadata(metadata);
        pipelineRequest.setSpec(spec);
        pipelineRequest.getMetadata().setAccountId(accountId);
        pipelineRequest.getMetadata().setOriginalYamlString("# More examples of Codefresh YAML can be found at\n# https://codefresh.io/docs/docs/yaml-examples/examples/\n\nversion: \"1.0\"\n# Stages can help you organize your steps in stages\nstages:\n  - \"clone\"\n  - \"test\"\n\nsteps:\n  clone:\n    title: \"Cloning repository\"\n    type: \"git-clone\"\n    repo: \"https://github.com/codefresh-io/cli/\"\n    # Clone the master branch. Or, use ${{CF_BRANCH}} to get branch name from trigger\n    # Learn more at https://codefresh.io/docs/docs/codefresh-yaml/variables/\n    revision: \"master\"\n    stage: \"clone\"\n\n  test:\n    title: \"Running test\"\n    type: \"freestyle\" # Run any command\n    image: \"ubuntu:latest\" # The image in which command will be executed\n    working_directory: \"${{clone}}\" # Running command where code cloned\n    commands:\n      - \"echo hello world\"\n    stage: \"test\"\n\n");
        pipelineRequest.getMetadata().setName(pipelineProject + " " + pipelineName + dateAsString);
        pipelineRequest.getSpec().setStages(new String[]{"clone", "test"});

        JsonObject clone = new JsonObject();
        clone.addProperty("repo", "https://github.com/codefresh-io/cli/");
        clone.addProperty("revision", "master");
        clone.addProperty("stage", "clone");
        clone.addProperty("title", "Cloning repository");
        clone.addProperty("type", "git-clone");

        JsonObject test = new JsonObject();
        JsonArray commands = new JsonArray();
        commands.add("echo hello world");
        test.add("commands", commands);
        test.addProperty("image", "ubuntu:latest");
        test.addProperty("stage", "test");
        test.addProperty("title", "Running test");
        test.addProperty("type", "freestyle");
        test.addProperty("working_directory", "${{clone}}");

        JsonObject steps = new JsonObject();
        steps.add("clone", clone);
        steps.add("test", test);
        pipelineRequest.getSpec().setSteps(steps);

        return pipelineRequest;
    }
}