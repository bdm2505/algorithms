package check1;

import java.util.Arrays;
import java.util.Scanner;

public class Z20 {

    private static int[] arr;
    private static boolean[] bools;

    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var n = in.nextInt();
        arr = new int[n];
        bools = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            bools[i] = false;
        }
        Arrays.sort(arr);
        System.out.println(count(true));

    }

    public static int count(boolean leftLamp) {
        if (leftLamp) {
            int l = 0;
            while (bools[l]) l++;
            int r = bools.length - 1;
            while (bools[r]) r--;

            if (r == l) {
              //  System.out.println("move one " + l + " " + Arrays.toString(bools));
                return arr[l];
            }
            bools[l] = true;
            bools[r] = true;
           // System.out.println("move " + l + " " + r + " " + Arrays.toString(bools));
            int dis1 = Math.max(arr[l], arr[r]) + count(false);
            bools[l] = false;
            bools[r] = false;
           // System.out.println("end move " + l + " " + r + " " + Arrays.toString(bools));

            int l2 = l + 1;
            while (bools[l2]) l2++;
            bools[l] = true;
            bools[l2] = true;
            //System.out.println("move2 " + l + " " + l2 + " " + Arrays.toString(bools));
            int dis2 = Math.max(arr[l], arr[l2]) + count(false);
            bools[l] = false;
            bools[l2] = false;
           // System.out.println("end move2 " + l + " " + l2 + " " + Arrays.toString(bools));

            int r2 = r - 1;
            while (bools[r2]) r2--;

            bools[r] = true;
            bools[r2] = true;
            //System.out.println("move3 " + r2 + " " + r + " " + Arrays.toString(bools));
            int dis3 = Math.max(arr[r2], arr[r]) + count(false);
            bools[r] = false;
            bools[r2] = false;
            //System.out.println("end move3 " + r2 + " " + r + " " + Arrays.toString(bools));

//            bools[l] = true;
//            bools[r2] = true;
//            int dis4 = Math.max(arr[r2], arr[r]) + count(false);// 20 25 - 5 10 (10)
//                                                                        // 5 - 10 20 25 (10)

            return Math.min(Math.min(dis1, dis2), dis3);
        } else {

            boolean res = true;
            for (boolean bool : bools) {
                if (!bool) {
                    res = false;
                    break;
                }
            }
            if (res)
                return 0;

            int n = -1;
            for (int i = 0; i < bools.length; i++) {
                if (bools[i]) {
                    n = i;
                    break;
                }
            }
            if (n == -1)
                return 0;
            bools[n] = false;
            //System.out.println("return  " + n + " " + Arrays.toString(bools));
            int result =  arr[n] + count(true);
            bools[n] = true;
            return result;
        }
    }
}
