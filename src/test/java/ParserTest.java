import org.junit.Assert;
import org.junit.Test;
import static utils.Executer.executeLine;

public class ParserTest extends Assert {

    @Test
    public void testExecuterSimpleCapitalLetter(){
        assertEquals(executeLine("echo \"F\""),"F");
    }

    @Test
    public void testExecuterSimpleSmallLetter(){
        assertEquals(executeLine("echo \"f\""),"f");
    }

    @Test
    public void testExecuterSimpleNumber(){
        assertEquals(executeLine("echo \"1\""),"1");
    }

    @Test
    public void testExecuterSimplePipe(){
        assertEquals(executeLine("echo \"|\""),"");
    }

    @Test
    public void testExecuterSimplePipeWithSpaces(){
        assertEquals(executeLine("echo \" | \"")," | ");
    }

    @Test
    public void testExecuterOnePipe(){
        assertEquals(executeLine("echo \"lk|\""),"lk|");
    }

    @Test
    public void testExecuterTwoPipe(){
        assertEquals(executeLine("echo \"1|1\""),"1|1");
    }

    @Test
    public void testExecuterSimpleQuotes(){
        assertEquals(executeLine("echo \"\"\""),"");
    }

    @Test
    public void testExecuterSimpleTwoQuotesWithSpaces(){
        assertEquals(executeLine("echo \"\"\"\""),"");
    }

    @Test
    public void testExecuterTwoQuotesWithSpaces(){
        assertEquals(executeLine("echo \" \" \" \""),"   ");
    }

    @Test
    public void testExecuterSimpleQuoteWithSpaces(){
        assertEquals(executeLine("echo \" ' \"")," ' ");
    }

    @Test
    public void testExecuterSimpleTwoQuoteWithSpaces(){
        assertEquals(executeLine("echo \" ' ' \"")," ' ' ");
    }

    @Test
    public void testExecuterSimpleSlashQuote(){
        assertEquals(executeLine("echo \"`\""),"`");
    }

    @Test
    public void testExecuterSimpleTwoSlashQuotes(){
        assertEquals(executeLine("echo \"``\""),"``");
    }

    @Test
    public void testExecuterSimpleDollar(){
        assertEquals(executeLine("echo \"$\""),"$");
    }
}