public class Car extends Vehicle {
    private int numberOfDoors;


    public Car(String brand, String model, int year, int numberOfDoors) {
        super(brand, model, year); // Call parent constructor
        this.numberOfDoors = numberOfDoors;
    }


    @Override
    public void displayInfo() {
        super.displayInfo(); // Reuses Vehicle's info
        System.out.println("    Type: Car, Doors: " + numberOfDoors);
    }


    @Override
    public double calculateMaintenanceCost() {
        // Formula: Older cars cost more
        return (2025 - year) * 150.0 + (numberOfDoors * 50.0);
    }
}
