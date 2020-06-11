package converter.nik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumConverterTest {
    String romanInvalidException1       = "";                // негативный кейс, пустая строка, вернёт ошибку "String is empty"
    String romanInvalidException2       = "MMMDDDCCCLLLXXX"; // негативный кейс, 15 символов выходят за косвенную проверку, вернёт ошибку "Invalid string length"
    String romanInvalidException3       = "ABEFGH";          // негативный кейс, таких символов нет в латинском исчислении, вернёт ошибку "Invalid input"
    String romanOutOfRangeException     = "MMMM";            // негативный кейс, выход за максимальный предел, вернёт ошибку "Number is out of range"
    String romanPassedUpperLimit        = "MMMCMXCIX";       // позитивный кейс, полученное число будет максимальным пределом
    String romanPassedLowerLimit        = "I";               // позитивный кейс, полученное число будет минимальным пределом
    String romanPassedMiddleOfRange     = "MCMXCIX";         // позитивный кейс, полученное число будет серединой диапазона

    int    arabicOutOfRange1            = 0;                 // негативный кейс, выход за мин. предел, вернёт ошибку "Number is out of range"
    int    arabicOutOfRange2            = 4000;              // негативный кейс, выход за макс. предел, вернёт ошибку "Number is out of range"
    int    arabicPassedUpperLimit       = 3999;              // позитивный кейс, полученное число будет максимальным пределом
    int    arabicPassedLowerLimit       = 1;                 // позитивный кейс, полученное число будет минимальным пределом
    int    arabicPassedMiddleOfRange    = 1999;              //    позитивный кейс, полученное число будет серединой диапазона

    /*
     * Тест позитивных кейсов конвертаций
     * Проверены по три значения: верхняя граница, нижняя граница, середина диапазона
     * Тесты должны быть пройдены успешно
     */
    @Test
    void positiveTestsConvert() {
        assertEquals(romanPassedUpperLimit, NumConverter.convert(arabicPassedUpperLimit));
        assertEquals(romanPassedLowerLimit, NumConverter.convert(arabicPassedLowerLimit));
        assertEquals(romanPassedMiddleOfRange, NumConverter.convert(arabicPassedMiddleOfRange));

        assertEquals(arabicPassedUpperLimit, NumConverter.convert(romanPassedUpperLimit));
        assertEquals(arabicPassedLowerLimit, NumConverter.convert(romanPassedLowerLimit));
        assertEquals(arabicPassedMiddleOfRange, NumConverter.convert(romanPassedMiddleOfRange));
    }

    /*
     * Тест перевода из латинской в арабскую СИ
     * Не пройдёт проверку т.к. на вводе пустая строка
     * Вернёт ошибку "String is empty"
     */
    @Test
    void romanToArabicNegativeTestConvert1() { //
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> NumConverter.convert(romanInvalidException1));
        assertEquals("String is empty", thrown.getMessage());
    }

    /*
     * Тест перевода из латинской в арабскую СИ
     * Не пройдёт проверку т.к. на вводе слишком длинная строка (больше 13 символов)
     * Вернёт ошибку "Invalid string length"
     */
    @Test
    void romanToArabicNegativeTestConvert2() { //
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> NumConverter.convert(romanInvalidException2));
        assertEquals("Invalid string length", thrown.getMessage());
    }

    /*
     * Тест перевода из латинской в арабскую СИ
     * Не пройдёт проверку т.к. таких символов нет в латинском исчислении (больше 13 символов)
     * Вернёт ошибку "Invalid input"
     */
    @Test
    void romanToArabicNegativeTestConvert3() { //
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> NumConverter.convert(romanInvalidException3));
        assertEquals("Invalid input", thrown.getMessage());
    }

    /*
     * Тест перевода из латинской в арабскую СИ
     * Не пройдёт проверку т.к. символьное представление выходит за верхний предел
     * Вернёт ошибку "Number is out of range"
     */
    @Test
    void romanToArabicNegativeTestConvert4() { //
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> NumConverter.convert(romanOutOfRangeException));
        assertEquals("Number is out of range", thrown.getMessage());
    }

    /*
     * Тест перевода из арабской в латинскую СИ
     * Не пройдёт проверку т.к. число выходит за нижний предел
     * Вернёт ошибку "Number is out of range"
     */
    @Test
    void arabicToRomanNegativeTestConvert1() { //
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> NumConverter.convert(arabicOutOfRange1));
        assertEquals("Number is out of range", thrown.getMessage());
    }

    /*
     * Тест перевода из арабской в латинскую СИ
     * Не пройдёт проверку т.к. число выходит за верхний предел
     * Вернёт ошибку "Number is out of range"
     */
    @Test
    void arabicToRomanNegativeTestConvert2() { //
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> NumConverter.convert(arabicOutOfRange2));
        assertEquals("Number is out of range", thrown.getMessage());
    }
}