package check1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class RG {
    public static void main(String[] args) throws FileNotFoundException {
        var wr = new PrintWriter("z2_9.txt");
        var r = new Random();
        var t = 6;
        wr.println(t);
        for (int i = 0; i < t; i++) {
            wr.println(r.nextInt(1000000));
        }
        wr.close();
    }
}
