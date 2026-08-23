public abstract class Vehicle {
    protected String brand;
    protected String model;
    protected int year;


    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }


    // Concrete method for basic info
    public void displayInfo() {
        System.out.printf("%d %s %s%n", year, brand, model);
    }


    // Abstract method for maintenance cost calculation
    public abstract double calculateMaintenanceCost();
}
