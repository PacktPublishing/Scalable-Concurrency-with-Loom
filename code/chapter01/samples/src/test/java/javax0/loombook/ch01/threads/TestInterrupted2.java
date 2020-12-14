package javax0.loombook.ch01.threads;

import javax0.loombook.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestInterrupted2 {

    @Test
    @DisplayName("Run the create threads snippet main")
    void interrupted2() throws Exception {
        try (final var __ = Output.file()) {
            Interrupted2.main(null);
        }
    }

}
