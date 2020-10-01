import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void inputData() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "12345,6","-12345", "12345.6"})
    void testHandleInputWithCorrectStrings(String Strings) {
        Main mainObj = new Main();
        Long actualResult = mainObj.handleInput(Strings);
        Assertions.assertEquals(12345,actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcabc", "123q23",".....", "123,23,123"})
    void testHandleInputWithIncorrectStrings(String Strings) {
        Main mainObj = new Main();
        Assertions.assertThrows(NumberFormatException.class, ()->{mainObj.handleInput(Strings);});
    }

    @Test
    void exchangeWays() {
    }
}