package javax0.loombook.ch01.threads;

import javax0.loombook.Utils;

public class Daemon {
    // snippet Daemon
    public static void main(String[] args) {
        final var t = new Thread(
            () -> {
                for (; ; ) {
                    Utils.sleepIgnoreInterrupt(100);
                }
            });
        t.setDaemon(true);
        t.start();
    }
    // end snippet
}
