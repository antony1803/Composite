package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.CustomTextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements CustomTextParser {

    private static final String SENTENCE_PATTERN = "[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)";
    private final CustomTextParser compositeParser = new SentenceParser();

    @Override
    public TextComposite parse(String text) {

        TextComposite paragraphComposite = new TextComposite(TextElementType.PARAGRAPH);
        Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            CustomTextComponent wordComponents = compositeParser.parse(matcher.group());
            paragraphComposite.add(wordComponents);
        }

        return paragraphComposite;
    }
}
