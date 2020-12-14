package javax0.loombook.ch01.threads;

public class CreateThreads {
    // snippet HelloThreads
    public static void main(String[] args)
        throws InterruptedException {

        final var t1 = new Thread(
            () -> System.out.println("Hello, thread 1"));
        final var t2 = new Thread(
            () -> System.out.println("Hello, thread 2"));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
    // end snippet
}
