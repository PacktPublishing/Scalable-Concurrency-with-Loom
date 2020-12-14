package javax0.loombook.ch05;

public class HelloWorldWithBuilder {
    public static void main(String[] args) {
        try {
            final var t = Thread.builder()
                .virtual()
                .task(() -> System.out.println("Hello, World!"))
                .start();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }}
