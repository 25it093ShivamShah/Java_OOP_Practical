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

public class MiniBankApp {
    public static void main(String[] args) {
        BankService service = new BankService();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Create Account  2. Exit");
        if (sc.hasNextLine() && sc.nextLine().trim().equals("1")) {
            service.createAccount("ACC-1", "Alice", 100.0);
            System.out.println("Created account! Total: " + service.getAccountCount());
        }
    }
}
