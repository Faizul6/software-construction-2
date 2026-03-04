public class Person {
    // Instance variables
    String firstName;
    String lastName;
    int nameChangeDay;
    int nameChangeMonth;
    int nameChangeYear;

    // Constructor to initialize person details
    Person(String fName, String lName, int d, int m, int y) {
        firstName = fName;
        lastName = lName;
        nameChangeDay = d;
        nameChangeMonth = m;
        nameChangeYear = y;
    }

    // Method to show person details
    void showDetails() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Date of Name Change: " + nameChangeDay + "." + nameChangeMonth + "." + nameChangeYear);
    }

    // Method to change the name (after checking)
    void changeName(String newFirst, String newLast, int newDay, int newMonth, int newYear) {
        // Check if at least 3 years have passed since the last name change
        if (newYear - nameChangeYear >= 3) {
            // Update name and date
            firstName = newFirst;
            lastName = newLast;
            nameChangeDay = newDay;
            nameChangeMonth = newMonth;
            nameChangeYear = newYear;
            System.out.println("Name change successful!");
        } else {
            // Show error message if 3 years not completed
            System.out.println("Error: You can only change your name after 3 years.");
        }
    }
}