/*
 * @author Dmitry Komarov
 * Created 06.03.2024
 */


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final String[] ROMAN = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
    public  static boolean isRoman = false;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение");
        String[] input = sc.nextLine().split(" ");

        if (input.length != 3){
            throw new Exception("Неверное число аргументов");
        }
        checkRoman(input);
        System.out.println(calc(input));
    }

    public static String calc(String[] inputArray) throws Exception{

        int firstArg, secondArg;
        char operand;

        if(isRoman){
            firstArg = romanToArabicConverter(inputArray[0]);
            secondArg = romanToArabicConverter(inputArray[2]);
            operand = inputArray[1].charAt(0);
            int result = calculation(firstArg,secondArg, operand);
            if (result <= 0){
                throw new Exception("Результатом работы с римскими числами могут быть только положительные числа");
            }
            return Converter.arabicToRoman(result);
        } else if (isNumber(inputArray[0]) && isNumber(inputArray[2])){
            firstArg = Integer.parseInt(inputArray[0]);
            secondArg = Integer.parseInt(inputArray[2]);
            operand = inputArray[1].charAt(0);

            if (firstArg >= 0 && firstArg <= 10 && secondArg >= 0 && secondArg <= 10 ){
                return Integer.toString(calculation(firstArg, secondArg, operand));
            } else {
                throw new Exception("Допустимо использовать только числа от 1 до 10");
            }

        } else {
            throw new NumberFormatException("Необходимо использовать только арабские или только римские числа от 1 до 10");
        }

    }

    public static boolean isNumber(String str){
        try {
            int intValue = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void checkRoman(String[] input){
        if (Arrays.asList(ROMAN).contains(input[0]) && Arrays.asList(ROMAN).contains(input[2])){
            isRoman = true;
        }
    }
    
    public static int romanToArabicConverter(String str) throws Exception {
        for (int i = 0; i < ROMAN.length; i++){
            if (ROMAN[i].equals(str)){
                return ++i;
            }
        }
        throw new Exception("Нет такого арабского числа");
    }

    public static int calculation (int firstArg, int secondArg, char operand) throws ArithmeticException{
        int result;
        switch (operand) {
            case ('+') -> result = firstArg + secondArg;
            case ('-') -> result = firstArg - secondArg;
            case ('/') -> {
                if (secondArg == 0) {
                    throw new ArithmeticException("Деление на ноль недоступно");
                }
                result = firstArg / secondArg;
            }
            case ('*') -> result = firstArg * secondArg;
            default -> throw new ArithmeticException("Недопустимый операнд");
        }
        return result;
    }

}
