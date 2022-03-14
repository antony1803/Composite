package by.kukyan.texthandler.reader;

import by.kukyan.texthandler.exception.CustomException;

public interface TextReader {
    String readTextFromFile(String path) throws CustomException;
}
