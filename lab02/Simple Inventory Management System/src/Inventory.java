import java.util.Arrays;
import java.util.Scanner;


/**
 * UML Class Diagram :
 * *
 ============================================================
 * | Inventory |
 * ==========================================================
 * | - items: Item[] (private) |
 * | - itemCount: int (private) |
 * | - INITIAL_CAPACITY: int (private, static final) |
 * ==========================================================
 * | + Inventory() |
 * | + addItem(item: Item): void |
 * | + updateItemQuantity(itemID: int, delta: int): boolean |
 * | + findItemByID(id: int): Item |
 * | + checkLowStock(): void |
 * | + calculateTotalInventoryValue(): double |
 * | + generateInventoryReport(): void |
 * | - resizeArray(): void (private helper) |
 * ==========================================================
 * * Relationship: Inventory <>-- 0..* Item (Aggregation)
 */

public class Inventory {
    // Private attributes
    private Item[] items; // Array to store items
    private int itemCount; // Current number of items in the array
    private static final int INITIAL_CAPACITY = 10; // Fixed initial size
    // Constructor
    public Inventory() {
        this.items = new Item[INITIAL_CAPACITY];
        this.itemCount = 0;
    }


    // Private helper method for array resizing
    private void resizeArray() {
        int newCapacity = items.length * 2;
        Item[] newItems = new Item[newCapacity];
        // Copy existing elements to the new array
        System.arraycopy(items, 0, newItems, 0, itemCount);
        this.items = newItems; // Reference the new, larger array
        System.out.println("Inventory resized to capacity " + newCapacity + ".");
    }


    // Add new items
    public void addItem(Item item) {
        // Check if resizing is necessary
        if (itemCount >= items.length) {
            resizeArray();
        }


        // Simple check to ensure ID is unique (optional but good practice)
        if (findItemByID(item.getItemID()) != null) {
            System.out.println("Error: Item with ID " + item.getItemID() + " already exists. Use update.");
            // We need to decrease the static totalItems count if the item wasn't added
            // This is a subtle point in design; ideally, item creation logic would be in Inventory.
            // For simplicity, we assume the user handles this for now.
            return;
        }


        items[itemCount++] = item; // Add the item and increment count [cite: 469]
        System.out.println(item.getName() + " added to inventory.");
    }


    // Search item by ID
    public Item findItemByID(int id) {
        for (int i = 0; i < itemCount; i++) {
            // Use the Item's getter for ID to respect encapsulation
            if (items[i].getItemID() == id) {
                return items[i];
            }
        }
        return null; // Not found
    }


    // Search item by name
    public Item findItemByName(String name) {
        for (int i = 0; i < itemCount; i++) {
            // Case-insensitive comparison
            if (items[i].getName().equalsIgnoreCase(name)) {
                return items[i];
            }
        }
        return null; // Not found
    }


    // Update item quantities (Restock/Sell)
    // Delta can be positive (restock) or negative (sell)
    public boolean updateItemQuantity(int itemID, int delta) {
        Item item = findItemByID(itemID);
        if (item == null) {
            System.out.println("Error: Item with ID " + itemID + " not found.");
            return false;
        }


        int newQuantity = item.getQuantity() + delta;
        // Input validation: Cannot sell more than available
        if (newQuantity < 0) {
            System.out.println("Error: Not enough stock to sell " + (-delta) + ". Current stock: " + item.getQuantity());
            return false;
        }


        item.setQuantity(newQuantity); // Use the setter for update
        System.out.printf("Quantity of %s updated to %d.\n", item.getName(), newQuantity);
        return true;
    }


    // Display low stock warnings
    public void checkLowStock() {
        System.out.println("\n--- Low Stock Warning (Quantity < 5) ---");
        boolean anyLowStock = false;
        for (int i = 0; i < itemCount; i++) {
            if (items[i].isLowStock()) { // Use Item's method
                System.out.println(items[i]);
                anyLowStock = true;
            }
        }
        if (!anyLowStock) {
            System.out.println("All items are adequately stocked.");
        }
    }


    // Calculate total inventory value
    public double calculateTotalInventoryValue() {
        double totalValue = 0.0;
        for (int i = 0; i < itemCount; i++) {
            totalValue += items[i].getTotalValue(); // Use Item's method
        }
        return totalValue;
    }


    // Generate inventory report
    public void generateInventoryReport() {
        System.out.println("\n--- Inventory Report ---");
        System.out.println("Total unique item types: " + itemCount); // Number of slots filled
        System.out.println("Current Array Capacity: " + items.length);
        System.out.println("Total Item Objects Created (Static Count): " + Item.getTotalItemsCount());
        System.out.printf("Total Inventory Value: $%.2f\n", calculateTotalInventoryValue());


        System.out.println("\n--- Item Details ---");
        if (itemCount == 0) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (int i = 0; i < itemCount; i++) {
            System.out.println(items[i]);
        }


        checkLowStock(); // Also includes low stock warnings in the report
        System.out.println("--------------------------------");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. Create an Inventory system with Item objects
        Inventory inventory = new Inventory();


        // Seed the inventory
        inventory.addItem(new Item("Laptop", 101, 15, 1200.00));
        inventory.addItem(new Item("Mouse", 102, 3, 25.50)); // Low stock item
        inventory.addItem(new Item("Keyboard", 103, 7, 75.00));


        int choice = -1;


        // 4. Provide a menu-driven interface for all operations [cite: 384]
        while (choice != 0) {
            System.out.println("\n--- Inventory Management Menu ---");
            System.out.println("1: Add new item");
            System.out.println("2: Update item quantity (Restock/Sell)");
            System.out.println("3: Search item by ID");
            System.out.println("4: Generate full report");
            System.out.println("5: Check low stock items");
            System.out.println("0: Exit");
            System.out.println("---------------------------------");
            System.out.print("Enter choice: ");


            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();


                switch (choice) {
                    case 1: // Add new item
                        System.out.print("Enter item Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter item ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter initial Quantity: ");
                        int quantity = scanner.nextInt();
                        System.out.print("Enter Price: $");
                        double price = scanner.nextDouble();
                        scanner.nextLine();


                        inventory.addItem(new Item(name, id, quantity, price));
                        break;


                    case 2: // Update item quantity
                        System.out.print("Enter ID of item to update: ");
                        int updateID = scanner.nextInt();
                        System.out.print("Enter change in quantity (positive for restock, negative for sell): ");
                        int delta = scanner.nextInt();
                        scanner.nextLine();
                        inventory.updateItemQuantity(updateID, delta);
                        break;


                    case 3: // Search item by ID
                        System.out.print("Enter ID of item to search: ");
                        int searchID = scanner.nextInt();
                        scanner.nextLine();
                        Item foundID = inventory.findItemByID(searchID);
                        if (foundID != null) {
                            System.out.println("Found: " + foundID);
                        } else {
                            System.out.println("Item with ID " + searchID + " not found.");
                        }
                        break;


                    case 4: // Generate full report
                        inventory.generateInventoryReport();
                        break;


                    case 5: // Check low stock
                        inventory.checkLowStock();
                        break;


                    case 0:
                        System.out.println("Exiting Inventory Management System.");
                        break;


                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
