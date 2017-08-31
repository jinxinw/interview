package company.amazon.oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Books {

    class UndirectedGraphNode {

        int label;
        ArrayList<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }

        List<List<Integer>> result = new ArrayList<>();
        // log visited node before push into queue
        Set<UndirectedGraphNode> visited = new HashSet<>();
        for (UndirectedGraphNode node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            List<Integer> row = bfs(node, visited);
            result.add(row);
        }

        return result;
    }

    private List<Integer> bfs(UndirectedGraphNode node,
            Set<UndirectedGraphNode> visited) {

        List<Integer> row = new ArrayList<>();
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.offer(node);
        visited.add(node);

        while (!q.isEmpty()) {
            UndirectedGraphNode qNode = q.poll();
            row.add(qNode.label);
            for (UndirectedGraphNode neighbor : qNode.neighbors) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                q.offer(neighbor);
                visited.add(neighbor);
            }
        }

        Collections.sort(row);
        return row;
    }
}
