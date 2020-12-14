package javax0.loombook.ch01.threads;

import javax0.loombook.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestHappensBeforeVolatile {
    @Test
    @DisplayName("Run the HappensBeforeVolatile main")
    void createThread() throws Exception {
        try (final var __ = Output.file()) {
            HappensBeforeVolatile.main(null);
        }
    }
}
