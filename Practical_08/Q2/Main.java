import java.util.Scanner;

class OutOfStockException extends Exception {
    private int shortfall;

    public OutOfStockException(String item, int shortfall) {
        super("Out of stock for " + item + "! Shortfall: " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private String[] items = {"Laptop", "Mouse", "Keyboard"};
    private int[] stock = {5, 10, 2};

    public void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Requested quantity must be positive: " + qty);
        }

        int index = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(item)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Item not found in warehouse: " + item);
            return;
        }

        if (stock[index] < qty) {
            int shortfall = qty - stock[index];
            throw new OutOfStockException(item, shortfall);
        }

        stock[index] -= qty;
        System.out.println("Issued " + qty + " of " + item + ". Remaining: " + stock[index]);
    }
}

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        String[] requestedItems = {"Laptop", "Mouse", "Keyboard", "Mouse"};
        int[] requestedQty = {2, -1, 5, 2};

        for (int i = 0; i < requestedItems.length; i++) {
            System.out.println("\nProcessing request for: " + requestedItems[i] + " (" + requestedQty[i] + ")");
            try {
                warehouse.issue(requestedItems[i], requestedQty[i]);
            } catch (InvalidQuantityException e) {
                System.out.println("Invalid Quantity: " + e.getMessage());
            } catch (OutOfStockException e) {
                System.out.println("Shortfall Notice: " + e.getMessage());
            }
        }
    }
}
