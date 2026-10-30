class OperationException extends Exception {
    public OperationException(String message) {
        super(message);
    }
}

class MyResource implements AutoCloseable {
    private String name;

    public MyResource(String name) {
        this.name = name;
        System.out.println("Resource " + name + " opened.");
    }

    public void doWork(boolean fail) throws OperationException {
        System.out.println("Working with " + name + "...");
        if (fail) {
            throw new OperationException("Operation failed on " + name);
        }
    }

    @Override
    public void close() {
        System.out.println("Resource " + name + " closed automatically.");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Scenario 1: Normal Execution ---");
        try (MyResource res = new MyResource("DatabaseConnection")) {
            res.doWork(false);
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        System.out.println("\n--- Scenario 2: Execution with Exception ---");
        try (MyResource res = new MyResource("FileStream")) {
            res.doWork(true);
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
