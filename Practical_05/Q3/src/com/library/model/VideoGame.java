package com.library.model;

public class VideoGame extends MediaItem {
    public VideoGame(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "VideoGame";
    }

    @Override
    public double lateFee(int daysLate) {
        int firstTier = Math.min(daysLate, 5);
        int secondTier = Math.max(0, daysLate - 5);
        return Math.min(50.00, firstTier * 3.00 + secondTier * 5.00);
    }
}
