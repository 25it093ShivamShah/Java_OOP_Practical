package com.library;

import com.library.model.Book;
import com.library.model.Dvd;
import com.library.model.Magazine;
import com.library.model.VideoGame;
import com.library.service.LateFeeCalculator;
import com.library.service.ReturnedItem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<ReturnedItem> batch = List.of(
                new ReturnedItem(new Book("Clean Code"), 6),
                new ReturnedItem(new Dvd("Inception"), 4),
                new ReturnedItem(new Magazine("Wired"), 10),
                new ReturnedItem(new VideoGame("Zelda"), 8),
                new ReturnedItem(new Book("Algorithms"), 60),
                new ReturnedItem(new Dvd("Matrix"), 0)
        );

        LateFeeCalculator calculator = new LateFeeCalculator();

        System.out.printf("%-10s %-16s %9s %9s%n", "Type", "Title", "Days Late", "Fee");
        System.out.println("-".repeat(47));

        for (ReturnedItem r : batch) {
            System.out.printf("%-10s %-16s %9d %9.2f%n",
                    r.item().getType(), r.item().getTitle(), r.daysLate(), r.fee());
        }

        System.out.println("-".repeat(47));
        System.out.printf("%-10s %-16s %9s %9.2f%n", "TOTAL", "", "", calculator.total(batch));
    }
}
