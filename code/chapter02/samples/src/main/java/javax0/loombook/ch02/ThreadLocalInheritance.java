package javax0.loombook.ch02;
// snippet InheritableThreadLocal_demo
public class ThreadLocalInheritance {
        private static final ThreadLocal local = new InheritableThreadLocal();
    public static void main(String[] args) {
        local.set("abraka dabra");
        Thread.builder().inheritThreadLocals().task( () -> System.out.println("inherited  "+local.get())).build().start();
        Thread.builder().task( () -> System.out.println("no inhetit "+local.get())).build().start();
    }
}
// end snippet
