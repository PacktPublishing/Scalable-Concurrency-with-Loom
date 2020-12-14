package javax0.loombook.ch01.threads;

// snippet Singleton
public class Singleton {
    private Singleton(){}
    private static final Singleton
        INSTANCE = new Singleton();
    public static Singleton getInstance(){
        return INSTANCE;
    }
}
// end singleton