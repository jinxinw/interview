/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.pocketgems.oa;

import java.util.HashMap;
import java.util.Map;

public class MaxDifference {

    class UnionFind {

        Map<Integer, Integer> map;
        int count;

        UnionFind(int[] A, int[] B) {
            this.map = new HashMap<>();
            for (int a : A) {
                if (!map.containsKey(a)) {
                    map.put(a, a);
                }
            }
            for (int b : B) {
                if (!map.containsKey(b)) {
                    map.put(b, b);
                }
            }
            count = map.size();
            for (int i = 0; i < A.length; i++) {
                union(A[i], B[i]);
            }
            System.out.println(map);
        }

        void union(int node1, int node2) {
            int father1 = find(node1);
            int father2 = find(node2);
            if (father1 != father2) {
                map.put(father1, father2);
                count--;
            }
        }

        int find(int node) {
            int father = map.get(node);
            while (father != map.get(father)) {
                //System.out.println(father + "-" + map.get(father));
                father = map.get(father);
                //System.out.println(father + "-" + map.get(father));
            }
            //System.out.println("-" + father);
            int temp = -1;
            int fa = node;
            while (fa != map.get(fa)) {
                temp = map.get(fa);
                map.put(fa, father);
                fa = temp;
            }
            return father;
        }

        int findDiff() {
            Map<Integer, int[]> diffMap = new HashMap<>();
            for (int key : map.keySet()) {
                //System.out.println(key);
                int father = find(key);
                if (diffMap.containsKey(father)) {
                    int[] minMax = diffMap.get(father);
                    minMax[0] = Math.min(minMax[0], key);
                    minMax[1] = Math.max(minMax[1], key);
                } else {
                    diffMap.put(father, new int[]{key, key});
                }
            }
            int maxDiff = 0;
            for (int key : diffMap.keySet()) {
                int[] minMax = diffMap.get(key);
                System.out.println(key + " " + minMax[0] + " " + minMax[1]);
                maxDiff = Math.max(minMax[1] - minMax[0], maxDiff);
            }
            return maxDiff;
        }
    }

    public int findMaxDifference(int[] A, int[] B) {
        UnionFind uf = new UnionFind(A, B);
        return uf.findDiff();
    }
    
    public static void main(String[] args) {
        MaxDifference md = new MaxDifference();
        int[] A = {1, 1, 3, 6, 9, 2, 7, 7, 11, 0};
        int[] B = {2, 3, 6, 9, 2, 4, 8, 10, 12, 13};
        System.out.println(md.findMaxDifference(A, B));
    }
}
