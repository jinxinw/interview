package company.amazon.oa;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int findMinSteps(int[][] maze, int[] start, int[] end) {
        int steps = 0;
        Queue<int[]> queue = new LinkedList<>();
        int m = maze.length;
        int n = maze[0].length;
        int[][] visited = new int[m][n];
        queue.offer(start);
        visited[start[0]][start[1]] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : directions) {
                int i = cur[0] + d[0];
                int j = cur[1] + d[1];
                if (i >= 0 && i < m && j >= 0 && j < n && visited[i][j] == 0 && maze[i][j] == 0) {
                    visited[i][j] = visited[cur[0]][cur[1]] + 1;
                    if (i == end[0] && j == end[1]) {
                        return visited[i][j];
                    }
                    queue.offer(new int[]{i, j});
                }
            }
        }
        return -1;
    }
    
    public static void main (String[] args) {
        Maze m = new Maze();
        int[][] maze = {{0,0,0,0,0}, {1,1,1,1,0}, {0,0,0,0,0}, {0,1,1,1,1}, {0,0,0,0,0}};
        int res = m.findMinSteps(maze, new int[] {0, 0}, new int[]{4, 4});
        System.out.println(res);
    }

}
