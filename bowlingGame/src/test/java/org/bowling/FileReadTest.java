package org.bowling;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hyun Woo Son on 10/9/17.
 */


public class FileReadTest {

    static Logger log = Logger.getLogger(FileReadTest.class);

    private FileRead fileRead;
    protected List<String> fileContentList;

    private static String FILE_NAME="test.txt";

    @Before
    public void setUp(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/"+FILE_NAME).getFile());
        String path = file.getAbsolutePath();
        log.info("path is : "+ path);
        assertTrue(path.contains(FILE_NAME));
        ByteArrayInputStream in = new ByteArrayInputStream(path.getBytes());
        fileRead = new FileRead(in);

    }


    @Test
    public void fileRead() throws Exception{
        fileContentList = fileRead.getFileContents();
        assertTrue(fileContentList.size() > 0);
    }

}
