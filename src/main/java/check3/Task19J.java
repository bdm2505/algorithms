package check3;

import java.util.Scanner;

public class Task19J {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        for(int n = 1; n <= 100000; n *= 10) {
            //int s = scanner.nextInt();
            for (int s = 0; s <= 81; s++) {


                int count = 0;
                for (int i = 0; i < n; i++) {
                    if (sum(i) == s) {
                        count++;
                        //System.out.print("(" + i + ")");
                    }
                }
                if (count < 10)
                    System.out.print(" ");
                if (count < 100)
                    System.out.print(" ");
                if (count < 1000)
                    System.out.print(" ");
                if (count < 10000)
                    System.out.print(" ");
                System.out.print(count + ", ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        int [][] arr = new int[10][82];
        for (int i = 0; i < 10; i++) {
            arr[i][0] = 1;
        }


        for (int i = 1; i < 10; i++) {
            int l = Math.min(i * 10 / 2, 82);
            for (int j = 1; j < l; j++) {
                arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
            }
            int k = i * 10;
            if (i % 2 == 1) k--;
            else k -= 2;
            int o = k;
            while (o >= l){
                if (o < 82)
                    arr[i][o] = arr[i][k - o];
                o--;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int count = arr[i][j];
                if (count < 10)
                    System.out.print(" ");
                if (count < 100)
                    System.out.print(" ");
                if (count < 1000)
                    System.out.print(" ");
                if (count < 10000)
                    System.out.print(" ");
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public static int sum(int k){

        return k % 10 + (k / 10) % 10 + (k / 100) % 10 + (k / 1000) % 10 + (k / 10000) % 10 + (k / 100000) % 10
                + (k / 1000000) % 10 + (k / 10000000) % 10 + (k / 100000000) % 10 + (k / 1000000000) % 10;
    }
}
