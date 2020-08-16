package com.codefresh.orchestration;

import picocli.CommandLine;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class ArgumentsParser implements Callable<HashMap<String,String>> {

    @CommandLine.Option(names = "-t", description = "The test option")
    String testName;

    @Override
    public HashMap<String,String> call() throws InvalidParameterException {

        HashMap<String, String> hashMap = new HashMap<>();

        if(testName != null) {
            hashMap.put("test", testName);
        }
        return hashMap;
    }
}