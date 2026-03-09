public class VehicleManager {
    public static void demonstrate() {
        System.out.println("\n--- Task 2: Vehicle Hierarchy and Polymorphism ---");


        // Creates an array of Vehicle objects containing different types
        Vehicle[] vehicles = {
                new Car("Honda", "CRV", 2024, 4),
                new Motorcycle("Harley-Davidson", "VRSCAW", 2010, 1250),
                new Truck("Ford", "F-150", 2019, 1500.0),
                new Car("BMW", "X5", 2017, 5)
        };


        // Use polymorphism to call methods on all vehicles in the array
        for (Vehicle v : vehicles) {
            v.displayInfo(); // Calls overridden method in subclass
            System.out.printf("    Estimated Maintenance Cost: $%.2f%n%n",
                    v.calculateMaintenanceCost()); // Calls specific implementation
        }
    }


    public static void main(String[] args) {
        demonstrate();
    }
}
