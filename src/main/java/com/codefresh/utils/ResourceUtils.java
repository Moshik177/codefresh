package com.codefresh.utils;

import java.io.IOException;

public class ResourceUtils {

    public static String copyFileFromResource(String driverPath,String testFolder) throws IOException {

        String[] resourcePathSplit = driverPath.split("/");
        String fileName = resourcePathSplit[resourcePathSplit.length - 1];
        TestFolderUtils.copyFileToNewLocation(driverPath,fileName,testFolder);
        return String.format("%s/%s",testFolder,fileName);
    }

    public static String getDriverPath(OsUtils.OS os,String browserType) {

        final String drivers = "drivers";
        String driverName = browserType + "driver";
        if(os.equals(OsUtils.OS.WINDOWS)) {
            driverName += ".exe";
        }
        return String.format("/%s/%s/%s/%s", drivers,os.name().toLowerCase(), browserType, driverName);
    }

    public static String getCliPath(OsUtils.OS os) {

        final String cli = "cli";
        String cliFileName = "codefresh";
        if(os.equals(OsUtils.OS.WINDOWS)) {
            cliFileName += ".exe";
        }
        return String.format("/%s/%s/%s", cli,os.name().toLowerCase(), cliFileName);
    }
}
