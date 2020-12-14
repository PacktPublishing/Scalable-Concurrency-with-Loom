package javax0.loombook.ch01.threads;

import javax0.loombook.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApply {

    // snippet thenApply
    public static void main(String[] args)
        throws ExecutionException, InterruptedException {
        final var sound =
            CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return "wuff";
        });
        while (!sound.isDone()) {
            Utils.sleepIgnoreInterrupt(100);
        }
        final var says = sound.thenApply((s) -> {
            System.out.println(
                Thread.currentThread().getName());
            return "The dog says " + s;
        });
        while (!says.isDone()) {
            System.out.println("Waiting");
            Utils.sleepIgnoreInterrupt(100);
        }
        System.out.println(says.get());
    }
    // end snippet

}
