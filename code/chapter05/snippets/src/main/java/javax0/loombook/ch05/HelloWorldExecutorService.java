package javax0.loombook.ch05;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HelloWorldExecutorService {

    public static void main(String[] args) {
        try (final ExecutorService e = Executors.newVirtualThreadExecutor().withDeadline(Instant.now().plusSeconds(30))) {
            Future<Object> f = e.submit(() -> {
                System.out.println("Hello, World!");
                return null;
            });
            f.get();
        } catch (Exception interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
