public class Person {
    protected String firstName;
    protected String lastName;
    protected int day, month, year;
    private static int personCount = 0;


    // Constructor with name
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        personCount++;
    }


    // Constructor
    public Person(String firstName, String lastName, int day, int month, int year) {
        this(firstName, lastName);
        this.day = day;
        this.month = month;
        this.year = year;
    }


    // --- Getters ---
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getYear() { return year; }
    public int getAge() {
        // Simple age calculation for demonstration (Task 4 requirement)
        if (year == 0) return 0;
        return java.time.Year.now().getValue() - year;
    }


    // --- Display Method ---
    public void displayInfo() {
        System.out.printf("Name: %s %s, Birth Date: %d.%d.%d%n",
                firstName, lastName, day, month, year);
    }
}
