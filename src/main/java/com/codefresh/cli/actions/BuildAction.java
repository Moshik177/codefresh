package com.codefresh.cli.actions;

import com.codefresh.cli.handler.CommandLine;
import com.codefresh.log.Log;
import com.codefresh.utils.OsUtils;
import org.testng.Assert;

public class BuildAction extends  BaseAction{

    private final String testFolder;
    private final OsUtils.OS os;

    public BuildAction(String testFolder,OsUtils.OS os){
        super(new CommandLine());
        this.testFolder = testFolder;
        this.os = os;
    }

    public void verifyBuildStatusSuccess(String pipelineName) {
        String command = "codefresh get builds --pipeline-name " +"\""
                +pipelineName+"\"" +" -s="+"\""+"success" +"\"";
        String output = commandLine.command(os,command,testFolder);
        Assert.assertFalse(output.contains("no available resources"), "Pipeline not build successfully");
        Log.info(PipelineAction.class,"Pipeline build successfully.");
    }
}
