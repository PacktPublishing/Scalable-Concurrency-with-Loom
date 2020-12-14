package javax0.loombook.ch05;

// snippet HelloWorld
public class HelloWorld {
    public static void main(String[] args) {
        try {
            final var t = Thread.startVirtualThread(() -> System.out.println("Hello, World."));
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
// end snippet