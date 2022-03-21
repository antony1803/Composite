package by.kukyan.texthandler.reader;

import by.kukyan.texthandler.exception.CustomException;
import by.kukyan.texthandler.reader.impl.TextReaderImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TestReaderTest {
    private static final String TEXT = "data/parserTest.txt";
    private TextReader reader;
    String expected;

    @BeforeClass
    public void setUp(){
        reader = new TextReaderImpl();
        expected = "Hi, its parser test.\n" +
                "Lets count something (2+3)/5*10+2 equals 2*2*3 nice.\n" +
                "Bye.";
    }

    @Test
    public void testRead(){
        String actual = "";
        try {
            actual = reader.readTextFromFile(TEXT);
        } catch (CustomException e) {
            fail("failed read");
        }
        assertEquals(actual, expected);
    }
}
