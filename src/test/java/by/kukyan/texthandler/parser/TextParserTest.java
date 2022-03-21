package by.kukyan.texthandler.parser;

import by.kukyan.texthandler.entity.Symbol;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.entity.TextElementType;
import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.parser.impl.TextParser;
import by.kukyan.texthandler.reader.TextReader;
import by.kukyan.texthandler.reader.impl.TextReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextParserTest {
    private static final String TEST_TEXT = "data/parserTest.txt";
    private static final String TEST_COMPOSITE_TEXT =  "data/parserCompositeTest.txt";
    private CustomTextParser parser;
    TextComposite expectedComposite;
    String temp;
    String compositeTemp;

    @BeforeClass
    public void setUp() throws CustomException {
        parser = new TextParser();
        TextReader reader = new TextReaderImpl();
        temp = reader.readTextFromFile(TEST_TEXT);
        compositeTemp = reader.readTextFromFile(TEST_COMPOSITE_TEXT);

        expectedComposite = new TextComposite(TextElementType.TEXT);

        TextComposite paragraph = new TextComposite(TextElementType.PARAGRAPH);
        TextComposite sentence = new TextComposite(TextElementType.SENTENCE);
        TextComposite word = new TextComposite(TextElementType.WORD);

        word.add(new Symbol('H', TextElementType.LETTER));
        word.add(new Symbol('i', TextElementType.LETTER));
        word.add(new Symbol(',', TextElementType.PUNCTUATION));

        sentence.add(word);

        word = new TextComposite(TextElementType.WORD);
        word.add(new Symbol('i', TextElementType.LETTER));
        word.add(new Symbol('t', TextElementType.LETTER));
        word.add(new Symbol('s', TextElementType.LETTER));
        sentence.add(word);

        word = new TextComposite(TextElementType.WORD);
        word.add(new Symbol('p', TextElementType.SYMBOL));
        word.add(new Symbol('a', TextElementType.SYMBOL));
        word.add(new Symbol('r', TextElementType.SYMBOL));
        word.add(new Symbol('s', TextElementType.SYMBOL));
        word.add(new Symbol('e', TextElementType.SYMBOL));
        word.add(new Symbol('r', TextElementType.SYMBOL));
        sentence.add(word);

        word = new TextComposite(TextElementType.WORD);
        word.add(new Symbol('t', TextElementType.LETTER));
        word.add(new Symbol('e', TextElementType.LETTER));
        word.add(new Symbol('s', TextElementType.LETTER));
        word.add(new Symbol('t', TextElementType.LETTER));
        word.add(new Symbol('.', TextElementType.PUNCTUATION));
        sentence.add(word);
        paragraph.add(sentence);
        expectedComposite.add(paragraph);

        paragraph = new TextComposite(TextElementType.PARAGRAPH);
        sentence = new TextComposite(TextElementType.SENTENCE);
        word = new TextComposite(TextElementType.WORD);
        word.add(new Symbol('B', TextElementType.LETTER));
        word.add(new Symbol('y', TextElementType.LETTER));
        word.add(new Symbol('e', TextElementType.LETTER));
        word.add(new Symbol('.', TextElementType.PUNCTUATION));
        sentence.add(word);

        paragraph.add(sentence);
        expectedComposite.add(paragraph);
    }

    @Test
    public void testParse(){
        TextComposite composite = parser.parse(temp);
        String actual = composite.toString();
        String expected = "Hi, its parser test. \n" +
                "Lets count something 12.0 equals 12.0 nice. \n" +
                "Bye. \n";
        assertEquals(actual, expected);
    }

    @Test
    public void testCompositeParse(){
        TextComposite actual = parser.parse(compositeTemp);
        assertEquals(actual.toString(), expectedComposite.toString());
    }
}
