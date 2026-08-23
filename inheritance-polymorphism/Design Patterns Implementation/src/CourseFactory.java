public class CourseFactory {
    public static Course createCourse(String type, String name, String code) {


        switch (type.toLowerCase()) {
            case "lecture":
                return new Lecture(name, code);
            case "Exam":
                return new Exam(name, code);
            default:
                // Error handling as required by good factory design
                throw new IllegalArgumentException("Unknown course type requested: " + type);
        }
    }
}
