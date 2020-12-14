package javax0.loombook;

public class Utils {
    public static void sleepIgnoreInterrupt(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
