package javax0.loombook.ch01.threads;

import javax0.loombook.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestOutJavaVersion {

    @Test
    @DisplayName("Run the create threads snippet main")
    void javaVersion() throws Exception {
        try (final var __ = Output.file()) {
            System.out.print("((@define JavaVersion=");
            OutJavaVersion.main(null);
            System.out.println("))");
        }
    }
}
