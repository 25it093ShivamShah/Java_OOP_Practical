import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of log lines: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] logs = new String[n];
        System.out.println("Enter the log messages:");
        for (int i = 0; i < n; i++) {
            logs[i] = scanner.nextLine();
        }

        System.out.print("Enter keyword to flag: ");
        String keyword = scanner.nextLine();

        String result = chatFilter.filter(logs, keyword);

        System.out.println("\n--- Moderation Report ---");
        System.out.println(result);

        scanner.close();
    }
}
