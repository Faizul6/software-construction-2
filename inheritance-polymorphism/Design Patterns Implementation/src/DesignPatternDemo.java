public class DesignPatternDemo implements StudentObserver {


    private String observerName;


    public DesignPatternDemo(String observerName) {
        this.observerName = observerName;
    }


    @Override
    public void update(String eventType, Student student) {
        System.out.println(">>> " + observerName + " Notification Received:");
        System.out.println("    Event: **" + eventType + "** -> Details: " + student);
    }


    @Override
    public void onStudentModified(Student student) {
        // This is the code that "reacts" to a change in the Student object.


        // Example: Print a notification to the console
        System.out.println(">>> OBSERVER NOTIFICATION <<<");
        System.out.println("Student data has been modified:");
        System.out.println("Student Name: " + student.getFirstName() + " " + student.getLastName());
    }
    @Override
    public void onStudentRemoved(Student student) {
        // This method handles the notification when a student is removed.


        System.out.println(">>> OBSERVER NOTIFICATION <<<");
        System.out.println("Student REMOVED from registry:");
        System.out.println("Removed Student: " + student.getFirstName() + " " + student.getLastName());
    }
    @Override
    public void onStudentAdded(Student student) {
        // This method handles the notification when a student is added.


        System.out.println(">>> OBSERVER NOTIFICATION <<<");
        System.out.println("Student Added from registry:");
        System.out.println("Added Student: " + student.getFirstName() + " " + student.getLastName());
    }
    public static void main(String[] args) {
        // 1. Access the Singleton instance
        StudentRegistry registry1 = StudentRegistry.getInstance();
        StudentRegistry registry2 = StudentRegistry.getInstance();


        // Check for Singleton guarantee:
        System.out.println("Is registry1 the same instance as registry2? " + (registry1 == registry2));
        System.out.println("--------------------------------------------------");


        // 2. Create and Register Observers
        DesignPatternDemo logObserver = new DesignPatternDemo("System Log");
        DesignPatternDemo uiObserver = new DesignPatternDemo("Dashboard UI");


        registry1.registerObserver(logObserver);
        registry1.registerObserver(uiObserver);
        System.out.println("--------------------------------------------------");


        // 3. Test Student ADD (Triggers Notifications)
        Student s1 = new Student(1001, "Alice ", "Johnson", "Engineering");
        registry1.addStudent(s1);


        Student s2 = new Student(1002, "Bob ", "Smith", "Arts");
        registry1.addStudent(s2);
        System.out.println("--------------------------------------------------");


        // 4. Test Student MODIFICATION (Triggers Notifications)
        registry1.modifyStudentDepartment(1001, "Computer Science");
        System.out.println("--------------------------------------------------");


        // 5. Test Student REMOVAL (Triggers Notifications)
        registry1.removeStudent(1002);
        System.out.println("--------------------------------------------------");


        // 6. Test Removal of Observer (UI no longer listens)
        registry1.removeObserver(uiObserver);


        // 7. Test Student ADD again (Only Log Observer gets notification)
        Student s3 = new Student(1003, "Charlie ", "Brown", "Business");
        registry1.addStudent(s3);
    }
}
