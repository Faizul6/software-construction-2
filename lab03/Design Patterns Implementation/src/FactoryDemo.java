public class FactoryDemo {
    public static void demonstrate() {
        System.out.println("\n--- Course Factory Demonstration ---");


        // Factory creates the specific Lecture instance
        Course mathLecture = CourseFactory.createCourse("LECTURE", "Calculus 2", "MA2501");


        // Factory creates the specific Exam instance
        Course javaExam = CourseFactory.createCourse("Exam", "Advanced Java Programming", "SO502");


        // Use polymorphism on the Course base type
        mathLecture.displayDetails();
        System.out.println("ECTS: " + mathLecture.calculateECTS());


        javaExam    .displayDetails();
        System.out.println("ECTS: " + javaExam  .calculateECTS());


        // Demonstrate error handling
        try {
            CourseFactory.createCourse("WORKSHOP", "Soft Skills", "XX101");
        } catch (IllegalArgumentException e) {
            System.err.println("Factory Error Handled: " + e.getMessage());
        }
    }
}
