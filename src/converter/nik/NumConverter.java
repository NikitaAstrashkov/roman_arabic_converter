package converter.nik;

import java.util.LinkedList;
import java.util.regex.Pattern;

public class NumConverter {
    static final String[] romanNumbers = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; // digits in roman number are going in this sequence
    static final int[] arabicNumbers =  {1000,  900, 500,  400, 100,  90 ,  50,  40 ,  10,   9 ,  5 ,   4 ,  1 }; // numbers representing roman digits equivalent in arabic

    static int arabicNum;
    /**
     * Function: convert
     * converting arabic num to roman num
     * @param arabic - arabic num to convert
     * @throws IllegalArgumentException when num is out of range
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

    /**
     * Function: convert (override)
     * converting roman num to arabic num
     * @param roman - roman num to convert
     * @throws IllegalArgumentException when num is out of range, or input string doesn't meets requirements to be classified as roman num
     * */
    public static int convert(String roman) throws IllegalArgumentException {
        if (roman.length() > 13) // MMMCMLXXXVIII - 13 symbols that represent roman num and is highest symbols array in range 1..3999, checking if input string is more than that
            throw new IllegalArgumentException("Invalid string length");

        if (roman.isEmpty()) // checking if string is empty
            throw new IllegalArgumentException("String is empty");

        if(!(Pattern.matches("[MDCLXVI]*", roman))) // checking if str contains of roman digits
            throw new IllegalArgumentException("Invalid input");

        LinkedList <String> romanDigits = splitRomanNumToDigits(roman); // splitting string on digits and checking if it meets requirements to order of digits and their reiteration

        short i = 0;
        int arabic = 0;
        while (!(romanDigits.isEmpty())) {
            if (romanNumbers[i].compareTo(romanDigits.peekFirst()) == 0) {
                arabic += arabicNumbers[i];
                romanDigits.removeFirst();
            }
            else
                i++;
        }
        if (arabic > 3999)
            throw new IllegalArgumentException("Number is out of range");
        return arabic;
    }

    private static LinkedList <String> splitRomanNumToDigits(String roman) throws IllegalArgumentException{
        LinkedList <String> romanDigits = new LinkedList<>();
        char[] romanArr = roman.toCharArray();
        char repeats = 0; // sign of repeated digits, less than 3 for all single char digits and less then 1 for double char digits
        char i = 0; // charArray iterator
        char j = 0; // romanNumbers iterator
        boolean singleDigit = false;
        for (; i < romanArr.length; i++) {
            if (j >= romanNumbers.length) // checked for compliance all roman digits but still have more on input, order of digits were broken
                throw new IllegalArgumentException("Illegal number");
            if (romanArr.length == i+1) {
                singleDigit = true;
            }
            if (romanNumbers[j].compareTo(Character.toString(romanArr[i])) == 0) {
                romanDigits.add(Character.toString(romanArr[i]));
                repeats++;
            } else if (!singleDigit && (romanNumbers[j].compareTo(Character.toString(romanArr[i]) + romanArr[i + 1]) == 0)) {
                romanDigits.add(Character.toString(romanArr[i]) + romanArr[i + 1]);
                i++;
                repeats++;
            } else {
                repeats = 0;
                j++;
                i--;
                continue;
            }
            if (dividesByTwo(j)) { // if romanNumbers iterator is even - we're on single char digit, else we're on double char digit
                if (repeats > 3) // 3 reiterations allowed for single char digit
                    throw new IllegalArgumentException("Illegal number");
            }
            else if (repeats > 1) // 1 reiteration allowed for double char digit
                throw new IllegalArgumentException("Illegal number");
        }
        return romanDigits;
    }

    private static boolean dividesByTwo(int a){
        return (a%2==0);
    }
}
