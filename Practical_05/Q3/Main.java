import java.util.List;

abstract class MediaItem {
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

class Book extends MediaItem {
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

class Dvd extends MediaItem {
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

class Magazine extends MediaItem {
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

class VideoGame extends MediaItem {
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

record ReturnedItem(MediaItem item, int daysLate) {
    public double fee() {
        return daysLate <= 0 ? 0.0 : item.lateFee(daysLate);
    }
}

class LateFeeCalculator {
    public double total(List<ReturnedItem> batch) {
        return batch.stream()
                .mapToDouble(ReturnedItem::fee)
                .sum();
    }
}

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
