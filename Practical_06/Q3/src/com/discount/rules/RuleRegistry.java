package com.discount.rules;

import java.util.LinkedHashMap;
import java.util.Map;

public class RuleRegistry {
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
