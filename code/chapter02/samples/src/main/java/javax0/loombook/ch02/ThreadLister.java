package javax0.loombook.ch02;

public class ThreadLister {
    public static void main(String[] args) throws NoSuchMethodException {
        var t = Thread.class.getDeclaredMethod("getAllThreads");
    }
}
