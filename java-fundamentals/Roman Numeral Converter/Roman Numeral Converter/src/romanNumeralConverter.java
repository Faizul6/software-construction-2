import java.util.Scanner;

public class romanNumeralConverter {
    private static int convertor(char c) {
        switch(c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0; // Handles invalid characters
        }
    }
    public static void main(String[] args) {
        //Scanner for input
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Roman Numeral to Integer Converter ---");
        System.out.print("Enter a Roman Numeral (e.g., XIV): ");
        String romanNumeral = scanner.nextLine().toUpperCase(); // Convert to uppercase just in case
        scanner.close();


        int integerResult = romanToInteger(romanNumeral);

        System.out.println("\n--- Conversion Result ---");
        System.out.println("Roman Numeral: " + romanNumeral);
        System.out.println("Integer Value: " + integerResult);
        System.out.println("-------------------------");


    }
    public static int romanToInteger(String roman) {
        int result = 0;
        int n = roman.length();

        for (int i = 0; i < n - 1; i++) {
            int currentVal = convertor(roman.charAt(i));
            int nextVal = convertor(roman.charAt(i + 1));

            // This is the core logic that handles both addition and subtraction
            if (currentVal < nextVal) {
                //SUBTRACTION CASE
                result -= currentVal;
            } else {
                //ADDITION CASE
                result += currentVal;
            }
        }
        result += convertor(roman.charAt(n - 1));
        //to add the last character
        return result;


    }
}
