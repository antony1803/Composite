package by.kukyan.texthandler.service;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.parser.impl.SentenceParser;
import by.kukyan.texthandler.parser.impl.TextParser;
import by.kukyan.texthandler.service.impl.CustomTextServiceImpl;
import by.kukyan.texthandler.service.impl.SorterBySize;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextServiceTest {
    private static final String VOWEL_REGEX = "[aAeEiIoOuU]";
    private static final String CONSONANT_REGEX = "[a-zA-Z&&[^aAeEiIoOuU]]";
    private static final Logger logger = LogManager.getLogger();
    private TextComposite composite;
    private SorterBySize sorter;
    private CustomTextServiceImpl service;
    private TextParser parser;


    @BeforeClass
    public void setUp(){
        parser = new TextParser();
        sorter = new SorterBySize();
        service = new CustomTextServiceImpl();
        String text = "Hi, its parser test again. And now we are testing some services.\n" +
                "Lets count something (2+3)/5*10+2 equals 2*2*3 nice. Its been a long time since i had such a bad headache. but nevertheless here i am.\n" +
                "Bye.";
        composite = parser.parse(text);
    }

    @Test
    public void testTextSorterBySize(){
        try {
            sorter.sort(composite);
        } catch (CustomException e) {
           logger.error("fail trying to sort", e);
        }
        String expected = "Lets count something 12.0 equals 12.0 nice. Its been a long time since i had such a bad headache. but nevertheless here i am. \n" +
                "Hi, its parser test again. And now we are testing some services. \n" +
                "Bye. \n";
        String actual = composite.toString();

        assertEquals(actual, expected);
    }

    @Test
    public void testCountConsonant(){
        int expected = 85;
        int actual = service.countLettersMatchingPattern(composite, CONSONANT_REGEX);
        assertEquals(actual, expected);
    }

    @Test
    public void testCountVowels(){
        int expected = 59;
        int actual = service.countLettersMatchingPattern(composite, VOWEL_REGEX);
        assertEquals(actual, expected);
    }

    @Test
    public void testRemoveSentencesWithMoreThanThisNumberOfWords(){
        try {
            service.removeSentencesWithWordsMoreThan(composite,5);
        } catch (CustomException e) {
            logger.error("service error", e);
        }
        String expected = "Hi, its parser test again. \n" +
                "but nevertheless here i am. \n" +
                "Bye. \n";
        String actual = composite.toString();
        assertEquals(actual, expected);
    }

    @Test
    public void testFindSentencesWithLongestWord(){
        List<CustomTextComponent> actual = null;
        try {
            actual = service.findSentencesWithLongestWord(composite);
        } catch (CustomException e) {
            logger.error("service error", e);
        }
        List<CustomTextComponent> expected = new ArrayList<>();
        SentenceParser sParser = new SentenceParser();
        TextComposite expectedSentence = sParser.parse("but nevertheless here i am. \n");
        expected.add(expectedSentence);
        assertEquals(actual.toString(), expected.toString());
    }

}
