package javax0.loombook.ch01.threads;

import javax0.loombook.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TestHelloThreadLocal {

    @Test
    @DisplayName("Run the create threads snippet main")
    void threadLocal() throws Exception {
        File output;
        try (final var out = Output.file()) {
            HelloThreadLocal.main(null);
            System.out.println();
            output = out.get();
        }
        assertOutputIsDemonstrative(output);
    }

    /**
     * Check the the output is demonstrative.
     * <p>
     * The output is demonstrative is there is a thread local printout 2 after a 1 and there is at least 1 after a 2.
     * <p>
     * If the  output is not demonstrative then it recursively calls the unit tests. Non-demonstrative output is very
     * unlikely and it is random.
     * <p>
     * If we do not get a demonstrative output until the stack runs out then we are cosmically unlucky.
     * <p>
     * In that case there is a bug somewhere.
     *
     * @param output the output file to check.
     * @throws Exception when the output file shows that there were more than two threads executing
     */
    private void assertOutputIsDemonstrative(File output) throws Exception {
        try (final var reader = new BufferedReader(new FileReader(output))) {
            reader.readLine(); // snippet definition
            final AtomicBoolean first = new AtomicBoolean(true);
            final AtomicInteger previous = new AtomicInteger(0);
            final int[] count = new int[2];
            Arrays.stream(reader.readLine().replaceAll("\\d+\\((\\d+)\\)", "$1")
                .split("\\s+"))
                .filter(s -> s.length() > 0)
                .mapToInt(Integer::parseInt)
                .peek(i -> {
                    if (i < 1 || i > 2) {
                        throw new RuntimeException("Wrong " + output.getAbsolutePath());
                    }
                })
                .peek(i -> {
                        if (first.get()) {
                            first.set(false);
                            previous.set(i);
                        }
                    }
                )
                .map(
                    i -> {
                        final var diff = i - previous.get();
                        previous.set(i);
                        return diff;
                    }
                )
                .filter(i -> i != 0)
                .forEach(i -> count[(i + 1) / 2]++);
            if (count[0] == 0 || count[1] == 0) {
                HelloThreadLocal.resetCount();
                threadLocal();
            }
        }
    }
}
