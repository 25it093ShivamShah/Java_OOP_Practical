package com.library.service;

import java.util.List;

public class LateFeeCalculator {
    public double total(List<ReturnedItem> batch) {
        return batch.stream()
                .mapToDouble(ReturnedItem::fee)
                .sum();
    }
}
