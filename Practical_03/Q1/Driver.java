import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0, n;
        System.out.print("Enter no. of coordinates: ");
        n = scanner.nextInt();
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            int a, b;
            System.out.print("Enter x-coordinate: ");
            a = scanner.nextInt();
            System.out.print("Enter y-coordinate: ");
            b = scanner.nextInt();
            p[i] = new Point(a, b);
        }
        for (int i = 0; i < n; i++) {
            boolean appeared = false;
            for (int j = 0; j < i; j++) {
                if (p[i].equals(p[j])) {
                    appeared = true;
                    break;
                }
            }
            if (!appeared) {
                count++;
            }
        }
        System.out.println(n + " points with " + (n - count) + " repeated -> Distinct: " + count);
        scanner.close();
    }
}
