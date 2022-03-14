package by.kukyan.texthandler.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.reader.TextReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class textReaderImplementation implements TextReader {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String readTextFromFile(String path) throws CustomException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL res = classLoader.getResource(path);
        if(res == null){
            logger.error("file doesnt exist", path);
            throw new CustomException("file doesnt exist" + path);
        }

        String result = "";
        try{
            result = Files.readString(Paths.get(res.toURI()));
        }catch (IOException | URISyntaxException e){
            logger.error("cant read");
            throw new CustomException("cant read");
        }
        return result;
    }
}
