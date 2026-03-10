import java.util.*;

public class BFS {

    private static Long exeTime;
    private static int nodesVisited = 0;

    public static List<Node> findPath(Grid grid, Node s, Node goal){

        Long startTime = System.currentTimeMillis();
        int width = grid.getWidth();
        int height = grid.getHeight();

        boolean[][] visited = new boolean[height][width];

        Queue<Node> queue = new LinkedList<>();

        queue.add(s);
        visited[s.getY()][s.getX()] = true;

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            int x = current.getX(); int y = current.getY();

            if (x==goal.getX() && y==goal.getY()) {

                goal.setParent(current);
                break;
            }

            for (Node neighbour : grid.getNeigh(current)) {

                int nx = neighbour.getX();
                int ny = neighbour.getY();

                if (!visited[ny][nx]) {

                    visited[ny][nx] = true;
                    nodesVisited++;
                    if (grid.getCell(nx, ny) != Grid.START && grid.getCell(nx, ny) != Grid.GOAL) {
                        grid.setCell(nx, ny, Grid.VISITED);
                    }

                    queue.add(neighbour);
                }
            }
        }

        List<Node> path = new ArrayList<>();

        Node cur = goal;

        while (cur != null) {
            
            path.add(cur);
            cur = cur.getParent();
        }

        Collections.reverse(path);

        for (Node n : path) {

            int cell = grid.getCell(n.getX(), n.getY());

            if (cell != Grid.START && cell != Grid.GOAL) {
                grid.setCell(n.getX(), n.getY(), Grid.PATH);
            }
        }

        // System.out.println("BFS completed, path length=" + path.size());

        exeTime = System.currentTimeMillis() - startTime;
        return path;
    }

    public static void stats(List<Node> path) {

        System.out.print("BFS  Path Length= " + path.size());
        System.out.print(" | Nodes visited= " + nodesVisited);
        System.out.printf(" | Execution time:  %.4f ms%n", exeTime / 1.0);
    }//END_stats
}