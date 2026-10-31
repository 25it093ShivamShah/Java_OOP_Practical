package com.library.model;

public abstract class MediaItem {
    private final String title;

    protected MediaItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract String getType();

    public abstract double lateFee(int daysLate);
}
