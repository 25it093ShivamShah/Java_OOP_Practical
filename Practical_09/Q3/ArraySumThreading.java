import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ArraySumThreading {

    private static final int ARRAY_SIZE = 100_000_000;
    private static final int NUM_THREADS = 4;
    private static int[] arr = new int[ARRAY_SIZE];

    private static long wrongTotal = 0;
    private static AtomicLong atomicTotal = new AtomicLong(0);
    private static long optimizedTotal = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            arr[i] = 1;
        }

        System.out.println("Expected Total: " + ARRAY_SIZE);
        System.out.println("----------------------------------------------");

        measureExecution("1. Wrong Result", ArraySumThreading::calculateWrong);
        measureExecution("2. Fix 1: AtomicLong", ArraySumThreading::calculateWithAtomic);
        measureExecution("3. Fix 2: Local Chunk Sum + Synchronized", ArraySumThreading::calculateWithOptimizedSync);
    }

    private static void calculateWrong() {
        executeInThreads((start, end) -> {
            for (int i = start; i < end; i++) {
                wrongTotal += arr[i];
            }
        });
        System.out.println("Result: " + wrongTotal);
    }

    private static void calculateWithAtomic() {
        executeInThreads((start, end) -> {
            for (int i = start; i < end; i++) {
                atomicTotal.addAndGet(arr[i]);
            }
        });
        System.out.println("Result: " + atomicTotal.get());
    }

    private static void calculateWithOptimizedSync() {
        executeInThreads((start, end) -> {
            long localSum = 0;
            for (int i = start; i < end; i++) {
                localSum += arr[i];
            }
            synchronized (lock) {
                optimizedTotal += localSum;
            }
        });
        System.out.println("Result: " + optimizedTotal);
    }

    private static void executeInThreads(ChunkTask task) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        int chunkSize = ARRAY_SIZE / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            final int start = i * chunkSize;
            final int end = (i == NUM_THREADS - 1) ? ARRAY_SIZE : (start + chunkSize);

            executor.submit(() -> task.process(start, end));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void measureExecution(String label, Runnable task) {
        long startTime = System.currentTimeMillis();
        System.out.println("Running: " + label);
        task.run();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " ms\n");
    }

    @FunctionalInterface
    interface ChunkTask {
        void process(int start, int end);
    }
}
