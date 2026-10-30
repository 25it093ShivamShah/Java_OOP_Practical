import java.util.Scanner;

public class Driver3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter template: ");
        String template = scanner.nextLine();

        System.out.print("Enter number of placeholders: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] names = new String[n];
        String[] values = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter placeholder name " + (i + 1) + ": ");
            names[i] = scanner.nextLine();
            System.out.print("Enter value for " + names[i] + ": ");
            values[i] = scanner.nextLine();
        }

        String output = TemplateFiller.fill(template, names, values);

        System.out.println("\n--- Filled Template ---");
        System.out.println(output);

        scanner.close();
    }
}
