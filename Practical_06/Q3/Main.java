import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@FunctionalInterface
interface DiscountRule {
    double apply(double price);
}

record NamedRule(String name, DiscountRule rule) {
}

class RuleRegistry {
    private final Map<Integer, NamedRule> rules = new LinkedHashMap<>();

    public RuleRegistry() {
        rules.put(1, new NamedRule("10% Off", p -> p * 0.90));
        rules.put(2, new NamedRule("20% Off", p -> p * 0.80));
        rules.put(3, new NamedRule("Flat 50 Off", p -> Math.max(0, p - 50)));
        rules.put(4, new NamedRule("15% Off Above 1000", p -> p > 1000 ? p * 0.85 : p));
    }

    public NamedRule get(int id) {
        return rules.get(id);
    }

    public Map<Integer, NamedRule> all() {
        return rules;
    }
}

class DiscountEngine {
    public List<Double> apply(List<Double> prices, DiscountRule rule) {
        return prices.stream()
                .map(rule::apply)
                .collect(Collectors.toList());
    }
}

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
