package converter.nik;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NumConverterTest {
    static Stream<Arguments> positiveTestsStringsAndNumbersProvider() {
        return Stream.of(
                arguments("MMMCMXCIX", 3999),
                arguments("I", 1),
                arguments("MCMXCIX", 1999)
        );
    }

    @ParameterizedTest
    @MethodSource("positiveTestsStringsAndNumbersProvider")
    void converter_LegalInput_true(String romanNumber, int arabicNumber) {
        assertEquals(romanNumber, NumConverter.convert(arabicNumber)); // Проверка перевода из арабского СИ в латинский
        assertEquals(arabicNumber, NumConverter.convert(romanNumber)); // Проверка перевода из латинского СИ в арабский
    }

    static Stream<Arguments> negativeRomanTestsStringsProvider() {
        return Stream.of(
                arguments("", "String is empty"),
                arguments("MMMDDDCCCLLLXXX", "Invalid string length"),
                arguments("ABEFGH", "Invalid input"),
                arguments("MMMM", "Number is out of range")
        );
    }

    @ParameterizedTest
    @MethodSource("negativeRomanTestsStringsProvider")
    void converter_InvalidRomanInput_ExceptionThrown(String romanInvalidNumber, String expectedBehavior) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> NumConverter.convert(romanInvalidNumber));
        assertEquals(expectedBehavior, thrown.getMessage());
    }

    static Stream<Arguments> negativeArabicTestsStringsProvider() {
        return Stream.of(
                arguments(0, "Number is out of range"),
                arguments(4000, "Number is out of range")
        );
    }

    @ParameterizedTest
    @MethodSource("negativeArabicTestsStringsProvider")
    void converter_InvalidArabicInput_ExceptionThrown(int arabicInvalidNumber, String expectedBehavior) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> NumConverter.convert(arabicInvalidNumber));
        assertEquals(expectedBehavior, thrown.getMessage());
    }
}

