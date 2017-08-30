package company.amazon.oa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class MovieNetwork {

    class Movie {

        int id;
        int rate;
        List<Movie> neighbors;

        Movie(int id, int rate) {
            this.id = id;
            this.rate = rate;
            this.neighbors = new ArrayList<>();
        }
    }

    public List<Integer> find(int k, Movie movie) {
        List<Integer> res = new ArrayList<>();
        if (movie == null || k <= 0) {
            return res;
        }
        PriorityQueue<Movie> heap = new PriorityQueue<>(new Comparator<Movie>() {
            public int compare(Movie m1, Movie m2) {
                return m1.rate - m2.rate;
            }
        });

        Set<Integer> visited = new HashSet<>();
        Queue<Movie> queue = new LinkedList<>();
        queue.offer(movie);
        while (!queue.isEmpty()) {
            Movie cur = queue.poll();
            if (!movie.equals(cur)) {
                if (heap.size() < k) {
                    heap.offer(cur);
                } else if (cur.rate > heap.peek().rate) {
                    heap.poll();
                    heap.offer(cur);
                }
            }
            for (Movie m : cur.neighbors) {
                if (!visited.contains(m.id)) {
                    visited.add(m.id);
                    queue.offer(m);
                }
            }
        }

        while (!heap.isEmpty()) {
            res.add(0, heap.poll().id);
        }
        return res;
    }

    public List<Movie> generateMovieNetwork(int n) {
        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Movie(i, i));
        }
        return list;
    }

    public static void main(String[] args) {
        MovieNetwork mn = new MovieNetwork();
        List<Movie> list = mn.generateMovieNetwork(10);
        list.get(1).neighbors.add(list.get(2));
        list.get(1).neighbors.add(list.get(3));
        list.get(1).neighbors.add(list.get(4));

        list.get(2).neighbors.add(list.get(3));
        list.get(2).neighbors.add(list.get(6));
        list.get(2).neighbors.add(list.get(1));

        list.get(4).neighbors.add(list.get(5));
        list.get(4).neighbors.add(list.get(7));
        list.get(4).neighbors.add(list.get(1));

        list.get(3).neighbors.add(list.get(1));
        list.get(3).neighbors.add(list.get(2));

        list.get(5).neighbors.add(list.get(4));

        list.get(6).neighbors.add(list.get(2));
        list.get(6).neighbors.add(list.get(7));

        list.get(7).neighbors.add(list.get(6));
        list.get(7).neighbors.add(list.get(4));

        list.get(0).neighbors.add(list.get(8));
        list.get(8).neighbors.add(list.get(0));

        System.out.println(mn.find(3, list.get(9)));
    }

}
