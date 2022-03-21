package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.CustomTextParser;

public class WordParser implements CustomTextParser {

    private static final String SYMBOL_SEPARATOR = "";
    private final CustomTextParser compositeParser = new SymbolParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite wordComposite = new TextComposite(TextElementType.WORD);
        String[] symbols = text.strip().split(SYMBOL_SEPARATOR);

        for (String symbol : symbols) {
            CustomTextComponent paragraphComponent = compositeParser.parse(symbol);
            wordComposite.add(paragraphComponent);
        }
        return wordComposite;
    }
}
