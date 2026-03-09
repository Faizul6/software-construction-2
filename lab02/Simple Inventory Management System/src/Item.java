public class Item {


    /** * UML Class Diagram for Item:
     * * =============================================
     * | Item |
     * ===============================================
     * | - name: String (private) |
     * | - itemID: int (private) |
     * | - quantity: int (private) |
     * | - price: double (private) |
     * | - totalItems: int (private, static) |
     * ==============================================
     * | + Item(name: String, itemID: int, quantity: int, price: double) |
     * | + getName(): String |
     * | + getItemID(): int |
     * | + getQuantity(): int |
     * | + getPrice(): double |
     * | + getTotalItemsCount(): int | (static)
     * | + setQuantity(quantity: int): void |
     * | + setPrice(price: double): void |
     * | + getTotalValue(): double |
     * | + isLowStock(): boolean |
     * =============================================
     */
    // Private attributes for encapsulation
    private String name;
    private int itemID;
    private int quantity;
    private double price;


    // Static variable to track total number of items created
    private static int totalItems = 0;


    // Constructor
    public Item(String name, int itemID, int quantity, double price) {
        this.name = name;
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        totalItems++; // Increment static counter
    }


    // --- Getters ---
    public String getName() { return name; }
    public int getItemID() { return itemID; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public static int getTotalItemsCount() { return totalItems; } // Static getter


    // --- Setters (with basic validation) ---
    public void setQuantity(int quantity) {
        // Input validation for quantity
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Error: Quantity cannot be negative.");
        }
    }
    public void setPrice(double price) {
        // Input validation for price
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Error: Price cannot be negative.");
        }
    }
    // Setters for name and ID are generally not needed after construction,
    // but can be added if required.


    // Calculate total value of this specific item
    public double getTotalValue() {
        return quantity * price;
    }


    // Low stock warning check
    public boolean isLowStock() {
        return quantity < 5; // Low stock is < 5
    }


    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Qty: %d, Price: $%.2f, Value: $%.2f%s",
                itemID, name, quantity, price, getTotalValue(), isLowStock() ? " (LOW STOCK!)" : "");
    }
}
