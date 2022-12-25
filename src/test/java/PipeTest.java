import org.junit.Assert;
import org.junit.Test;

import static utils.Executer.executeLine;

public class PipeTest extends Assert {

    @Test
    public void testPipeOnValidFile() {
        assertEquals(executeLine("cat example.txt | wc"), "2 8 0 ");
    }

    @Test
    public void testPipeOnNotValidFile() {
        assertEquals(executeLine("cat bad.txt | wc"), "1 3 0 ");
    }

    @Test
    public void testPipeOnPythonValidFile() {
        assertEquals(executeLine("cat main.py | wc"), "1 2 0 ");
    }

    @Test
    public void testPipeOnCatAndEcho() {
        assertEquals(executeLine("cat example.txt | echo word"), "word");
    }

    @Test
    public void testPipeOnCatAndWc() {
        assertEquals(executeLine("wc | echo word"), "1 1 0 \nword");
    }

    @Test
    public void testPipeOnGrepAndEcho() {
        assertEquals(executeLine("grep  \"точка\" exampleSecond.txt | echo 1"), "1");
    }

    @Test
    public void testPipeOnGrepEchoAndCat() {
        assertEquals(executeLine("grep  \"точка\" exampleSecond.txt | echo 1 | cat main.py"), "print(\"Hello world!\")");
    }
}