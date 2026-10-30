import java.util.Scanner;

public class TollSystem {
    public static void main(String[] args) {
        int total = 0, bikeCount = 0, carCount = 0, truckCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter vehicle number (or 'done' to stop): ");
            String number = scanner.nextLine();
            if (number.equalsIgnoreCase("done")) break;

            System.out.print("Enter vehicle type (bike, car, truck): ");
            String type = scanner.nextLine().toLowerCase();

            int toll = 0;
            switch (type) {
                case "bike":
                    toll = 20;
                    bikeCount++;
                    break;
                case "car":
                    toll = 50;
                    carCount++;
                    break;
                case "truck":
                    toll = 150;
                    truckCount++;
                    break;
                default:
                    System.out.println("Invalid type!");
                    continue;
            }

            total += toll;
        }

        System.out.println("Total toll: " + total);
        if (bikeCount >= carCount && bikeCount >= truckCount) {
            System.out.println("Most frequent: bike");
        } else if (carCount >= bikeCount && carCount >= truckCount) {
            System.out.println("Most frequent: car");
        } else {
            System.out.println("Most frequent: truck");
        }
        scanner.close();
    }
}
