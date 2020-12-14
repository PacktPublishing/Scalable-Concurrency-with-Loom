package javax0.loombook.ch05;

public class ScopedMtHelloWorld {
    private static final int N = 2;
    static final Scoped<Integer> sv = Scoped.forType(Integer.class);
    private static final Thread[] threads = new Thread[N];
    private static final Thread[] waited = new Thread[N];

    static {
        for (int i = 0; i < N; i++) {
            waited[i] = Thread.builder().virtual().task(() -> {
            }).build();
        }
    }


    private static final class MyScoped implements Runnable {
        final int me;

        private MyScoped(int me) {
            this.me = me;
        }

        @Override
        public void run() {
            Thread.currentThread().setName("hello " + me);
            var __ = sv.bind(me);
            waited[me].start();
            waitForLaterThreadsToStart();
            System.out.println("I am " + me + " and my scope says I am " + sv.get());
            waitForEarlierThreadsToFinish();
            System.out.println("I am " + me + " and my scope still says I am " + sv.get());
        }

        private void waitForEarlierThreadsToFinish() {
            for (int i = 0; i < me; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    throw new RuntimeException("I am " + me + " interrupted in the second loop.");
                }
            }
        }

        private void waitForLaterThreadsToStart() {
            for (int i = me + 1; i < N; i++) {
                try {
                    waited[i].join();
                } catch (InterruptedException e) {
                    throw new RuntimeException("I am " + me + " interrupted int the first loop.");
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            threads[i] = Thread.startVirtualThread(new MyScoped(i));
        }
        for (int i = 0; i < N; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("I am the main and I am interrupted.");
            }
        }
    }

}
