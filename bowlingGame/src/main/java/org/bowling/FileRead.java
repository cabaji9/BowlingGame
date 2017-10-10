package org.bowling;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Obtains the input from user with the complete path of the file
 * Validates the file
 * Read its content
 * And return it to start.
 * Created by Hyun Woo Son on 10/9/17.
 */
public class FileRead {

    static Logger log = Logger.getLogger(StartPlay.class);

    private InputStream inputStream;


    public FileRead(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<String> getFileContents() throws Exception {
        List<String> fileContentsList = new ArrayList<>();
        String filePath = getFilePathByUserInput();
        if (validateFilePath(filePath)) {
            log.debug("getFileContents | File path correct proceed to read contents");
            fileContentsList = obtainAllFileContents(filePath);
        } else {
            log.error("File does not exist or path is incorrect try again.");
        }
        return fileContentsList;
    }


    private List<String> obtainAllFileContents(String pathStr) throws Exception {
        Path path = Paths.get(pathStr);
        BufferedReader reader = Files.newBufferedReader(path);
        List<String> fileContentsList = reader.lines().collect(Collectors.toList());
        log.debug("obtainAllFileContents | Readed from file: " + fileContentsList);
        return fileContentsList;
    }

    private String getFilePathByUserInput() {
        log.debug("getFilePathByUserInput | game starting asking for input.");
        Scanner reader = new Scanner(inputStream);  // Reading from System.in
        System.out.println("Enter file complete path (only txt): ");
        String filePath = reader.next(); // Scans the next token of the input as an int.
        log.debug("getFilePathByUserInput | file : " + filePath);
        reader.close();
        return filePath;
    }

    private boolean validateFilePath(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
