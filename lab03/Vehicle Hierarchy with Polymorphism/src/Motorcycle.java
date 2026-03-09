public class Motorcycle extends Vehicle {
    private int engineCC; // Engine Displacement in cubic centimeters


    public Motorcycle(String brand, String model, int year, int engineCC) {
        super(brand, model, year);
        this.engineCC = engineCC;
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("    Type: Motorcycle, Engine CC: " + engineCC);
    }


    @Override
    public double calculateMaintenanceCost() {
        // Formula: Higher CC increases cost
        return 500.0 + (engineCC * 0.5);
    }
}
