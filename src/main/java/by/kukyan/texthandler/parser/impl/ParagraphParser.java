package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.TextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.TextParser;

public class ParagraphParser implements TextParser {
    private static final String PARAGRAPH_PATTERN = "[\\n\\t]+";
    private final SentenceParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite paragraphComposite = new TextComposite(TextElementType.PARAGRAPH);
        String[] paragraphs = text.strip().split(PARAGRAPH_PATTERN);

        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = sentenceParser.parse(paragraph);
            paragraphComposite.add(paragraphComponent);
        }
        return paragraphComposite;
    }
}
