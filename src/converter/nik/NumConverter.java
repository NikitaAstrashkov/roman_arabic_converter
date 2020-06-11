package converter.nik;

public class NumConverter {
    /*
     * Конвертация из арабской системы исчисления в латинскую.
     * */
    public static String convert(int arabic) throws IllegalArgumentException {
        if (arabic > 3999 || arabic < 1) {
            throw new IllegalArgumentException("Number is out of range");
        }
        StringBuilder roman = new StringBuilder();
        while (arabic >= 1000) {
            roman.append("M");
            arabic -= 1000;        }
        while (arabic >= 900) {
            roman.append("CM");
            arabic -= 900;
        }
        while (arabic >= 500) {
            roman.append("D");
            arabic -= 500;
        }
        while (arabic >= 400) {
            roman.append("CD");
            arabic -= 400;
        }
        while (arabic >= 100) {
            roman.append("C");
            arabic -= 100;
        }
        while (arabic >= 90) {
            roman.append("XC");
            arabic -= 90;
        }
        while (arabic >= 50) {
            roman.append("L");
            arabic -= 50;
        }
        while (arabic >= 40) {
            roman.append("XL");
            arabic -= 40;
        }
        while (arabic >= 10) {
            roman.append("X");
            arabic -= 10;
        }
        while (arabic >= 9) {
            roman.append("IX");
            arabic -= 9;
        }
        while (arabic >= 5) {
            roman.append("V");
            arabic -= 5;
        }
        while (arabic >= 4) {
            roman.append("IV");
            arabic -= 4;
        }
        while (arabic >= 1) {
            roman.append("I");
            arabic -= 1;
        }
        return roman.toString();
    }

    /*
    * Конвертация из латинской системы исчисления в арабскую.
    * */
    public static int convert(String roman) throws IllegalArgumentException {
        int i = 0;
        int arabic = 0;
        char[] romanBff = (roman + "Q").toCharArray(); // Q - символ в конце массива, нужен чтобы не вывалились за пределы массива при проверках типа charBff[i+1]

        if (romanBff.length > 13) // MMMCMLXXXVIII - 13 латинских чисел, максимальное количество латинских цифросимволов в заданном диапазоне, косвенная проверка на выход за пределы.
            throw new IllegalArgumentException("Invalid string length");
        if (roman.isEmpty()) // проверяем не пуста ли предоставленная строка
            throw new IllegalArgumentException("String is empty");

        while (i < romanBff.length) {
            switch (romanBff[i]) {
                case 'M':
                    arabic += 1000;
                    break;
                case 'D':
                    arabic += 500;
                    break;
                case 'C':
                    if (romanBff[i+1] == 'M') {
                        arabic += 900;
                        i++;
                    }
                    else if (romanBff[i+1] == 'D') {
                        arabic += 400;
                        i++;
                    }
                    else arabic += 100;
                    break;
                case 'L':
                    arabic += 50;
                    break;
                case 'X':
                    if (romanBff[i+1] == 'C') {
                        arabic += 90;
                        i++;
                    }
                    else if (romanBff[i+1] == 'L') {
                        arabic += 40;
                        i++;
                    }
                    else arabic += 10;
                    break;
                case 'V':
                    arabic += 5;
                    break;
                case 'I':
                    if (romanBff[i+1] == 'X') {
                        arabic += 9;
                        i++;
                    }
                    else if (romanBff[i+1] == 'V') {
                        arabic += 4;
                        i++;
                    }
                    else arabic += 1;
                    break;
                case 'Q':
                    break;
                default:
                    throw new IllegalArgumentException("Invalid input");
            }
            i++;
        }
        if (arabic > 3999) throw new IllegalArgumentException("Number is out of range");
        return arabic;
    }
}
