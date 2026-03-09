import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;


public class AppLogger {


    // Requirement 4: Configurable log output
    public static void setupLogger() {
        Logger rootLogger = Logger.getLogger("");
        // Remove default ConsoleHandler
        rootLogger.removeHandler(rootLogger.getHandlers()[0]);


        try {
            // 1. Console Output
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(consoleHandler);


            // 2. File Output
            FileHandler fileHandler = new FileHandler("system_events.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(fileHandler);


            // Set minimum logging level
            rootLogger.setLevel(Level.INFO);


        } catch (IOException e) {
            // Use standard output if logger setup fails
            System.err.println("Could not setup file logger: " + e.getMessage());
        }
    }
}

