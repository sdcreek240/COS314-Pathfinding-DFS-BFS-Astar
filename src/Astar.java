import java.util.*;
import java.lang.Math;

public class Astar extends Algo {



    public List<Node> findPath(Grid grid) {

        startTimer();

        Node start = grid.getStart(); Node goal = grid.getGoal();

        // priority queue using the f-score f= g + h
        PriorityQueue<Node> open = new PriorityQueue<>( (n1, n2) -> Integer.compare(n1.getF(), n2.getF()) );

        // Track visited nodes and their best g-scores
        Map<Node, Integer> gScore = new HashMap<>();
        Map<Node, Integer> fScore = new HashMap<>();
        Set<Node> closed = new HashSet<>();

        // Initialize start node
        gScore.put(start, 0); fScore.put(start, h(start, goal));
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
            if ((x == goal.getX()) && (y == goal.getY())) {

                endNode = curr;
                break;
            }//END_if goal

            // Mark as seen
            closed.add(curr); nodesVisited++;
            
            if ((grid.getCell(x, y) != Grid.START) && (grid.getCell(x, y) != Grid.GOAL)) grid.setCell(x, y, Grid.VISITED);

            // Explore neighbors
            for (Node neighbor : grid.getNeigh(curr)) {

                if (closed.contains(neighbor)) continue;

                int nX = neighbor.getX(); 
                int nY = neighbor.getY();

                // Skip obstacles
                if (grid.getCell(nX, nY) == Grid.OBSTACLE) continue;

                // Calculate tentative g-score (assuming uniform cost of 1 per step)
                int tentativeG = gScore.get(curr) + 1;

                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {

                    neighbor.setParent(curr);
                    gScore.put(neighbor, tentativeG);
                    
                    int hScore = h(neighbor, goal);
                    int fScore_val = tentativeG + hScore;
                    fScore.put(neighbor, fScore_val);
                    neighbor.setF(fScore_val);

                    open.remove(neighbor);  open.add(neighbor);
                }//END_if
            }//END_neighbor
        }

        // build path
        List<Node> path = new ArrayList<>();
        Node cur = endNode;

        while (cur != null) {

            path.add(cur);
            cur = cur.getParent();
        }

        Collections.reverse(path);

        // Mark path on grid
        for (Node n : path) {
            
            int c = grid.getCell(n.getX(), n.getY());
            if ((c != Grid.START) && (c != Grid.GOAL)) grid.setCell(n.getX(), n.getY(), Grid.PATH);
        }//END_n

        stopTimer();
        exeTime = getExeTime();

        return path;
    }

    // private static int f(Node curr, Node goal) {

    //     return g(curr) + h(curr, goal);
    // }//END_f

    private int h(Node curr, Node goal){

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

    
    public void stats(List<Node> path) {

        System.out.print("A*  Path Length= " + path.size());
        System.out.print(" | Nodes visited= " + nodesVisited);
        System.out.printf(" | Execution time:  %.4f ms%n", exeTime / 1.0);
    }//END_stats
}//END_Astar