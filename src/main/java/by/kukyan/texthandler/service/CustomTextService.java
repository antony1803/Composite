package by.kukyan.texthandler.service;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.exception.CustomException;

import java.util.List;
import java.util.Map;

public interface CustomTextService {
    List<CustomTextComponent> findSentencesWithLongestWord(CustomTextComponent component) throws CustomException;

    void removeSentencesWithWordsMoreThan(CustomTextComponent component, int numberOfWords) throws CustomException;

    Map<String, Integer> findWordsFrequency(CustomTextComponent component) throws CustomException;

    int countLettersMatchingPattern(CustomTextComponent component, String pattern);
}
