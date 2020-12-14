package javax0.loombook.ch01.threads;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TryCalculateE {
    // snippet Future_e
    private static final long MAX_TIME = 20; // ms
    private static final int N = 100000;
    private static final BigDecimal EPSILON =
        new BigDecimal("0." + "0".repeat(N) + "1");
    private static final MathContext mc =
        new MathContext(N + 2, RoundingMode.HALF_UP);

    public static void main(String[] args)
        throws ExecutionException, InterruptedException {
        try (ExecutorService es =
                 Executors.newSingleThreadExecutor()) {
            final var calculateE =
                es.submit(TryCalculateE::calculator);
            long start = System.currentTimeMillis();
            long now = start;
            while (!calculateE.isDone()) {
                now = System.currentTimeMillis();
                if (now - start > MAX_TIME) {
                    calculateE.cancel(true);
                }
            }
            if (calculateE.isCancelled()) {
                System.out.println("We have not finished in "
                    + MAX_TIME + "ms");
                System.out.println(
                    "We have to use 2.718281...");
            } else {
                System.out.println("We have finished in "
                    + (now - start) + "ms");
                BigDecimal e = calculateE.get();
                System.out.println((("" + e).length() - 2)
                    + " decimal precision");
            }
        }
    }
    // end snippet

    // snippet calculator
    private static BigDecimal calculator() {
        BigDecimal fact = BigDecimal.ONE;
        BigDecimal e = BigDecimal.valueOf(2.0);
        BigDecimal n = BigDecimal.valueOf(2);
        BigDecimal diff;
        do {
            final var e0 = e;
            fact = fact.multiply(n);
            n = n.add(BigDecimal.ONE);
            e = e.add(BigDecimal.ONE.divide(fact, mc));
            if (Thread.interrupted()) {
                System.out.println("Interrupted");
                return null;
            }
            diff = e.subtract(e0).abs();
        } while (diff.compareTo(EPSILON) >= 0);
        return e;
    }
    // end snippet

}
