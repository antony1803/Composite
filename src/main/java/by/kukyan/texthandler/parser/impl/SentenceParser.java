package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.TextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {

    private static final String SENTENCE_PATTERN = "[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)";
    private final WordParser wordParser = new WordParser();

    @Override
    public TextComposite parse(String text) {

        TextComposite sentenceComposite = new TextComposite(TextElementType.SENTENCE);
        Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
        Matcher matcher = pattern.matcher(text);

        List<String> sentences = new ArrayList<>();
        while (matcher.find()) {
            sentences.add(matcher.group());
        }

        for (String sentence : sentences) {
            TextComponent wordComponents = wordParser.parse(sentence);
            sentenceComposite.add(wordComponents);
        }

        return sentenceComposite;
    }
}
