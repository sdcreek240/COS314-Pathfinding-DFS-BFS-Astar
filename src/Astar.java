import java.util.*;
import java.lang.Math;

public class Astar {

    private static Long exeTime;
    private static int nodesVisited = 0;

    public static List<Node> findPath(Grid grid, Node start, Node goal) {

        Long startTime = System.currentTimeMillis();
        int w = grid.getWidth(); 
        int h = grid.getHeight();

        // Priority queue ordered by f-score
        PriorityQueue<Node> open = new PriorityQueue<>(
            (n1, n2) -> Integer.compare(n1.getF(), n2.getF())
        );

        // Track visited nodes and their best g-scores
        Map<Node, Integer> gScore = new HashMap<>();
        Map<Node, Integer> fScore = new HashMap<>();
        Set<Node> closed = new HashSet<>();

        // Initialize start node
        gScore.put(start, 0);
        fScore.put(start, h(start, goal));
        start.setF(fScore.get(start));
        open.add(start);

        Node endNode = null; // will hold the final node on success

        while (!open.isEmpty()) {

            Node curr = open.poll();

            // Skip if already processed
            if (closed.contains(curr)) continue;

            int x = curr.getX(); 
            int y = curr.getY();

            // Check if goal reached
            if (x == goal.getX() && y == goal.getY()) {
                // record the current node so we can rebuild the path later
                endNode = curr;
                break;
            }

            // Mark as processed
            closed.add(curr);
            nodesVisited++;
            
            if (grid.getCell(x, y) != Grid.START && grid.getCell(x, y) != Grid.GOAL) {
                grid.setCell(x, y, Grid.VISITED);
            }

            // Explore neighbors
            for (Node neighbor : grid.getNeigh(curr)) {

                if (closed.contains(neighbor)) continue;

                int nX = neighbor.getX(); 
                int nY = neighbor.getY();

                // Skip obstacles
                if (grid.getCell(nX, nY) == Grid.OBSTACLE) continue;

                // Calculate tentative g-score (assuming uniform cost of 1 per step)
                int tentativeG = gScore.get(curr) + 1;

                // If this path is better than any previous path to neighbor
                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {

                    // Update parent and scores
                    neighbor.setParent(curr);
                    gScore.put(neighbor, tentativeG);
                    
                    int hScore = h(neighbor, goal);
                    int fScore_val = tentativeG + hScore;
                    fScore.put(neighbor, fScore_val);
                    neighbor.setF(fScore_val);

                    // re-insert into open to update priority (remove old instance if present)
                    open.remove(neighbor);
                    open.add(neighbor);
                }
            }
        }

        // Reconstruct path
        List<Node> path = new ArrayList<>();
        Node cur = endNode;

        while (cur != null) {
            path.add(cur);
            cur = cur.getParent();
        }

        Collections.reverse(path);

        // Mark path on grid
        for (Node n : path) {
            
            int cell = grid.getCell(n.getX(), n.getY());
            if (cell != Grid.START && cell != Grid.GOAL) {
                grid.setCell(n.getX(), n.getY(), Grid.PATH);
            }
        }

        exeTime = System.currentTimeMillis() - startTime;
        return path;
    }

    // private static int f(Node curr, Node goal) {

    //     return g(curr) + h(curr, goal);
    // }//END_f

    private static int h(Node curr, Node goal){

        //Manhatten distance 
        return Math.abs(curr.getX() - goal.getX()) + Math.abs(curr.getY() - goal.getY());
    }//END_h

    //Calculate path from curretn node to start node
    // private static int g(Node curr) {

    //     int iPath = 0;

    //     while (curr.getParent()!=null){

    //         curr = curr.getParent();
    //         iPath++;
    //     }//END_while

    //     return iPath;
    // }//END_g

    
    public static void stats(List<Node> path) {

        System.out.print("A*  Path Length= " + path.size());
        System.out.print(" | Nodes visited= " + nodesVisited);
        System.out.printf(" | Execution time:  %.4f ms%n", exeTime / 1.0);
    }//END_stats
}//END_Astar