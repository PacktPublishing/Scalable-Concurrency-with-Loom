package javax0.loombook.ch01.threads;

import javax0.loombook.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestTryCalculateE {
    @DisplayName("Calculate e in a future")
    @Test
    void test() throws Exception {
        try (var __ = Output.file()) {
            TryCalculateE.main(null);
        }
    }
}
