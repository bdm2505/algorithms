package check3;

import java.util.ArrayList;
import java.util.Scanner;

public class Task2J {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        int P = scanner.nextInt();

        ArrayList<Integer> [] arr = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            arr[scanner.nextInt() - 1].add(scanner.nextInt() - 1);
        }



    }
}
