import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StudentDataPersistenceService {


    // Implement Logging
    private static final Logger LOGGER = Logger.getLogger(StudentDataPersistenceService.class.getName());


    private final String DATA_FILE = "students.dat";
    private final String BACKUP_FILE = "students_backup.dat";






    public void saveToCSV(List<Student> students, String filename) throws DataPersistenceException {


        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {


            // Write the header row
            writer.println("Name,ID,Age");


            // Write data for each student
            for (Student student : students) {
                // Format the output: Name,ID,Age
                writer.printf("%s,%d,%d%n",
                        student.getName(),
                        student.getMatriculationNumber(),
                        student.getAge());
            }


            LOGGER.log(Level.INFO, "Data successfully saved to CSV: {0}", filename);


        } catch (IOException e) {
            // Catch IOException and wrap it in DataPersistenceException
            LOGGER.log(Level.SEVERE, "Failed to save CSV data", e);
            throw new DataPersistenceException("Failed to save student data to " + filename, e);
        }
    }
    public List<Student> loadFromCSV(String filename) throws DataPersistenceException {
        List<Student> students = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {


            String line = reader.readLine(); // Skip header


            while ((line = reader.readLine()) != null) {
                try {
                    // Assuming you have a helper method to parse the line
                    Student student = parseStudentLine(line);
                    students.add(student);
                } catch (Exception e) {
                    // Handle malformed input gracefully
                    LOGGER.log(Level.WARNING, "Skipping malformed line: {0}", line);
                    // Continue reading the next line instead of crashing
                }
            }


            LOGGER.log(Level.INFO, "Data successfully loaded from CSV: {0}", filename);
            return students;


        } catch (IOException e) {
            // Wrap and re-throw checked exception
            LOGGER.log(Level.SEVERE, "Failed to load CSV data", e);
            throw new DataPersistenceException("Failed to load student data from " + filename, e);
        }
    }


    private Student parseStudentLine(String line) {
        return null;
    }




    // Load student data & Demonstrate error recovery
    public List<Student> load() throws DataPersistenceException {
        LOGGER.log(Level.INFO, "Attempting to load student data from {0}", DATA_FILE);


        // Try primary file first
        List<Student> students = tryLoadFile(DATA_FILE);


        // Error Recovery Mechanism Load with error recovery)
        if (students.isEmpty()) {
            LOGGER.log(Level.WARNING, "Primary file failed. Attempting to load from backup: {0}", BACKUP_FILE);
            students = tryLoadFile(BACKUP_FILE);


            if (students.isEmpty()) {
                LOGGER.log(Level.WARNING, "Both primary and backup files failed. Initializing empty registry.");
                return new ArrayList<>(); // Return empty list
            } else {
                LOGGER.log(Level.INFO, "Successfully loaded data from backup file.");
            }
        }
        return students;
    }


    // Helper method for loading and wrapping exceptions
    private List<Student> tryLoadFile(String filename) throws DataPersistenceException {
        try {
            // Use try-with-resources
            try (FileInputStream fis = new FileInputStream(filename);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {


                // Unchecked cast warning is expected here for Object serialization
                List<Student> students = (List<Student>) ois.readObject();
                return students;
            }
        }
        //  Catch multiple exceptions and wrap
        catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException e) {
            // Log error
            LOGGER.log(Level.SEVERE, "Critical file read error on " + filename, e);
            //  Wrap and re-throw the error
            throw new DataPersistenceException("Failed to read data from file: " + filename, e);
        }
    }


    // Backup and restore functionality
    private void backupData(String source, String destination) throws IOException {
        File sourceFile = new File(source);
        if (sourceFile.exists()) {
            try (InputStream in = new FileInputStream(source);
                 OutputStream out = new FileOutputStream(destination)) {


                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                LOGGER.log(Level.INFO, "Backup created successfully from {0}", source);
            }
        }
    }


    // Example validation method throwing an UNCHECKED exception
    public static void validateMatriculation(String matriculationNumberStr, List<Student> existingStudents) throws InvalidMatriculationNumberException {
        int matriculationNumberInt;


        try {
            matriculationNumberInt = Integer.parseInt(matriculationNumberStr);
        } catch (NumberFormatException e) {
            // Handle case where input is not a number (e.g., "ABCD")
            throw new InvalidMatriculationNumberException(
                    "Matriculation number must be a valid number.", e);
        }


        // Validate for conflict
        if (existingStudents.stream()
                .anyMatch(s -> s.getMatriculationNumber() == matriculationNumberInt)) {
            throw new InvalidMatriculationNumberException(
                    "Matriculation number " + matriculationNumberStr + " is already in use."
            );
        }
    }
}
