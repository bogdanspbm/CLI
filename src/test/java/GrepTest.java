import org.junit.Assert;
import org.junit.Test;

import static utils.Executer.executeLine;

public class GrepTest extends Assert {

    @Test
    public void testSimpleOnGrepOnOneWordInRowWithFlagW() {
        assertEquals(executeLine("grep -w \"точка\" exampleSecond.txt"), "точка\n");
    }

    @Test
    public void testSimpleOnGrepOnOneWordInRowWithoutFlagW() {
        assertEquals(executeLine("grep \"точка\" exampleSecond.txt"), "точка\n");
    }

    @Test
    public void testSimpleOnGrepOnOneInvalidWordInRowWithFlagW() {
        assertEquals(executeLine("grep -w \"точ\" exampleSecond.txt"), "");
    }

    @Test
    public void testSimpleOnGrepOnWordsInRow() {
        assertEquals(executeLine("grep -w \"новое\" exampleSecond.txt"), "попробуй новое предложение\n");
    }

    @Test
    public void testOnGrepOnWordsInRowWithPipe() {
        assertEquals(executeLine("grep -w \"родителями\" exampleSecond.txt"), "приходи с друзьями | родителями\n");
    }

    @Test
    public void testOnGrepOnFakeWord() {
        assertEquals(executeLine("grep -w \"класс\" exampleSecond.txt"), "");
    }

    @Test
    public void testOnGrepOnNotValidWordWithDollar() {
        assertEquals(executeLine("grep -w \"родителями$\" exampleSecond.txt"), "");
    }

    @Test
    public void testOnGrepOnNotValidWord() {
        assertEquals(executeLine("grep -w \"^родителями\" exampleSecond.txt"), "");
    }

    @Test
    public void testSimpleOnThePartOfTheWord() {
        assertEquals(executeLine("grep  \"франц\" exampleSecond.txt"), "попробуй этих мягких французских булок\n");
    }

    @Test
    public void testSimpleOnSearcingWordWithTwoInFile() {
        assertEquals(executeLine("grep  \"купи\" exampleSecond.txt"), "купи \"квас\"\nкупи 2 квас\n");
    }

    @Test
    public void testOnGrepWitFlagI() {
        assertEquals(executeLine("grep -i \"ривер\" exampleSecond.txt"), "крутой Ривер\n");
    }

    @Test
    public void testOnGrepWithFlagA0Rows() {
        assertEquals(executeLine("grep -A0 \"чаю\" exampleSecond.txt"), "да выпей чаю\n");
    }

    @Test
    public void testOnGrepWithFlagA3Rows() {
        assertEquals(executeLine("grep -A3 \"чаю\" exampleSecond.txt"), "да выпей чаю\nпопробуй новое предложение\nкупи \"квас\"\nприходи с друзьями | родителями\n");
    }

    @Test
    public void testOnGrepWithFlagA10Rows() {
        assertEquals(executeLine("grep -A10 \"крутой\" exampleSecond.txt"), "крутой Ривер\n");
    }

    @Test
    public void testOnGrepWithFlagA5Rows() {
        assertEquals(executeLine("grep -A5 \"учитель\" exampleSecond.txt"), "");
    }

    @Test
    public void testOnGrepWithFlagA3IW() {
        assertEquals(executeLine("grep  -A3 -i -w  \"квас\"  exampleSecond.txt"), "купи \"квас\"\nприходи с друзьями | родителями\nкупи 2 квас\nточка\nкрутой Ривер\n");
    }

    @Test
    public void testOnGrepWithFlagA2IW() {
        assertEquals(executeLine("grep  -A2 -i -w  \"Новое\"  exampleSecond.txt"), "попробуй новое предложение\nкупи \"квас\"\nприходи с друзьями | родителями\n");
    }

    @Test
    public void testOnGrepWithFlagIW() {
        assertEquals(executeLine("grep  -i  -w  \"купи\"  exampleSecond.txt"), "купи \"квас\"\nкупи 2 квас\n");
    }

    @Test
    public void testOnGrepWithFlagAWWrongConcatenation() {
        assertEquals(executeLine("grep  -A3w  \"этих\"  exampleSecond.txt"), "");
    }
}