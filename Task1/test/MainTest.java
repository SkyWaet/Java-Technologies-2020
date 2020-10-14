import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @ParameterizedTest
    @ValueSource(strings = {"123456", "123456.12", "123456,12", "123456 789111", "-123456", "-123456.12", "-123456,12", "-123456 789111"})
    void testCorrectSumInput(String str) {
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        Long actRes = Main.inputSumValue(stream);
        Assertions.assertEquals(123456, actRes);
    }


    @ParameterizedTest
    @ValueSource(strings = {"1 2 3 4 5 6", "1 2.0 3,5 -4,56 5.00000 -6"})
    void testCorrectNominalsInput(String str) {
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        Long[] actRes = Main.inputNominals(stream);
        Long[] expResult = new Long[6];
        for (int i = 0; i <= 5; i++) {
            expResult[i] = (long) (i + 1);
        }
        Assertions.assertArrayEquals(expResult, actRes);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "12345,6", "-12345", "12345.6"})
    void testHandleInputWithCorrectStrings(String Strings) {
        Long actualResult = Main.handleInput(Strings);
        Assertions.assertEquals(12345, actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcabc", "123q23", ".....", "123,23,123"})
    void testHandleInputWithIncorrectStrings(String Strings) {
        Assertions.assertThrows(NumberFormatException.class, () -> Main.handleInput(Strings));
    }


    @Test
    void testExchangeWays() {
        long sum = 5;
        Long[] longCashData = new Long[2];
        for (int i = 0; i < 2; i++) {
            longCashData[i] = (long) (i + 1);
        }

        int size = longCashData.length;
        Long[] currentCombination = new Long[size];
        for (int i = 0; i < size; i++) {
            currentCombination[i] = (long) 0;
        }
        Assertions.assertEquals(3, Main.exchangeWays(sum, longCashData, currentCombination, size - 1));
    }

}