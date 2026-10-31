package com.library.model;

public class Book extends MediaItem {
    public Book(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "Book";
    }

    @Override
    public double lateFee(int daysLate) {
        return Math.min(20.00, daysLate * 0.50);
    }
}
