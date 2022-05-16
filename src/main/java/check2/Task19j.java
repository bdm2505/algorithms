package check2;

import java.math.BigInteger;
import java.util.Scanner;

public class Task19j {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            BigInteger big = new BigInteger(str);
            BigInteger next = big.sqrt().pow(2);
            if(big.equals(next))
                System.out.println(i + 1);
        }
    }
}
