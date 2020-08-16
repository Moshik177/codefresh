package com.codefresh.cli.actions;

import com.codefresh.api.models.PipelineRequest;
import com.codefresh.cli.handler.CommandLine;
import com.codefresh.cli.utils.YamlUtil;
import com.codefresh.log.Log;
import com.codefresh.utils.OsUtils;
import org.testng.Assert;

public class PipelineAction extends BaseAction{

    private final String testFolder;
    private final OsUtils.OS os;

    public PipelineAction(String testFolder,OsUtils.OS os){
        super(new CommandLine());
        this.testFolder = testFolder;
        this.os = os;
    }

    public void createPipeline(String pipelineName){

        try {
            String pipelineYaml = testFolder + "\\spec.yaml";
            PipelineRequest pipelineRequest = YamlUtil.getYaml(pipelineYaml, PipelineRequest.class);
            pipelineRequest.getMetadata().setName(pipelineName);
            YamlUtil.createYaml(pipelineYaml, pipelineRequest);

            String command = "codefresh create -f " + pipelineYaml;
            String output = commandLine.command(os, command, testFolder);
            Assert.assertTrue(output.contains("Pipeline " + pipelineName + " created"),
                    "Pipeline not created.");
            Log.info(PipelineAction.class, "Pipeline created.");
        } catch (Exception e){
            Assert.fail("can't create pipeline,error: " + e.getMessage());
        }
    }

    public void runPipeline(String pipelineName){
        String command = "codefresh run "+ "\"" + pipelineName +"\"";

        String output = commandLine.command(os,command,testFolder);
        Assert.assertTrue(output.contains("Successfully ran git-clone step: Cloning repository"),
                "Pipeline not run- clone step.");
        Assert.assertTrue(output.contains("Successfully ran freestyle step: Running test"),
                "Pipeline not run-test step.");
        Log.info(PipelineAction.class,"Pipeline run.");
    }
}