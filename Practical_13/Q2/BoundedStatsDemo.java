public class BoundedStatsDemo {

    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            return 0.0;
        }

        double sum = 0.0;
        for (N val : values) {
            if (val != null) {
                sum += val.doubleValue();
            }
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        Integer[] intValues = {10, 20, 30, 40};
        Double[] doubleValues = {2.5, 3.5, 4.0};
        Long[] longValues = {100L, 200L, 300L, 400L, 500L};

        System.out.println("Integer Average: " + average(intValues));
        System.out.println("Double Average: " + average(doubleValues));
        System.out.println("Long Average: " + average(longValues));
    }
}
