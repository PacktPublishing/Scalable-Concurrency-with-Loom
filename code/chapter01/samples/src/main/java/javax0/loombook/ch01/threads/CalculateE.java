package javax0.loombook.ch01.threads;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculateE {
    private static final int N = 1000;
    private static final int MAX_ITERATIONS = 2000;
    private static final MathContext mc = new MathContext(N + 2, RoundingMode.HALF_UP);


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final long startP, endP;
        BigDecimal eP;
        try (ExecutorService es = Executors.newWorkStealingPool()) {
            startP = System.currentTimeMillis();
            eP = getE(es);
            endP = System.currentTimeMillis();
        }

        final long startV, endV;
        BigDecimal eV;
        try (ExecutorService es = Executors.newVirtualThreadExecutor()) {
            startV = System.currentTimeMillis();
            eV = getE(es);
            endV = System.currentTimeMillis();
        }

        BigDecimal fact = BigDecimal.ONE;
        BigDecimal e = BigDecimal.valueOf(2.0);
        int n = 2;
        final long startS = System.currentTimeMillis();
        for (int iterations = 0; iterations < MAX_ITERATIONS; iterations++) {
            fact = fact.multiply(new BigDecimal(n));
            n++;
            e = e.add(BigDecimal.ONE.divide(fact, mc));
        }
        final long endS = System.currentTimeMillis();
        final var eS = e;

        System.out.println("Parallel: " + (endP - startP) + "ms\n"
            + "Virtual: " + (endV - startV) + "ms\n"
            + "Serial: " + (endS - startS) + "ms\n"
        );
        if (("" + eP).equals("" + eV) && ("" + eV).equals("" + eS)) {
            System.out.println("OK");
        } else {
            System.out.println(eP);
            System.out.println(eV);
            System.out.println(eS);
        }
    }

    private static BigDecimal getE(ExecutorService es) throws InterruptedException, ExecutionException {
        BigDecimal fact = BigDecimal.ONE;
        BigDecimal e = BigDecimal.valueOf(2.0);
        int n = 2;
        for (int iterations = 0; iterations < MAX_ITERATIONS; iterations++) {
            final var factNow = fact;
            final var bigN = BigDecimal.valueOf(n);
            final var futureFact = es.submit(() -> factNow.multiply(bigN));
            e = e.add(BigDecimal.ONE.divide(fact = futureFact.get(), mc));
            n++;
        }
        return e;
    }

}
