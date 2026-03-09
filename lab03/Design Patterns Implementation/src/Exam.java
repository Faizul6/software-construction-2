public class Exam extends Course {
    public Exam(String name, String code) {
        super(name, code);
    }


    @Override
    public void displayDetails() {
        System.out.printf("Course: %s (%s) - Type: Seminar/Project%n", name, code);
    }


    @Override
    public double calculateECTS() {
        return 3.0; // Seminars often yield 3 ECTS
    }
}
