//config manager
public class ConfigurationManager {
    // volatile ensures changes are visible across threads
    private static volatile ConfigurationManager instance;


    private int maxStudents = 500;
    private final String universityName = "HAW Hamburg";


    // Private constructor prevents external instantiation
    private ConfigurationManager() {}


    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }


    // Getters and setters for configuration data
    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents (int max) { this.maxStudents = max; }
    public String getUniversityName() { return universityName; }
}
