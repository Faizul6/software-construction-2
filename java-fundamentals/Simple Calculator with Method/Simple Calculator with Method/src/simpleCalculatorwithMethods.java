import java.util.Scanner;

class simpleCalculatorwithMethods {
    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner input = new Scanner(System.in);

        // Declare variables for user choice and numbers
        int option;
        double num1, num2, result = 0.0;

        // Initialize option to -1 to start the loop
        option = -1;

        // Main program loop - continues until user chooses to exit (option 5)
        while (option != 5) {
            // Display calculator menu
            System.out.println("== Simple Calculator ==");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Choose an operation (1-5): ");

            // Get user's menu choice
            option = input.nextInt();
            input.nextLine(); // Consume leftover newline character

            // Check if user wants to perform a calculation (not exit)
            if (option != 5) {
                // Get two numbers from user
                System.out.print("Enter first number: ");
                num1 = input.nextDouble();
                System.out.print("Enter second number: ");
                num2 = input.nextDouble();

                // Perform operation based on user's choice
                switch (option) {
                    case 1:
                        // Addition operation
                        result = addition(num1, num2);
                        System.out.println("Result: " + num1 + " + " + num2 + " = " + result);
                        continue; // Continue to next iteration of loop

                    case 2:
                        // Subtraction operation
                        result = subtraction(num1, num2);
                        System.out.println("Result: " + num1 + " - " + num2 + " = " + result);
                        continue; // Continue to next iteration of loop

                    case 3:
                        // Multiplication operation
                        result = multiplication(num1, num2);
                        System.out.println("Result: " + num1 + " * " + num2 + " = " + result);
                        continue; // Continue to next iteration of loop

                    case 4:
                        // Division operation
                        result = division(num1, num2);
                        System.out.println("Result: " + num1 + " / " + num2 + " = " + result);
                        continue; // Continue to next iteration of loop
                }
            } else {
                // User chose to exit - display goodbye message
                System.out.println("Thank you for using the calculator!");
            }
        }
    }

    // Method to add two numbers
    static double addition(double a, double b) {
        return a + b;
    }

    // Method to subtract two numbers
    static double subtraction(double a, double b) {
        return a - b;
    }

    // Method to multiply two numbers
    static double multiplication(double a, double b) {
        return a * b;
    }

    // Method to divide two numbers
    static double division(double a, double b) {
        // Check for division by zero error
        if (b == 0) {
            System.out.println("Error: Division by zero!");
            return 0; // Return 0 if division by zero
        }
        return a / b; // Return result of division
    }
}





