package timus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task1742 {

    ArrayList<Integer> [] graf;
    ArrayList<Integer> [] rgraf;

    ArrayList<Integer> order;

    boolean [] used;

    void dfs(int node){
        used[node] = true;
        for (int i = 0; i < graf[node].size(); i++) {
            if(!used[graf[node].get(i)])
                dfs(graf[node].get(i));
        }
        order.add(node);
    }

    boolean [] rused;
    void rdfs(int node) {
        rused[node] = true;
        for (int i = 0; i < rgraf[node].size(); i++) {
            if(!rused[rgraf[node].get(i)])
                rdfs(rgraf[node].get(i));
        }
    }

    void linedfs(int node){
        used[node] = true;
        for (int i = 0; i < graf[node].size(); i++) {
            if(!used[graf[node].get(i)])
                linedfs(graf[node].get(i));
        }
    }


    public Task1742() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        graf = new ArrayList[n];
        used = new boolean[n];
        rused = new boolean[n];
        rgraf = new ArrayList[n];
        order = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graf[i] = new ArrayList<>(1);
            rgraf[i] = new ArrayList<>(4);
        }
        for (int i = 0; i < n; i++) {
            int k = in.nextInt() - 1;
            if(k != i){
                graf[i].add(k);
                rgraf[k].add(i);
            }
        }
        //System.out.println("graf=" + Arrays.toString(graf));
        //System.out.println("rgraf=" + Arrays.toString(rgraf));
        Arrays.fill(used, false);
        Arrays.fill(rused, false);
        int min = 0;
        for (int i = 0; i < n; i++) {
            if(!used[i]){
                dfs(i);
            }
        }
        //System.out.println(order);
        //System.out.println(rgraf[3]);

        int max = 0;
        for (int i = 0; i < order.size(); i++) {
            int node = order.get(order.size() - i - 1);
            if(!rused[node]){
                max++;
                rdfs(node);
            }
        }

        Arrays.fill(used, false);
        for (int i = 0; i < n; i++) {
            int node = order.get(n - i - 1);
            if(!used[node]){
                min++;
                linedfs(node);
            }
        }


        System.out.println(min + " " + max);
    }

    public static void main(String[] args) {
        new Task1742();
    }
}
