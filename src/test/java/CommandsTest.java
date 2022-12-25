import commands.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static utils.Executer.executeLine;

public class CommandsTest extends Assert {

    @Test
    public void testNotValidCommand(){
        assertEquals(executeLine("is"),"Неизвестная команда: is");
    }

    @Test
    public void testEchoCommandOneWord(){
        assertEquals(executeLine("echo word"),"word");
    }

    @Test
    public void testEchoCommandAFewWords(){
        assertEquals(executeLine("echo A few words"),"A few words");
    }

    @Test
    public void testOutCommandOnValidFile(){
        assertEquals(executeLine("python main.py"),"Hello world!\n");
    }

    @Test
    public void testOutCommandOnNotValidFile(){
        assertEquals(executeLine("python not_valid.py"),"");
    }

    @Test
    public void testPwdCommand(){
        assertEquals(executeLine("pwd"),System.getProperty("user.dir"));
    }

    @Test
    public void testLsCommand() {
        CommandLS command = new CommandLS("ls");
        String output = command.buildOutput();
        String[] fileNames = output.split("\n");

        File curDir = new File(System.getProperty("user.dir"));
        File[] files = curDir.listFiles();
        assert files.length == fileNames.length;
        for (int i = 0; i < files.length; i++) {
            assert files[i].getName().equals(fileNames[i]);
        }
    }

    @Test
    public void testCatCommandOnValidFile(){
        assertEquals(executeLine("cat example.txt"),"попробуй этих мягких французских булок\nда выпей чаю\n");
    }

    @Test
    public void testCatCommandOnNotValidFile(){
        assertEquals(executeLine("cat notvalid.txt"),"File doesn't exist");
    }

    @Test
    public void testCatCommandWithoutParameters(){
        assertEquals(executeLine("cat"),"Wrong argument");
    }

    @Test
    public void testWcCommandOnValidFile(){
        assertEquals(executeLine("wc example.txt"),"2 8 96 ");
    }

    @Test
    public void testWcCommandOnNotValidFile(){
        assertEquals(executeLine("wc bad.txt"),"1 1 0 ");
    }

    @Test
    public void testWcCommandWithoutParameters(){
        assertEquals(executeLine("wc"),"Wrong argument");
    }
}