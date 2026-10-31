package com.discount;

import com.discount.engine.DiscountEngine;
import com.discount.rules.NamedRule;
import com.discount.rules.RuleRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RuleRegistry registry = new RuleRegistry();
        DiscountEngine engine = new DiscountEngine();

        System.out.print("Enter prices separated by spaces: ");
        List<Double> prices = new ArrayList<>();
        for (String token : sc.nextLine().trim().split("\\s+")) {
            prices.add(Double.parseDouble(token));
        }

        while (true) {
            System.out.println();
            System.out.println("===== Discount Engine =====");
            for (Map.Entry<Integer, NamedRule> e : registry.all().entrySet()) {
                System.out.println(e.getKey() + ". " + e.getValue().name());
            }
            System.out.println("0. Exit");
            System.out.print("Choose rule: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Goodbye.");
                break;
            }

            NamedRule selected = registry.get(choice);
            if (selected == null) {
                System.out.println("Unknown rule.");
                continue;
            }

            List<Double> result = engine.apply(prices, selected.rule());

            System.out.println();
            System.out.println("Rule: " + selected.name());
            for (int i = 0; i < prices.size(); i++) {
                System.out.printf("%10.2f  ->  %10.2f%n", prices.get(i), result.get(i));
            }

            double before = prices.stream().mapToDouble(Double::doubleValue).sum();
            double after = result.stream().mapToDouble(Double::doubleValue).sum();
            System.out.printf("Total: %.2f -> %.2f%n", before, after);
        }
    }
}
