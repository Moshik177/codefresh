package com.codefresh.cli.actions;

import com.codefresh.cli.handler.CommandLine;
import com.codefresh.log.Log;
import com.codefresh.utils.OsUtils;
import org.testng.Assert;

public class AuthenticationAction extends BaseAction {

    private final String testFolder;
    private final OsUtils.OS os;
    private final String apiKey = "5f3040fa50270d57f9686b12.5342307beb65d833ed0dd0213d14144d";

    public AuthenticationAction(String testFolder, OsUtils.OS os) {
        super(new CommandLine());
        this.testFolder = testFolder;
        this.os = os;
    }

    public void authentication() {
        String command = "codefresh auth create-context --api-key " + apiKey;
        String output = commandLine.command(os,command,testFolder);
        Assert.assertTrue(output.contains("Updated context"),
                "authentication failed");
        Log.info(AuthenticationAction.class,"authentication succeeded.");
    }
}
