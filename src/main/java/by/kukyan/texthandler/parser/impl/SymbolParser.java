package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.Symbol;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.CustomTextParser;

public class SymbolParser implements CustomTextParser {
    private static final String NUMBER_PATTERN = "\\d";
    private static final String PUNCTUATION_PATTERN = "[\\p{Punct}\\s]";

    @Override
    public TextComposite parse(String text) {
        TextComposite symbolComposite = new TextComposite(TextElementType.SYMBOL);
        if (!text.isBlank()) {
            TextElementType type;

            if (text.matches(NUMBER_PATTERN)) {
                type = TextElementType.NUMBER;
            } else if (text.matches(PUNCTUATION_PATTERN)) {
                type = TextElementType.PUNCTUATION;
            } else {
                type = TextElementType.LETTER;
            }
            CustomTextComponent component = new Symbol(text.charAt(0), type);
            symbolComposite.add(component);
        }
        return symbolComposite;
    }
}
