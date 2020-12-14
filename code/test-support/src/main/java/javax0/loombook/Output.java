package javax0.loombook;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * This utility class redirects the output so that we can grab the output as a snippet.
 */
public class Output implements AutoCloseable {

    final private PrintStream old;
    private final PrintStream out;
    private final File outputFile;

    public static Output file(String name) throws IOException {
        return new Output(name);
    }

    public static Output file() throws IOException {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        final String name = walker.getCallerClass().getCanonicalName();
        return new Output(name + ".txt");
    }

    private Output(String outputFileName) throws IOException {
        this.old = System.out;
        String s = "";
        int i = 1000;
        while (!new File(s + "ROOT").exists()) {
            s = s + "../";
            if (i-- < 0)
                throw new RuntimeException("ROOT placeholder file should be in the root directory of the book project");
        }
        outputFile = new File(s + "generated/output/" + outputFileName);
        out = new PrintStream(outputFile, StandardCharsets.UTF_8);
        System.setOut(out);
        out.println("snippet " + outputFileName.replaceAll("\\.txt$","").replaceAll("\\.","_"));
    }

    public File get(){
        return outputFile;
    }

    @Override
    public void close() throws Exception {
        out.println("end snippet");
        out.close();
        System.setOut(old);
    }
}
