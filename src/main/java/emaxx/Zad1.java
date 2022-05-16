package emaxx;

public class Zad1 {

    public static void main(String[] args) {
        int[] deads = {3, 4, 6, 7, 7, 7, 7};
        int n = 7;
        int[] zp = {100, 28, 16, 3, 4, 1, 4};



    }
    /*

     */

    static int solution(int[] arr, int m) {
        int result = 0;
        for (int num : arr) {
            int count = m / num;
            result += count;
            m -= count * num;
        }
        return result;
    }
}
