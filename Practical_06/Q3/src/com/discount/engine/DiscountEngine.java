package com.discount.engine;

import com.discount.rules.DiscountRule;

import java.util.List;
import java.util.stream.Collectors;

public class DiscountEngine {
    public List<Double> apply(List<Double> prices, DiscountRule rule) {
        return prices.stream()
                .map(rule::apply)
                .collect(Collectors.toList());
    }
}
