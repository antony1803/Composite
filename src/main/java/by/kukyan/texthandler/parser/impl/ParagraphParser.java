package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.CustomTextParser;

public class ParagraphParser implements CustomTextParser {
    private static final String PARAGRAPH_LIMITER = "[\\n\\t]+";
    private final CustomTextParser compositeParser = new SentenceParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite composite = new TextComposite(TextElementType.PARAGRAPH);
        String[] paragraphs = text.strip().split(PARAGRAPH_LIMITER);

        for (String paragraph : paragraphs) {
            CustomTextComponent paragraphComponent = compositeParser.parse(paragraph);
            composite.add(paragraphComponent);
        }
        return composite;
    }
}
