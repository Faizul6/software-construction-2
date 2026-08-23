public class InputValidator {


    // Validate name formats
    public static void validateName (String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        // Allows letters, spaces, hyphens, and German umlauts
        if (!name.matches("[a-zA-ZäöüÄÖÜß\\s-]+")) {
            throw new IllegalArgumentException("Name contains invalid characters: " + name);
        }
    }


    // Validate matriculation number range
    public static void validateMatriculationNumber(int number)
            throws InvalidMatriculationNumberException {
        if (number < 1001 || number > 999999) {
            throw new InvalidMatriculationNumberException(
                    "Matriculation number must be between 1001 and 999999. Found: " + number);
        }
    }


    // Example: Validate date
    public static void validateDate(int day, int month, int year) throws IllegalArgumentException {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
        if (year < 1900 || year > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
    }
}
