import java.util.Scanner;

enum Coin {
    ONE, TWO, FIVE, TEN
}

public class VendingMachine {
    public static void main(String[] args) {
        int price = 15, total = 0, value = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("The price of snack is: " + price);
        while (total < price) {
            System.out.print("Now you can insert coin (ONE,TWO,FIVE,TEN): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("ONE") || input.equals("TWO") || input.equals("FIVE") || input.equals("TEN")) {
                Coin coin = Coin.valueOf(input);
                switch (coin) {
                    case ONE:
                        value = 1;
                        break;
                    case TWO:
                        value = 2;
                        break;
                    case FIVE:
                        value = 5;
                        break;
                    case TEN:
                        value = 10;
                        break;
                }
                total += value;
                System.out.println("Total so far: " + total);
            } else {
                System.out.println("Enter valid coin!!");
            }
        }
        System.out.println("Amount paid successfully!!");
        System.out.println("Your change is: " + (total - price));
        scanner.close();
    }
}
