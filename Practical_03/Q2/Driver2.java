import java.util.Scanner;

public class Driver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter no. of cards you want to add: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        Card[] c = new Card[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter rank of card " + (i + 1) + " : ");
            String rank = scanner.nextLine();
            System.out.print("Enter suit card " + (i + 1) + " : ");
            String suit = scanner.nextLine();
            Card newc = new Card(rank, suit);
            boolean dupli = false;
            for (int j = 0; j < i; j++) {
                if (c[j] != null && newc.equals(c[j])) {
                    System.out.println("Duplicate found: " + newc);
                    dupli = true;
                    break;
                }
            }
            if (!dupli) {
                c[i] = newc;
            }
        }
        scanner.close();
    }
}
