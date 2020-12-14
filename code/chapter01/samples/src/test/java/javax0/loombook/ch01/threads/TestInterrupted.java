package javax0.loombook.ch01.threads;

import javax0.loombook.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestInterrupted {

    @Test
    @DisplayName("Run the create threads snippet main")
    void interrupted() throws Exception {
        try (final var __ = Output.file()) {
            Interrupted.main(null);
        }
    }

}
