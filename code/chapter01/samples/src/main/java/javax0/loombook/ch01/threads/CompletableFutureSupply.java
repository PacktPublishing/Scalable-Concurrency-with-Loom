package javax0.loombook.ch01.threads;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureSupply {
    private static final long MAX_TIME = 20; // ms
    private static final int N = 1000;
    private static final BigDecimal EPSILON = new BigDecimal("0." + "0".repeat(N) + "1");
    private static final MathContext mc = new MathContext(N + 2, RoundingMode.HALF_UP);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
    // snippet CompleteFuture_ee
        final var calculateE =
            CompletableFuture.supplyAsync(() -> calculator());
        long start = System.currentTimeMillis();
        long now = start;
        while (!calculateE.isDone()) {
            now = System.currentTimeMillis();
            if (now - start > MAX_TIME) {
                calculateE.cancel(true);
            }
        }
    // end snippet
        if (calculateE.isCancelled()) {
            System.out.println("We have not finished in " + MAX_TIME + "ms");
        } else {
            System.out.println("We have finished in " + (now - start) + "ms");
            BigDecimal e = calculateE.get();
            System.out.println((("" + e).length() - 2) + " decimal precision");
        }
    }

    private static BigDecimal calculator() {
        BigDecimal fact = BigDecimal.ONE;
        BigDecimal e = BigDecimal.valueOf(2.0);
        BigDecimal n = BigDecimal.valueOf(2);
        BigDecimal e0;
        do {
            e0 = e;
            fact = fact.multiply(n);
            n = n.add(BigDecimal.ONE);
            e = e.add(BigDecimal.ONE.divide(fact, mc));
            if (Thread.interrupted()) {
                System.out.println("Interrupted");
                return null;
            }
        } while (e.subtract(e0).abs().compareTo(EPSILON) >= 0);
        return e;
    }

}