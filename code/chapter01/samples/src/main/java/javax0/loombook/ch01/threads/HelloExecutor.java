package javax0.loombook.ch01.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class HelloExecutor {
    // snippet HelloExecutor
    private static final int THREADS = 3;
    private static final int TASKS = 4;

    private static long startTime;

    private static void logTime(String s) {
        System.out.println("Thread "
            + Thread.currentThread().getName()
            + s
            + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] args)
        throws InterruptedException {

        try (ExecutorService es =
                 Executors.newFixedThreadPool(THREADS)) {
            startTime = System.currentTimeMillis();
            for (int i = 0; i < TASKS; i++) {
                logTime(" schedule ");
                es.execute(() -> {
                    logTime(" start ");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        logTime(" interrupted ");
                        return;
                    }
                    logTime(" end ");
                });
            }
            es.shutdown();
            while (!es.awaitTermination(10, MILLISECONDS)) {
                Thread.onSpinWait();
            }
        }

    }
    // end snippet
}
