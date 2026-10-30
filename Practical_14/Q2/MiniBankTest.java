import java.io.*;
import java.util.*;

class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String accountNumber;
    private final String holderName;
    private double balance;

    public Account(String accountNumber, String holderName, double balance) {
        if (balance < 0) throw new IllegalArgumentException("Initial balance cannot be negative");
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    public void withdraw(double amount) {
        if (amount > balance) throw new IllegalStateException("Insufficient funds");
        this.balance -= amount;
    }
}

class BankService {
    private final List<Account> accounts = new ArrayList<>();

    public void createAccount(String num, String name, double balance) {
        if (findAccount(num) != null) throw new IllegalArgumentException("Account already exists");
        accounts.add(new Account(num, name, balance));
    }

    public Account findAccount(String num) {
        return accounts.stream().filter(a -> a.getAccountNumber().equals(num)).findFirst().orElse(null);
    }

    public int getAccountCount() { return accounts.size(); }

    public void save(File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(new ArrayList<>(accounts));
        }
    }

    @SuppressWarnings("unchecked")
    public void load(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            accounts.clear();
            accounts.addAll((List<Account>) ois.readObject());
        }
    }
}

public class MiniBankTest {

    public static void main(String[] args) {
        testAddingAccountIncreasesCount();
        testInvalidActionThrows();
        testSaveAndReloadDataUnchanged();
        System.out.println("All 3 tests PASSED successfully!");
    }

    static void testAddingAccountIncreasesCount() {
        BankService service = new BankService();
        if (service.getAccountCount() != 0) throw new RuntimeException("Test 1 Failed: Count should be 0");
        service.createAccount("A1", "Bob", 50.0);
        if (service.getAccountCount() != 1) throw new RuntimeException("Test 1 Failed: Count should be 1");
        System.out.println("PASS: testAddingAccountIncreasesCount");
    }

    static void testInvalidActionThrows() {
        Account account = new Account("A1", "Bob", 50.0);
        boolean caught = false;
        try {
            account.withdraw(100.0);
        } catch (IllegalStateException e) {
            caught = true;
        }
        if (!caught) throw new RuntimeException("Test 2 Failed: Expected IllegalStateException on overdraft");
        System.out.println("PASS: testInvalidActionThrows");
    }

    static void testSaveAndReloadDataUnchanged() {
        File file = new File("test_temp_bank.ser");
        try {
            BankService service1 = new BankService();
            service1.createAccount("A1", "Alice", 200.0);
            service1.save(file);

            BankService service2 = new BankService();
            service2.load(file);

            if (service2.getAccountCount() != 1 || service2.findAccount("A1").getBalance() != 200.0) {
                throw new RuntimeException("Test 3 Failed: Restored data mismatch");
            }
            System.out.println("PASS: testSaveAndReloadDataUnchanged");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            file.delete();
        }
    }
}
