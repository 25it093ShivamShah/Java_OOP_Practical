import java.util.InputMismatchException;
import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean success = false;
        int attempts = 0;

        while (!success) {
            attempts++;
            try {
                System.out.println("\n--- Attempt " + attempts + " ---");
                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter operator (+, -, *, /): ");
                char op = scanner.next().charAt(0);

                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                if (op == '/' && num2 == 0) {
                    throw new DivideByZeroException("Division by zero is not allowed.");
                }

                double result = 0;
                switch (op) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = num1 / num2; break;
                    default:
                        System.out.println("Invalid operator!");
                        continue;
                }

                System.out.println("Result: " + result);
                success = true;

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid number format. Please enter numeric values.");
                scanner.nextLine();
            } catch (DivideByZeroException e) {
                System.out.println("Custom Error: " + e.getMessage());
            } finally {
                System.out.println("Log: Finished attempt " + attempts);
            }
        }
        scanner.close();
    }
}
