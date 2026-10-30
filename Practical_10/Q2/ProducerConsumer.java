class Buffer {
    private int data;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            wait();
        }
        data = value;
        available = true;
        System.out.println("Produced: " + value);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (!available) {
            wait();
        }
        int value = data;
        available = false;
        System.out.println("Consumed: " + value);
        notify();
        return value;
    }
}

class Producer extends Thread {
    private Buffer buffer;
    private int count;

    public Producer(Buffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {
        try {
            for (int i = 1; i <= count; i++) {
                buffer.produce(i);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer extends Thread {
    private Buffer buffer;
    private int count;

    public Consumer(Buffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {
        try {
            for (int i = 1; i <= count; i++) {
                buffer.consume();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        int totalItems = 10;

        Producer producer = new Producer(buffer, totalItems);
        Consumer consumer = new Consumer(buffer, totalItems);

        producer.start();
        consumer.start();
    }
}
