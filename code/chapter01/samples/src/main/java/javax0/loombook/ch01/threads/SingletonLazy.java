package javax0.loombook.ch01.threads;

// snippet SingletonLazy
public class SingletonLazy {
    private SingletonLazy(){}
    private static SingletonLazy instance;
    public static SingletonLazy getInstance(){
        if( instance == null ){
            instance = new SingletonLazy();
        }
        return instance;
    }
}
// end snippet