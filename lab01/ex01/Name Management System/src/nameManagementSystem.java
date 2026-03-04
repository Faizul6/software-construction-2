// Main class
import java.util.Scanner;

public class nameManagementSystem {
    public static void main(String[] args) {
        // Create Scanner for user input
        Scanner input = new Scanner(System.in);
        System.out.println("Moin Moin! (Good day!)");

        // Create Person objects
        Person person1 = new Person("Faizul", "Robin", 2, 10, 2022);
        Person person2 = new Person("Julian", "Apel", 12, 12, 2022);
        Person person3 = new Person("Bull", "Eye", 14, 1, 2018);

        int choice = -1; // Menu choice variable

        // Menu loop
        while (choice != 0) {
            System.out.println("\n===============================");
            System.out.println("0: Exit");
            System.out.println("1: Change name of Person 1");
            System.out.println("2: Change name of Person 2");
            System.out.println("3: Change name of Person 3");
            System.out.println("4: --Show all persons details--");
            System.out.println("===============================");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // Consume leftover newline

            Person selected = null; // To store selected person

            if (choice == 0) {
                // Exit option
                System.out.println("Program Terminated!!");
            }
            else if (choice == 4) {
                System.out.println("--All Person--");
                person1.showDetails();
                person2.showDetails();
                person3.showDetails();
            }
            else {
                // Select the correct person based on choice
                if (choice == 1) {
                    selected = person1;
                } else if (choice == 2) {
                    selected = person2;
                } else if (choice == 3) {
                    selected = person3;
                } else {
                    System.out.println("Invalid choice");
                }

                // Ask for new name and date
                System.out.print("Enter new first name: ");
                String newFirst = input.nextLine();
                System.out.print("Enter new last name: ");
                String newLast = input.nextLine();
                System.out.print("Enter the new date (day month year): ");
                int newChangeDay = input.nextInt();
                int newChangeMonth = input.nextInt();
                int newChangeYear = input.nextInt();

                // Attempt to change name and show details
                selected.changeName(newFirst, newLast, newChangeDay, newChangeMonth, newChangeYear);
                selected.showDetails();
            }
        }
    }
}


// Person class

