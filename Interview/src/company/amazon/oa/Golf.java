package company.amazon.oa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Golf {

    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    class Node {

        int i;
        int j;
        int h;

        Node(int i, int j, int h) {
            this.i = i;
            this.j = j;
            this.h = h;
        }
    }

    public int flatFields(List<List<Integer>> fields) {
        PriorityQueue<Node> heap = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n1.h - n2.h;
            }
        });
        int m = fields.size();
        int n = fields.get(0).size();
        for (int i = 0; i < fields.size(); i++) {
            for (int j = 0; j < fields.get(i).size(); j++) {
                if (fields.get(i).get(j) > 1) {
                    heap.offer(new Node(i, j, fields.get(i).get(j)));
                }
            }
        }
        Node start = new Node(0, 0, fields.get(0).get(0));
        int sum = 0;
        while (!heap.isEmpty()) {
            Node end = heap.poll();
            int step = bfs(start, end, fields);
            if (step == -1) {
                return -1;
            }
            sum = sum + step + end.h;
            start = end;
        }

        /*List<Node> startNodes = new ArrayList<>();
         if (fields.get(0).get(0) == 1) {
         startNodes.add(new Node(0, 0, fields.get(0).get(0)));
         }
         if (fields.get(0).get(n - 1) == 1) {
         startNodes.add(new Node(0, n - 1, fields.get(0).get(n - 1)));
         }
        
         if (fields.get(m - 1).get(0) == 1) {
         startNodes.add(new Node(m - 1, 0, fields.get(m - 1).get(0)));
         }
        
         if (fields.get(m - 1).get(n - 1) == 1) {
         startNodes.add(new Node(m - 1, n - 1, fields.get(m - 1).get(n - 1)));
         }*/
        return sum;
    }

    private int bfs(Node start, Node end, List<List<Integer>> fields) {
        int m = fields.size();
        int n = fields.get(0).size();
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(start);
        visited[start.i][start.j] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                for (int[] d : directions) {
                    int x = cur.i + d[0];
                    int y = cur.j + d[1];

                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        int h = fields.get(x).get(y);
                        if (h == end.h) {
                            fields.get(x).set(y, 1);
                            return step + 1;
                        }
                        if (h == 1) {
                            visited[x][y] = true;
                            queue.offer(new Node(x, y, h));
                        }
                    }
                }
                step++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Golf g = new Golf();
        List<List<Integer>> fields = new ArrayList<>();
        fields.add(new ArrayList<>());
        fields.add(new ArrayList<>());
        fields.get(0).add(1);
        fields.get(0).add(1);
        fields.get(0).add(0);
        fields.get(0).add(2);
        fields.get(1).add(3);
        fields.get(1).add(1);
        fields.get(1).add(1);
        fields.get(1).add(1);
        int res = g.flatFields(fields);
        System.out.println(res);
    }
}
