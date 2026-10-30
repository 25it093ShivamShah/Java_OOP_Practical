import java.io.*;

class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private transient String sessionToken;

    public UserAccount(String username, String email, String sessionToken) {
        this.username = username;
        this.email = email;
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "UserAccount{username='" + username + "\'', email='" + email + "\'', sessionToken='" + sessionToken + "\''}";
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        String filename = "users.ser";

        UserAccount[] originalAccounts = {
            new UserAccount("Shivam", "Shivam@gmail.com", "token_xyz_123"),
            new UserAccount("Raj", "Raj@gmail.com", "token_abc_456")
        };

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(originalAccounts);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UserAccount[] restoredAccounts = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            restoredAccounts = (UserAccount[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Restored Accounts:");
        if (restoredAccounts != null) {
            for (UserAccount account : restoredAccounts) {
                System.out.println(account);
            }
        }

        new File(filename).delete();
    }
}
