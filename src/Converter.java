/*
 * @author Dmitry Komarov
 * Created 06.03.2024
 */


public class Converter {
    public static final String[] ROMAN_NUMERALS = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static final int[] VALUES = {100, 90, 50, 40, 10, 9, 5, 4, 1};


    public static String arabicToRoman(int value){
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (value > 0){
            if (value - VALUES[i] >= 0){
                result.append(ROMAN_NUMERALS[i]);
                value -= VALUES[i];
            } else {
                i++;
            }
        }
        return result.toString();
    }
}

