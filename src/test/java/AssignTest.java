import org.junit.Assert;
import org.junit.Test;

import static utils.Executer.executeLine;

public class AssignTest extends Assert {

    @Test
    public void testSimpleAssignNumber() {
        assertEquals(executeLine("a=5"),null);
        assertEquals(executeLine("echo $a"),"5");
    }

    @Test
    public void testSimpleAssignLetter() {
        assertEquals(executeLine("c=b"),null);
        assertEquals(executeLine("echo $c"),"b");
    }

    @Test
    public void testAssignLetterInQuotes() {
        assertEquals(executeLine("k=\"b\""),null);
        assertEquals(executeLine("echo $k"),"b");
    }

    @Test
    public void testAssignLetterInTwoQuote() {
        assertEquals(executeLine("d='v'"),null);
        assertEquals(executeLine("echo $d"),"v");
    }

    @Test
    public void testAssignLetterInPipes() {
        assertEquals(executeLine("r=|6|"),"Неизвестная команда: 6");
        assertEquals(executeLine("echo $r"),"");
    }

    @Test
    public void testAssignEqualsUnderQuotes() {
        assertEquals(executeLine("t=\"=6\""),null);
        assertEquals(executeLine("echo $t"),"=6");
    }

    @Test
    public void testAssignWothoutArgument() {
        assertEquals(executeLine("l="),null);
        assertEquals(executeLine("echo $l"),"");
    }

    @Test
    public void testAssignWothoutNameOfVariable() {
        assertEquals(executeLine("=100"),null);
        assertEquals(executeLine("echo $"),"$");
    }

    @Test
    public void testAssignModulation() {
        assertEquals(executeLine("a=1000"),null);
        assertEquals(executeLine("b=a"),null);
        assertEquals(executeLine("echo $b"),"a");
    }
}