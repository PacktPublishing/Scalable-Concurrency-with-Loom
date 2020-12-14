package javax0.loombook.ch05;

import javax0.loombook.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestHelloWorldExecutorService {

    @Test
    @DisplayName("Run the Hello, World snippet main")
    void helloWorld() throws Exception {
        try (final var __ = Output.file()) {
            HelloWorldExecutorService.main(null);
        }
    }
}
