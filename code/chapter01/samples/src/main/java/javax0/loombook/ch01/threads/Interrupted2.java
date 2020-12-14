package javax0.loombook.ch01.threads;

public class Interrupted2 {

    // snippet Interrupted2
    final static Runnable interruptedLoop = () -> {
        while (true) {
            if( Thread.interrupted()){
                System.out.println("I am interrupted!");
                break;
            }
            Thread.onSpinWait();
        }
    };
    // end snippet

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

}
