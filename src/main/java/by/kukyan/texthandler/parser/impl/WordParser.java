package by.kukyan.texthandler.parser.impl;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.parser.CustomTextParser;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Optional;
import java.util.regex.Pattern;

public class WordParser implements CustomTextParser {

    private static final String WORD_LIMITER = "\\s+";
    private static final String PATTERN_FOR_CALCULATION = "([0-9]+[\\+\\-\\*\\/][0-9]+)+([\\+\\-\\*\\/][0-9]+)*";

    private final CustomTextParser compositeParser = new SymbolParser();

    @Override
    public TextComposite parse(String text) {
        TextComposite wordComposite = new TextComposite(TextElementType.WORD);
        String[] words = text.strip().split(WORD_LIMITER);
        Pattern pattern = Pattern.compile(PATTERN_FOR_CALCULATION);

        for (int i = 0; i < words.length; i++) {
            if (pattern.matcher(words[i]).find()) {
                words[i] = calculateArithmetic(words[i]).orElse(words[i]);
            }
        }

        for (int i = 0; i < words.length; i++) {
            CustomTextComponent symbolComponent = compositeParser.parse(words[i]);
            wordComposite.add(symbolComponent);
        }

        return wordComposite;
    }

    private Optional<String> calculateArithmetic(String input) {
        Expression expression = new ExpressionBuilder(input).build();
        String result = String.valueOf(expression.evaluate());
        return Optional.of(result);
    }
}