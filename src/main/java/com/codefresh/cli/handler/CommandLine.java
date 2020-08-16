package com.codefresh.cli.handler;

import com.codefresh.log.Log;
import com.codefresh.utils.OsUtils;
import org.testng.Assert;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class CommandLine {

    public String command(OsUtils.OS os,String command,String folderDir){
        Process process = null;
        String result = null;
        try {
            command = addSuffixToCommandForWindows(os,command);
            File dir = new File(folderDir);

            Log.info(CommandLine.class,"The command before execute: " + command);
            process = Runtime.getRuntime().exec(command,null,dir);
            process.waitFor(30, TimeUnit.SECONDS);

            result =  getResultOfCommand(process);
            Log.info(CommandLine.class,"The output of the command is: " + result);
        } catch(Exception e) {
            Assert.fail("The Command didn't succeeded, error " +e.getMessage());
        } finally {
            if(process != null)
                 process.destroy();
        }
        return result;
    }

    private String getResultOfCommand(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder lines = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.append(line);
        }
        return lines.toString();
    }

    private String addSuffixToCommandForWindows(OsUtils.OS os,String command){

        if(os.equals(OsUtils.OS.WINDOWS))
            return "cmd /c " + command;
         else return command;
    }
}