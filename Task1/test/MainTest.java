import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @ParameterizedTest
    @ValueSource(strings = {"123456", "123456.12","123456,12","123456 789111","-123456", "-123456.12","-123456,12","-123456 789111"})
    void correctSumInput(String str) {
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        Long actRes = Main.inputSumValue(stream);
        Assertions.assertEquals(123456,actRes);

    }

    @ParameterizedTest
    @ValueSource(strings = {"a123456", "12.3456.12","123 456,12","assdf 789111","null"})
    void incorrectSumInput(String str) {
        InputStream stream = new ByteArrayInputStream(str.getBytes());

    }


    @ParameterizedTest
    @ValueSource(strings = {"12345", "12345,6","-12345", "12345.6"})
    void testHandleInputWithCorrectStrings(String Strings) {
        Long actualResult = Main.handleInput(Strings);
        Assertions.assertEquals(12345,actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcabc", "123q23",".....", "123,23,123"})
    void testHandleInputWithIncorrectStrings(String Strings) {
       Assertions.assertThrows(NumberFormatException.class, ()->{Main.handleInput(Strings);});
    }

    @Test
    void exchangeWays() {
    }
}