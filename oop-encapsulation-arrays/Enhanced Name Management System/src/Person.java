public class Person {
        // Make instance variables private for encapsulation
        private String firstName;
        private String lastName;
        // Changing variable names for better clarity/simplicity
        private int day, month, year;


        // Static class variable to count the total number of Person objects
        private static int personCount = 0;


        // Constructor to initialize person details
        Person(String fName, String lName, int d, int m, int y) {
            this.firstName = fName;
            this.lastName = lName;
            // The dates are now set via the setter to ensure validation is used
            setDay(d);
            setMonth(m); // The month setter includes validation
            setYear(y);


            personCount++; // Increment when a new Person object is created
        }


        // --- Getter Methods ---
        public String getFirstName() {
            return firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public int getDay() {
            return day;
        }
        public int getMonth() {
            return month;
        }
        public int getYear() {
            return year;
        }


        // Static method to get the total person count
        public static int getPersonCount() {
            return personCount;
        }


        // --- Setter Methods ---
        public void setFirstName(String newFirst) {
        }
        public void setLastName(String newLast) {
            this.lastName = newLast;
        }
        public void setDay(int day) {
            // Simple day setter, assuming day input is meaningful per requirements
            this.day = day;
        }
        public void setYear(int year) {
            // Simple year setter for completeness
            this.year = year;
        }


        // Setter with validation for month (must be between 1-12)
        public void setMonth(int month) {
            if (month >= 1 && month <= 12) {
                this.month = month;
            } else {
                System.out.println("Invalid month! Must be between 1-12."); // Display error message
            }
        }


        // Method to show person details
        // The previous changeName method logic has been removed as the new task
        // focuses on encapsulation and array usage, not the 3-year name change rule.
        public void showDetails() {
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Date of Name Change: " + day + "." + month + "." + year);
        }
    }
