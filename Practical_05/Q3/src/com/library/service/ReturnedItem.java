package com.library.service;

import com.library.model.MediaItem;

public record ReturnedItem(MediaItem item, int daysLate) {
    public double fee() {
        return daysLate <= 0 ? 0.0 : item.lateFee(daysLate);
    }
}
