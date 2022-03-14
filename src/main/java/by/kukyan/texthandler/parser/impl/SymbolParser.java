package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.Symbol;
import by.kukyan.texthandler.entity.TextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.TextParser;

public class SymbolParser implements TextParser {

    private static final String SYMBOL_SEPARATOR = "";
    private static final String NUMBER_PATTERN = "\\d";
    private static final String PUNCTUATION_PATTERN = "[\\p{Punct}\\s]";

    @Override
    public TextComposite parse(String text) {
        String[] symbols = text.strip().split(SYMBOL_SEPARATOR);
        TextComposite symbolComposite = new TextComposite(TextElementType.SYMBOL);

        for (String symbol : symbols) {
            if (!symbol.isBlank()) {
                TextElementType type;

                if (symbol.matches(NUMBER_PATTERN)) {
                    type = TextElementType.NUMBER;
                } else if (symbol.matches(PUNCTUATION_PATTERN)) {
                    type = TextElementType.PUNCTUATION;
                } else {
                    type = TextElementType.LETTER;
                }
                TextComponent component = new Symbol(symbol.charAt(0), type);
                symbolComposite.add(component);
            }
        }
        return symbolComposite;
    }
}
