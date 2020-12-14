package javax0.loombook.ch01.threads;

public class OutJavaVersion {
    // snippet JavaVersion
    public static void main(String[] args) {
        final var v = Runtime.version();
        System.out.print("Java build "+v.feature()+"-"+v.pre().orElse("[]")+"+"+v.build().orElse(0)+"-"+v.optional().orElse("[]]"));
    }
    // end snippet
}
