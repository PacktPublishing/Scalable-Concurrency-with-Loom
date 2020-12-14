package javax0.loombook.ch01.threads;

import javax0.loombook.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApply2 {

    // snippet thenApply2
    public static void main(String[] args)
        throws ExecutionException, InterruptedException {
        final var sound =
            CompletableFuture.supplyAsync(() -> {
            Utils.sleepIgnoreInterrupt(1000);
            System.out.println(
                Thread.currentThread().getName());
            return "wuff";
        });
        final var says = sound.thenApply((s) -> {
            System.out.println(
                Thread.currentThread().getName());
            return "The dog says " + s;
        });
        while (!says.isDone()) {
            System.out.println("Waiting");
            Utils.sleepIgnoreInterrupt(300);
        }
        System.out.println(says.get());
    }
    // end snippet
}
