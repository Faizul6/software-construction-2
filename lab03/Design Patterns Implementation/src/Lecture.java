public class Lecture extends Course {
    public Lecture(String name, String code) {
        super(name, code);
    }


    @Override
    public void displayDetails() {
        System.out.printf("Course: %s (%s) - Type: Lecture%n", name, code);
    }


    @Override
    public double calculateECTS() {
        return 6.0; // Lectures often yield 6 ECTS
    }
}
