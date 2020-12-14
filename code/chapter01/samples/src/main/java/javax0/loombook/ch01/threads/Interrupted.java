package javax0.loombook.ch01.threads;

public class Interrupted {
    // snippet Interrupted

    final static Runnable interruptedLoop = () -> {
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("I am interrupted!");
                break;
            }
        }
    };

    public static void main(String[] args) {
        final var t = new Thread(interruptedLoop);
        t.start();
        try {
            Thread.sleep(100);
            t.interrupt();
            t.join();
            System.out.println("Main joined the thread.");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }
    // end snippet

}
