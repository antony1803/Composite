package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.CustomTextParser;

public class TextParser implements CustomTextParser {
    private static final String PARAGRAPH_LIMITER = "[\\n\\t]+";
    private final CustomTextParser compositeParser = new ParagraphParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite textComposite = new TextComposite(TextElementType.TEXT);
        String[] paragraphs = text.strip().split(PARAGRAPH_LIMITER);

        for (String paragraph : paragraphs) {
            CustomTextComponent paragraphComponent = compositeParser.parse(paragraph);
            textComposite.add(paragraphComponent);
        }

        return textComposite;
    }
}
