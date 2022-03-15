package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.CustomTextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements CustomTextParser {

    private static final String SENTENCE_PATTERN = "[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)";
    private final CustomTextParser compositeParser = new WordParser();

    @Override
    public TextComposite parse(String text) {

        TextComposite sentenceComposite = new TextComposite(TextElementType.SENTENCE);
        Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
        Matcher matcher = pattern.matcher(text);

        List<String> sentences = new ArrayList<>();
        while (matcher.find()) {
            sentences.add(matcher.group());
        }

        for (int i = 0; i < sentences.size(); i++) {
            CustomTextComponent wordComponents = compositeParser.parse(sentences.get(i));
            sentenceComposite.add(wordComponents);
        }

        return sentenceComposite;
    }
}
