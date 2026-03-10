import java.util.*;

//Recursion
public class DFS {

    private static Long exeTime;
    private static int nodesVisited = 0;

    public static List<Node> findPath(Grid grid) {

        long startTime = System.currentTimeMillis(); // Start time

        int h = grid.getHeight();
        int w = grid.getWidth();

        boolean[][] visited = new boolean[h][w];

        Node start = grid.getStart();
        Node goal = grid.getGoal();

        if (dfs(grid, start, goal, visited)) {
            // reconstruct path via parent pointers

            List<Node> path = new ArrayList<>();
            Node cur = goal;
            while (cur != null) {
                path.add(cur);
                cur = cur.getParent();
            }

            for (Node n : path) {

                int cell = grid.getCell(n.getX(), n.getY());

                if (cell != Grid.START && cell != Grid.GOAL) {
                    grid.setCell(n.getX(), n.getY(), Grid.PATH);
                }
            }

            Collections.reverse(path);

            exeTime = System.currentTimeMillis() - startTime;

            return path;
        }
        return Collections.emptyList();
    }

//Recursive function
    private static boolean dfs(Grid grid, Node c, Node goal, boolean[][] visited) {

        int x = c.getX(); int y = c.getY();

        // out-of-bounds cells are treated as obstacles by getCell()
        if (visited[y][x] || grid.getCell(x, y)==Grid.OBSTACLE) { return false; }

        visited[y][x] = true;
        nodesVisited++;
        if (grid.getCell(x, y) != Grid.START && grid.getCell(x,y)!=Grid.GOAL) grid.setCell(x, y, Grid.VISITED);

        if (x == goal.getX() && y == goal.getY()) { 
            
            goal.setParent(c);
            return true; 
        }

        for (Node n : grid.getNeigh(c)) {

            n.setParent(c);
            if (dfs(grid, n, goal, visited)) {
                return true;
            }
        }//END_n

        return false;
    }//END_dfs

    public static void stats(List<Node> path) {

        System.out.print("DFS  Path Length= " + path.size());
        System.out.print(" | Nodes visited= " + nodesVisited);
        System.out.printf(" | Execution time:  %.4f ms%n", exeTime / 1.0);
    }//END_stats
}
