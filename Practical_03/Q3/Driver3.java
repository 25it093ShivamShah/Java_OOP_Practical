import java.util.Scanner;

public class Driver3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of fractions: ");
        int n = scanner.nextInt();
        Fraction[] fractions = new Fraction[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter numerator of fraction " + (i + 1) + ": ");
            int num = scanner.nextInt();
            System.out.print("Enter denominator of fraction " + (i + 1) + ": ");
            int den = scanner.nextInt();
            Fraction newFrac = new Fraction(num, den);
            boolean duplicate = false;
            for (int j = 0; j < i; j++) {
                if (fractions[j] != null && newFrac.equals(fractions[j])) {
                    System.out.println("Duplicate found: " + newFrac);
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                fractions[i] = newFrac;
            }
        }
        System.out.println("\nFractions entered:");
        for (Fraction f : fractions) {
            if (f != null) {
                System.out.println(f);
            }
        }
        scanner.close();
    }
}
