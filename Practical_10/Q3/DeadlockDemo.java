public class DeadlockDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("[Thread 1] Trying to acquire Lock A...");
            synchronized (lockA) {
                System.out.println("[Thread 1] Acquired Lock A! Pausing...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("[Thread 1] Trying to acquire Lock B...");
                synchronized (lockB) {
                    System.out.println("[Thread 1] Acquired Lock B! Transfer complete.");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("[Thread 2] Trying to acquire Lock B...");
            synchronized (lockB) {
                System.out.println("[Thread 2] Acquired Lock B! Pausing...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("[Thread 2] Trying to acquire Lock A...");
                synchronized (lockA) {
                    System.out.println("[Thread 2] Acquired Lock A! Transfer complete.");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("All transfers finished!");
    }
}
