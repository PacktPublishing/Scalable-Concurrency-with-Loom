package javax0.loombook.ch02;

import javax0.loombook.Utils;

import java.util.Set;

public class ThreadGroupExample {
    private static int N = 20;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        final var group = new ThreadGroup("example thread group");
        group.setDaemon(false);
        group.setMaxPriority(Thread.MAX_PRIORITY);
        for (boolean virtual : Set.of(true, false)) {
            demonstrateThreads(group, virtual);
        }
    }

    private static void demonstrateThreads(ThreadGroup group, boolean virtual) throws InterruptedException {
        final var threads = new Thread[N];
        System.out.println(virtual ? "virtual" : "kernel");
        synchronized (lock) {
            for (int i = 0; i < N; i++) {
                var thread = Thread.builder().group(group).name("worker-", i).task(() -> {
                        synchronized (lock) {
                            printInterrupted();
                        }
                    }
                );
                if (virtual)
                    thread = thread.virtual();
                threads[i] = thread.build();
                threads[i].setPriority(9);
                threads[i].start();
                Utils.sleepIgnoreInterrupt(100);
                System.out.println(threads[i].toString() + "'s group is " + threads[i].getThreadGroup());
            }
            ThreadGroup vgroup;
            if (virtual) {
                vgroup = threads[0].getThreadGroup();
                System.out.println("Virtual group count: " + vgroup.activeCount());
                vgroup.interrupt();
                threads[0].interrupt();
            }
            System.out.println("Group count: " + group.activeCount());
            group.interrupt();
        }
        for (final var t : threads) {
            t.join();
        }
    }

    private static void printInterrupted() {
        System.out.print(Thread.currentThread().getName());
        if (Thread.interrupted()) {
            System.out.println(" is interrupted.");
        } else {
            System.out.println(" is free as a bird.");
        }
    }
}
