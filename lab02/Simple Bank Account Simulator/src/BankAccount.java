import java.util.Scanner;

/**
 * UML Class Diagram:
 * * =============================================================
 * | BankAccount |
 * ===============================================================
 * | - balance: double (private) |
 * | - accountHolder: String (private) |
 * ===============================================================
 * | + BankAccount(name: String, initialBalance: double) | (Constructor)
 * | + getBalance(): double |
 * | + getAccountHolder(): String |
 * | + displayAccountInfo(): void |
 * | + deposit(amount: double): boolean | (Includes validation) 
 * | + withdraw(amount: double): boolean | (Includes validation)
 * | + main(args: String[]): void | (static) 
 * ============================================================== 
 */


public class BankAccount {
    // Private variables
    private double balance;
    private String accountHolder;


    // Constructor [cite: 182, 227]
    public BankAccount(String name, double initialBalance) {
        this.accountHolder = name;
        // Simple check for initial balance, though task focuses on deposit/withdrawal validation
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
            System.out.println("Warning: Initial balance cannot be negative. Set to 0.0.");
        }
    }


    // Method to check balance (Getter)
    public double getBalance() {
        return balance;
    }


    // Getter for account holder
    public String getAccountHolder() {
        return accountHolder;
    }


    // Method to display account info
    public void displayAccountInfo() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.printf("Current Balance: $%.2f\n", balance);
    }


    // Method for deposit with validation
    public boolean deposit(double amount) {
        // Validation: no negative deposits
        if (amount > 0) {
            this.balance += amount; // Update balance
            System.out.printf("Deposit successful. $%.2f added.\n", amount); // Success message
            return true;
        } else {
            System.out.println("Transaction failed: Deposit amount must be positive."); // Error message
            return false;
        }
    }


    // Method for withdrawal with validation
    public boolean withdraw(double amount) {
        // Validation: amount must be positive AND sufficient funds [cite: 193, 206, 253]
        if (amount > 0 && amount <= balance) {
            this.balance -= amount; // Update balance
            System.out.printf("Withdrawal successful. $%.2f withdrawn.\n", amount); // Success message
            return true;
        } else {
            System.out.println("Transaction failed:"); // Error message
            if (amount <= 0) {
                System.out.println("  Withdrawal amount must be positive.");
            } else {
                System.out.printf("  Insufficient funds. Current balance: $%.2f. Requested: $%.2f\n", balance, amount);
            }
            return false;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // 1. Create a BankAccount object
        BankAccount myAccount = new BankAccount("Learning Java Student", 500.00);


        int choice = -1;


        // Menu loop
        while (choice != 0) {
            // 2. Display a menu with options
            System.out.println("\n--- Bank Account Menu ---");
            System.out.println("1: Check balance");
            System.out.println("2: Make deposit");
            System.out.println("3: Make withdrawal");
            System.out.println("4: Display account info");
            System.out.println("0: Exit");
            System.out.println("-------------------------");
            System.out.print("Enter your choice: ");


            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline


                // 3. Handle user input and call appropriate methods
                switch (choice) {
                    case 1:
                        System.out.printf("Your current balance is: $%.2f\n", myAccount.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        if (scanner.hasNextDouble()) {
                            double depositAmount = scanner.nextDouble();
                            myAccount.deposit(depositAmount); // Deposit and validation happen here
                        } else {
                            System.out.println("Invalid input. Please enter a valid amount.");
                            scanner.next(); // Consume invalid input
                        }
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        if (scanner.hasNextDouble()) {
                            double withdrawalAmount = scanner.nextDouble();
                            myAccount.withdraw(withdrawalAmount); // Withdrawal and validation happen here
                        } else {
                            System.out.println("Invalid input. Please enter a valid amount.");
                            scanner.next(); // Consume invalid input
                        }
                        break;
                    case 4:
                        myAccount.displayAccountInfo();
                        break;
                    case 0:
                        System.out.println("Thank you for using the Simple Bank Account Simulator. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number from the menu.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close();
    }
}
