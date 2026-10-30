class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

public class GenericContainersDemo {

    public static <T> T getFirst(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.set("Generics in Java");
        String text = stringBox.get();
        System.out.println("String Box: " + text);

        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        Integer number = intBox.get();
        System.out.println("Integer Box: " + number);

        Pair<String, Integer> studentRecord = new Pair<>("Alice", 95);
        Pair<Double, Boolean> sensorReading = new Pair<>(36.6, true);
        System.out.println("Student: " + studentRecord);
        System.out.println("Sensor: " + sensorReading);

        String[] words = {"Apple", "Banana", "Cherry"};
        Integer[] nums = {10, 20, 30};

        String firstWord = getFirst(words);
        Integer firstNum = getFirst(nums);

        System.out.println("First Word: " + firstWord);
        System.out.println("First Number: " + firstNum);
    }
}
