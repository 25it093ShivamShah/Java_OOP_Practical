package com.discount.rules;

@FunctionalInterface
public interface DiscountRule {
    double apply(double price);
}
