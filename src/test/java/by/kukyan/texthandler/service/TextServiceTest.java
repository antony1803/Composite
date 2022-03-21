package by.kukyan.texthandler.service;

import by.kukyan.texthandler.entity.CustomTextComponent;
import by.kukyan.texthandler.entity.TextComposite;
import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.parser.impl.ParagraphParser;
import by.kukyan.texthandler.parser.impl.SentenceParser;
import by.kukyan.texthandler.parser.impl.TextParser;
import by.kukyan.texthandler.service.impl.CustomTextServiceImpl;
import by.kukyan.texthandler.service.impl.SorterBySize;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class TextServiceTest {
    private static final String VOWEL_REGEX = "[aAeEiIoOuUyY]";
    private static final String CONSONANT_REGEX = "[a-zA-Z&&[^aAeEiIoOuUyY]]";
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
    public void testSentenceSorterBySize(){
        try {
            sorter.sort(composite);
        } catch (CustomException e) {
            logger.error("fail trying to sort", e);
        }
        ParagraphParser paragraphParser = new ParagraphParser();
        TextComposite paragraph = paragraphParser.parse("Hi, its parser test again. And now we are testing some services. \n");
        try {
            sorter.sort(paragraph);
        } catch (CustomException e) {
            logger.error("sorter error", e);
        }
        String expected = "And now we are testing some services. Hi, its parser test again. \n";
        assertEquals(paragraph.toString(), expected);

    }

    @Test
    public void testCountConsonant(){
        int expected = 84;
        int actual = service.countLettersMatchingPattern(composite, CONSONANT_REGEX);
        assertEquals(actual, expected);
    }

    @Test
    public void testCountVowels(){
        int expected = 60;
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

    @Test
    public void testFindSentencesWithLongestWordWithoutRemovingPunctuation(){
        List<CustomTextComponent> actual = null;
        String punctuationText = "Hi, its parser test again abbreviated. \n" +
                "but nevertheless here i am. \n" +
                "Bye. \n";
        TextComposite punctuationComposite = parser.parse(punctuationText);
        try {
            actual = service.findSentencesWithLongestWordWithoutRemovingPunctuation(punctuationComposite);
        } catch (CustomException e) {
            logger.error("service error", e);
        }
        List<CustomTextComponent> expected = new ArrayList<>();
        SentenceParser sParser = new SentenceParser();
        TextComposite expectedSentence = sParser.parse("Hi, its parser test again abbreviated. \n");
        expected.add(expectedSentence);
        expectedSentence = sParser.parse("but nevertheless here i am. \n");
        expected.add(expectedSentence);
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void testWordFrequency(){
        Map<String, Integer> actual = null;
        Map<String, Integer> expected = new HashMap<>();
        String frequencyText = "Hi, its Anton.\n" +
                "Hi again.\n";
        TextComposite frequencyComposite = parser.parse(frequencyText);
        try {
            actual = service.findWordsFrequency(frequencyComposite);
        } catch (CustomException e) {
            logger.error("frequency error", e);
        }
        expected.put("hi", 2);
        expected.put("anton", 1);
        expected.put("again", 1);
        expected.put("its", 1);
        assertEquals(actual, expected);
    }

}
