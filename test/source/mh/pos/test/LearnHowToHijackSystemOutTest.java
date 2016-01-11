package mh.pos.test;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static mh.pos.test.TextUtilities.lines;


public class LearnHowToHijackSystemOutTest {

    private PrintStream productionSystemOut;
    @Before
    public void rememberSystemOut(){
        productionSystemOut = System.out;
    }

    @After
    public void restoreSystemOut(){
        System.setOut(productionSystemOut);
    }

    @Test
    public void singleLineOfText() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        System.out.println("hello world");

        Assert.assertEquals(
                Collections.singletonList("hello world"),
                lines(canvas.toString("UTF-8")));
    }

    @Test
    public void multipleLinesOfText() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        System.out.println("1");
        System.out.println("2");
        System.out.println("3");

        Assert.assertEquals(
                Arrays.asList("1","2","3"),
                lines(canvas.toString("UTF-8")));
    }


}
