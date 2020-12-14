package javax0.loombook.ch01.threads;
// snippet HappensBeforeVolatile
public class HappensBeforeVolatile {
    volatile static int counter;
    static String word;

    public static void main(String[] args)
        throws InterruptedException {
        counter = 0;
        Thread t1 = new Thread(() -> {
            word = "Hello";
            counter = 1;
            while (counter != 2) {
                Thread.onSpinWait();
            }
            word = ", World";
            counter = 3;
        }
        );
        Thread t2 = new Thread(() -> {
            while (counter != 1) {
                Thread.onSpinWait();
            }
            System.out.print(word);
            counter = 2;
            while (counter != 3) {
                Thread.onSpinWait();
            }
            System.out.println(word);
        }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
// end snippet