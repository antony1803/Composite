package by.kukyan.texthandler.service.impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.Symbol;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.service.CustomTextService;

import java.util.*;
import java.util.stream.Collectors;

public class CustomTextServiceImpl implements CustomTextService {

    @Override
    public List<CustomTextComponent> findSentencesWithLongestWord(CustomTextComponent component) throws CustomException {
        checkComponentForRequired(component, TextElementType.TEXT);
        List<CustomTextComponent> words = component.getInnerComponents().stream()
                .flatMap(paragraph -> paragraph.getInnerComponents().stream())
                .flatMap(sentence -> sentence.getInnerComponents().stream()).toList();
        long maxLength = 0;
        for (CustomTextComponent word : words) {
            long currentWordLength = word.getInnerComponents().stream()
                    .filter(symbol -> symbol.getInnerComponents().get(0).getComponentElementType() == TextElementType.LETTER)
                    .count();
            if (currentWordLength > maxLength) {
                maxLength = currentWordLength;
            }
        }

        List<CustomTextComponent> sentences = new ArrayList<>();

        sentences.addAll(component.getInnerComponents().stream()
                        .flatMap(text -> text.getInnerComponents().stream()).toList());
        List<CustomTextComponent> sentencesWithLongestWord = new ArrayList<>();
        for(var sentence : sentences) {
            for (var word : sentence.getInnerComponents()) {
                long currentWordLenght = word.getInnerComponents().stream()
                        .filter(temp -> temp.getInnerComponents().get(0).getComponentElementType() == TextElementType.LETTER).count();
                if (currentWordLenght == maxLength) {
                    sentencesWithLongestWord.add(sentence);
                }
            }
        }
        return sentencesWithLongestWord;
    }

    @Override
    public List<CustomTextComponent> findSentencesWithLongestWordWithoutRemovingPunctuation(CustomTextComponent component) throws CustomException {
        checkComponentForRequired(component, TextElementType.TEXT);
        List<CustomTextComponent> words = component.getInnerComponents().stream()
                .flatMap(paragraph -> paragraph.getInnerComponents().stream())
                .flatMap(sentence -> sentence.getInnerComponents().stream()).toList();
        int maxLength = 0;
        for (CustomTextComponent word : words) {
            if (word.size() > maxLength) {
                maxLength = word.size();
            }
        }

        List<CustomTextComponent> sentences = new ArrayList<>();

        sentences.addAll(component.getInnerComponents().stream()
                .flatMap(paragraph -> paragraph.getInnerComponents().stream()).toList());
        List<CustomTextComponent> sentencesWithLongestWord = new ArrayList<>();
        for(var sentence : sentences) {
            for (var word : sentence.getInnerComponents()) {
                int currentWordLenght = word.size();
                if (currentWordLenght == maxLength) {
                    sentencesWithLongestWord.add(sentence);
                }
            }
        }
        return sentencesWithLongestWord;
    }

    @Override
    public void removeSentencesWithWordsMoreThan(CustomTextComponent component, int numberOfWords) throws CustomException {
        checkComponentForRequired(component, TextElementType.TEXT);
        TextComposite result = new TextComposite(TextElementType.TEXT);
        for(var paragraph : component.getInnerComponents()){
            TextComposite tempParagraph = new TextComposite(TextElementType.PARAGRAPH);
            for (var sentence : paragraph.getInnerComponents()){
                if(sentence.size() > numberOfWords){
                    continue;
                }
                tempParagraph.add(sentence);
            }
            result.add(tempParagraph);
        }
        component.setInnerComponents(result.getInnerComponents());
    }

    @Override
    public Map<String, Integer> findWordsFrequency(CustomTextComponent component) throws CustomException {
        checkComponentForRequired(component, TextElementType.TEXT);

        Map<String, Integer> wordWithFrequency = new HashMap<>();
        for(var paragraph : component.getInnerComponents()){
            for(var sentence : paragraph.getInnerComponents()){
                for(var word : sentence.getInnerComponents()){
                    StringBuilder tempWord = new StringBuilder();
                    for(var symbol : word.getInnerComponents()){
                        if(symbol.getInnerComponents().get(0).getComponentElementType() == TextElementType.LETTER){
                            tempWord.append(Character.toString(((Symbol)symbol.getInnerComponents().get(0)).getSymbol()).toLowerCase());
                        }
                    }
                    if (wordWithFrequency.containsKey(tempWord.toString())) {
                        int wordFrequency = wordWithFrequency.get(tempWord.toString()) + 1;
                        wordWithFrequency.put(tempWord.toString(), wordFrequency);
                    } else {
                        wordWithFrequency.put(tempWord.toString(), 1);
                    }
                }
            }
        }
        return wordWithFrequency;
    }

    @Override
    public int countLettersMatchingPattern(CustomTextComponent component, String pattern){
        return component.getInnerComponents().stream()
                .flatMap(text -> text.getInnerComponents().stream())
                .flatMap(paragraph -> paragraph.getInnerComponents().stream())
                .flatMap(sentence -> sentence.getInnerComponents().stream())
                .flatMap(word -> word.getInnerComponents().stream())
                .filter(letter -> letter.getComponentElementType() == TextElementType.LETTER)
                .filter(letter -> letter.toString().matches(pattern))
                .toList().size();
    }

    private void checkComponentForRequired(CustomTextComponent component, TextElementType required) throws CustomException{
        if(component.getComponentElementType() != required){
            throw new CustomException("Invalid component type");
        }
    }
}
