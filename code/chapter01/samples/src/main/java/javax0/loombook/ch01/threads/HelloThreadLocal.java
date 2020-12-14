package javax0.loombook.ch01.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class HelloThreadLocal {
    // snippet HelloThreadLocal
    private static final int THREADS = 2;
    private static final int REPEAT = 10;
    private static final ThreadLocal<Integer> tl =
        new ThreadLocal<>();
    private static final AtomicInteger count =
        new AtomicInteger(0);

    private static void callBack() {
        int k = count.incrementAndGet();
        System.out.print(" " + k + "(" + tl.get() + ")");
        if (k == 10) {
            System.out.println();
        }
    }

    private static void middle() {
        callBack();
    }

    private static final AtomicInteger tlCounter =
        new AtomicInteger(0);
    private static final Runnable task = () -> {
        tl.set(tlCounter.incrementAndGet());
        for (int j = 0; j < REPEAT; j++) {
            middle();
        }
    };

    public static void main(String[] args)
        throws InterruptedException {
        try (ExecutorService es =
                 Executors.newFixedThreadPool(THREADS)) {
            for (int i = 0; i < THREADS; i++) {
                es.execute(task);
            }
            es.shutdown();
            while (!es.awaitTermination(1, MILLISECONDS)) {
                Thread.onSpinWait();
            }
        }
    }
    // end snippet

    static void resetCount(){
        count.set(0);
        tlCounter.set(0);
    }
}
