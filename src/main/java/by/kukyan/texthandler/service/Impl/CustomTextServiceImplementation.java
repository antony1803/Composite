package by.kukyan.texthandler.service.Impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.Symbol;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.service.CustomTextService;

import java.util.*;
import java.util.stream.Collectors;

public class CustomTextServiceImplementation implements CustomTextService {
    public static final String VOWEL_REGEX = "[aAeEiIoOuU]";
    public static final String CONSONANT_REGEX = "[a-zA-Z&&[^aAeEiIoOuU]]";

    @Override
    public List<CustomTextComponent> findSentencesWithLongestWord(CustomTextComponent component) throws CustomException {
        checkComponentForRequired(component, TextElementType.TEXT);
        List<CustomTextComponent> words = component.getInnerComponents().stream()
                .flatMap(paragraph -> paragraph.getInnerComponents().stream())
                .flatMap(sentence -> sentence.getInnerComponents().stream()).toList();
        long maxLength = 0L;
        for (CustomTextComponent word : words) {
            long currentWordLength = word.getInnerComponents().stream()
                    .filter(symbol -> symbol.getComponentElementType() == TextElementType.LETTER)
                    .count();
            if (currentWordLength > maxLength) {
                maxLength = currentWordLength;
            }
        }

        List<CustomTextComponent> sentences = new ArrayList<>();

        sentences.addAll(component.getInnerComponents().stream()
                .flatMap(paragraph -> paragraph.getInnerComponents().stream()).toList());
        List<CustomTextComponent> sentencesWithLongestWord = new ArrayList<>();
        for(var sentence : sentences){
            for(var word : sentence.getInnerComponents()){
                long currentWordLenght = word.getInnerComponents().stream()
                        .filter(temp -> temp.getComponentElementType() == TextElementType.LETTER).count();
                if(currentWordLenght == maxLength){
                    sentencesWithLongestWord.add(sentence);
                }
            }
        }
        return sentencesWithLongestWord;
    }

    @Override
    public void removeSentencesWithWordsMoreThan(CustomTextComponent component, int numberOfWords) throws CustomException {
        checkComponentForRequired(component, TextElementType.PARAGRAPH);
        component.getInnerComponents().forEach(paragraph ->
                paragraph.getInnerComponents().removeIf(sentence -> sentence.getInnerComponents().size() > numberOfWords));
    }

    @Override
    public int countVowelsInSentence(CustomTextComponent component) throws CustomException {
        return countLettersMatchingPattern(component, VOWEL_REGEX);
    }

    @Override
    public int countConsonantsInSentence(CustomTextComponent component) throws CustomException {
        return countLettersMatchingPattern(component, CONSONANT_REGEX);
    }

    @Override
    public Map<String, Integer> findWordsFrequency(CustomTextComponent component) throws CustomException {
        checkComponentForRequired(component, TextElementType.TEXT);

        Map<String, Integer> wordWithFrequency = new HashMap<>();
        component.getInnerComponents().stream()
                .flatMap(paragraph -> paragraph.getInnerComponents().stream())
                .flatMap(sentence -> sentence.getInnerComponents().stream())
                .filter(phrase -> phrase.getComponentElementType() == TextElementType.WORD)
                .forEach(word -> {
                    String wordStr = word.getInnerComponents().stream()
                            .map(symbol -> ((Symbol)symbol).getSymbol())
                            .collect(Collectors.joining()).toLowerCase(Locale.ROOT);

                    if (wordWithFrequency.containsKey(wordStr)) {
                        int wordFrequency = wordWithFrequency.get(wordStr) + 1;
                        wordWithFrequency.put(wordStr, wordFrequency);
                    } else {
                        wordWithFrequency.put(wordStr, 1);
                    }
                });

        return wordWithFrequency;
    }

    private void checkComponentForRequired(CustomTextComponent component, TextElementType required) throws CustomException{
        if(component.getComponentElementType() != required){
            throw new CustomException("Invalid component type");
        }
    }

    private int countLettersMatchingPattern(CustomTextComponent component, String pattern){
        return component.getInnerComponents().stream()
                .flatMap(paragraph -> paragraph.getInnerComponents().stream())
                .flatMap(sentence -> sentence.getInnerComponents().stream())
                .flatMap(word -> word.getInnerComponents().stream())
                .filter(letter -> letter.getComponentElementType() == TextElementType.LETTER)
                .filter(letter -> letter.toString().matches(pattern))
                .toList().size();
    }
}
