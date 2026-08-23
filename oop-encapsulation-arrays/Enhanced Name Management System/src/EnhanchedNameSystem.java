import java.util.Scanner;


public class EnhanchedNameSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Moin Moin! (Good day!)");


        // Replace the three separate person variables with an array of Person objects
        Person[] persons = new Person[3];


        // Create Person objects and store them in the array
        persons[0] = new Person("Faizul", "Robin", 2, 10, 2022);
        persons[1] = new Person("Julian", "Apel", 12, 12, 2022);
        persons[2] = new Person("Bull", "Eye", 14, 1, 2018);


        int choice = -1;


        // Menu loop
        while (choice != 0) {
            System.out.println("\n===============================");
            System.out.println("0: Exit");
            // Menu options now refer to array indices (1-based for user)
            System.out.println("1: Change details of Person 1 (Index 0)");
            System.out.println("2: Change details of Person 2 (Index 1)");
            System.out.println("3: Change details of Person 3 (Index 2)");
            System.out.println("4: --Show all persons details--");
            System.out.println("5: Display total person count"); // New option
            System.out.println("===============================");
            System.out.print("Enter your choice: ");


            // Basic input handling for the menu choice
            if (input.hasNextInt()) {
                choice = input.nextInt();
                input.nextLine(); // Consume leftover newline
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // Clear invalid input
                choice = -1;
                continue;
            }


            if (choice == 0) {
                // Exit option
                System.out.println("Program Terminated!!");
            }
            else if (choice == 4) {
                System.out.println("--- All Person Details ---");
                // Loop through the array to show details for all persons
                for (Person p : persons) {
                    if (p != null) {
                        p.showDetails();
                        System.out.println("---------------------------");
                    }
                }
            }
            else if (choice == 5) {
                // Display the total person count using the static method
                System.out.println("Total number of Person objects: " + Person.getPersonCount());
            }
            else if (choice >= 1 && choice <= persons.length) {
                int index = choice - 1;
                Person selected = persons[index];


                System.out.print("Enter new first name: ");
                String newFirst = input.nextLine();
                System.out.print("Enter new last name: ");
                String newLast = input.nextLine();
                System.out.print("Enter the new date (day month year): ");


                int newDay = -1, newMonth = -1, newYear = -1;
                if (input.hasNextInt()) newDay = input.nextInt();
                if (input.hasNextInt()) newMonth = input.nextInt();
                if (input.hasNextInt()) newYear = input.nextInt();
                input.nextLine(); // Consume leftover newline


                // Update details using setters, which now includes month validation
                selected.setFirstName(newFirst);
                selected.setLastName(newLast);
                selected.setDay(newDay);
                selected.setMonth(newMonth); // Validation happens here
                selected.setYear(newYear);


                // Show updated details
                System.out.println("--- Details after update ---");
                selected.showDetails();
            } else {
                System.out.println("Invalid choice. Please choose from the menu options.");
            }
        }
    }
}
