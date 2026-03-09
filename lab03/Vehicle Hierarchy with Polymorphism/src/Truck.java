public class Truck extends Vehicle {
    private double cargoCapacityTons;


    public Truck(String brand, String model, int year, double cargoCapacityTons) {
        super(brand, model, year);
        this.cargoCapacityTons = cargoCapacityTons;
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("    Type: Truck, Capacity: %.1f tons%n", cargoCapacityTons);
    }


    @Override
    public double calculateMaintenanceCost() {
        // Formula: High capacity increases cost substantially
        return 2000.0 + (cargoCapacityTons * 300.0);
    }
}
