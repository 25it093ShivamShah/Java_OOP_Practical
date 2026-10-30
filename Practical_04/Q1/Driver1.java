import java.util.Scanner;

public class Driver1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no. of Passwords you want to test: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] pw = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            pw[i] = scanner.nextLine();
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Rule 1: Length: " + PasswordChecker.checkLength(pw[i]));
            System.out.println("Rule 2: Uppercase: " + PasswordChecker.checkUppercase(pw[i]));
            System.out.println("Rule 3: Digit: " + PasswordChecker.checkDigit(pw[i]));
            System.out.println("Rule 4: Special: " + PasswordChecker.checkSpecial(pw[i]));
            System.out.println(pw[i] + " -> " + PasswordChecker.strength(pw[i]));
        }
        scanner.close();
    }
}
