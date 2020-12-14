package javax0.loombook.ch01.threads;
// snippet DCLBadBadBad
public class DCLBadBadBad {
    private DCLBadBadBad() {
    }

    private static DCLBadBadBad instance;

    public static DCLBadBadBad getInstance() {
        if (instance == null) {
            synchronized (DCLBadBadBad.class) {
                if (instance == null) {
                    instance = new DCLBadBadBad();
                }
            }
        }
        return instance;
    }
}
//end snippet