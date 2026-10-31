package com.library.model;

public class Magazine extends MediaItem {
    public Magazine(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "Magazine";
    }

    @Override
    public double lateFee(int daysLate) {
        return Math.max(0, daysLate - 3) * 0.25;
    }
}
