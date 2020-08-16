package com.codefresh.utils;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

public class TestFolderUtils {

    private final static String testsFolder = "codefresh";
    private final static String homeFolder = System.getProperty("user.home")
            + File.separator + testsFolder;

    public static void createFolderForLogs() throws SecurityException {

        try {
        Path homeFolderPath = Paths.get(homeFolder);
        if(!Files.isDirectory(homeFolderPath)) {
            Files.createDirectory(homeFolderPath);
        }
        } catch (Exception e){
            throw new SecurityException("can't create parent folder");
        }

    }
    public static String createTestsFolder() throws SecurityException  {

            Instant instant = Instant.now();
            Path folderPath = Paths.get(homeFolder, String.valueOf(instant.getEpochSecond()));
            return createFolder(folderPath);
    }

    public static void copyFileToNewLocation(String resourcePath,String fileName, String dirToCopy) throws IOException {

        URL resourceUrl = TestFolderUtils.class.getResource(resourcePath);
        createFolder(Paths.get(dirToCopy));
        File extractedResourceDest = new File(dirToCopy + File.separator + fileName);
        FileUtils.copyURLToFile(resourceUrl, extractedResourceDest);
    }

    public static String createFolder(Path folderPath) throws SecurityException  {

        try {
            if(!Files.exists(folderPath)) {
                Files.createDirectory(folderPath);
            }
            return folderPath.toString();
        } catch (Exception e){
            throw new SecurityException("can't create folder :"+ folderPath.toString());
        }
    }
}
