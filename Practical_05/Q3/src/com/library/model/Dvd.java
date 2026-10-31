package com.library.model;

public class Dvd extends MediaItem {
    public Dvd(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "DVD";
    }

    @Override
    public double lateFee(int daysLate) {
        return Math.min(25.00, daysLate * 2.00);
    }
}
