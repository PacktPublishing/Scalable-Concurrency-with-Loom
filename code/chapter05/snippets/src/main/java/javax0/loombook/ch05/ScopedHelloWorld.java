package javax0.loombook.ch05;

public class ScopedHelloWorld {

    static final Scoped<String> sv = Scoped.forType(String.class);

    void foo() {
        try (var __ = sv.bind("A")) {
            bar();
            baz();
            bar();
        }
    }

    void bar() {
        System.out.println(sv.get());
    }

    void baz() {
        try (var __ = sv.bind("B")) {
            bar();
        }
    }

    public static void main(String[] args) {
        new ScopedHelloWorld().foo();

    }
}
