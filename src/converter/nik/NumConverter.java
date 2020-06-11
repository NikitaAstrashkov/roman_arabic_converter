package converter.nik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumConverter {
    static String[] romanNumbers = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; // Цифры в числе идут только в таком порядке
    static int[] arabicNumbers =  {1000,  900, 500,  400, 100,  90 ,  50,  40 ,  10,   9 ,  5 ,   4 ,  1 }; // Числа, соответствующие цифрам латинской СИ

    static String[] romanNumbers1 = {"CM", "CD", "XC", "XL", "IX", "IV",  "M", "D", "C", "L", "X", "V", "I"}; // Цифры в числе идут только в таком порядке
    static int[] arabicNumbers1 =    {900,  400,  90 ,  40 ,   9 ,   4 , 1000, 500, 100,  50,  10,  5 ,  1 }; // Числа, соответствующие цифрам латинской СИ

    static int arabicNum;
    /*
     * Конвертация из арабской системы исчисления в латинскую.
     * */
    public static String convert(int arabic) throws IllegalArgumentException {
        StringBuilder romanNum = new StringBuilder();
        arabicNum = arabic;
        if (arabic > 3999 || arabic < 1) {
            throw new IllegalArgumentException("Number is out of range");
        }
        for (int j = 0; j < romanNumbers.length; j++) {
            romanNum.append(convertParts(arabicNumbers[j], romanNumbers[j]));
        }
        return romanNum.toString();
    }


    private static String convertParts(int arabicDecrease, String appendix) {
        StringBuilder currentRomanNum = new StringBuilder();
        while (arabicNum >= arabicDecrease) {
            currentRomanNum.append(appendix);
            arabicNum -= arabicDecrease;
        }
        return currentRomanNum.toString();
    }

    /*
     * Конвертация из латинской системы исчисления в арабскую.
     * */
    public static int convert(String roman) throws IllegalArgumentException {
        int i = 0;
        int arabic = 0;
        StringBuilder currentRoman = new StringBuilder(roman);
        if (roman.length() > 13) // MMMCMLXXXVIII - 13 латинских чисел, максимальное количество латинских цифросимволов в заданном диапазоне, косвенная проверка на выход за пределы.
            throw new IllegalArgumentException("Invalid string length");
        if (roman.isEmpty()) // проверяем не пуста ли предоставленная строка
            throw new IllegalArgumentException("String is empty");
        //Pattern romanNumberValidation = Pattern.compile("M*D*C*L*X*V*I*", Pattern.MULTILINE);
        //Matcher m = romanNumberValidation.matcher(roman);
        if(!(Pattern.matches("[MDCLXVI]*", roman)))
        {
            throw new IllegalArgumentException("Invalid input");
        }
        while (true) {
            if (currentRoman.toString().isEmpty()) {
                break;
            }
            int index = currentRoman.indexOf(romanNumbers1[i]);
            if (index == -1) {
                i++;
            }
            else {
                arabic += arabicNumbers1[i];
                if (i < 6)
                    currentRoman.delete(index, index+2); // Удаляем два элемента
                else
                    currentRoman.deleteCharAt(index); // Удаляем первый элемент
            }
        }
        if (arabic > 3999)
            throw new IllegalArgumentException("Number is out of range");
        return arabic;
    }

    private static boolean dividesByTwo(int a){
        return (a%2==0);
    }
}
