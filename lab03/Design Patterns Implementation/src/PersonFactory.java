public abstract class PersonFactory {
    public static Person createPerson(String type, String firstName, String lastName, String department) {
        switch (type.toLowerCase()) {
            case "student":


                return new Student(0,firstName, lastName,department);
            case "professor":
                return new Professor(firstName, lastName);
            case "staff":
                return new Staff(firstName, lastName);
            default:
                throw new IllegalArgumentException("Unknown person type: " + type);
        }
    }
}
