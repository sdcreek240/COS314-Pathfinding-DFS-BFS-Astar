import java.util.*;

//Recursion
public class DFS extends Algo {

    private long sTime = 0;//Start time
    private static Long exeTime;
    private static int nodesVisited = 0;

    private Node start, goal;

    public List<Node> findPath(Grid grid) {

        startTimer();

        this.start = grid.getStart(); this.goal = grid.getGoal();


        Deque<Node> stackOpen = new ArrayDeque<>();
        List<Node> closed = new List<>();
        List<Node> path = new List<>();

        open.push(start);

        if (dfs(grid, stackOpen, closed)) {




            // Collections.reverse(path);

            stopTimer();
            // return path;
        }//END_if

        return Collections.emptyList();
    }//END_findPath


    private static boolean dfs(Grid grid, Deque<Node> open, List<Node> closed) {

        // // out-of-bounds cells are treated as obstacles by getCell()
        // if (visited[y][x] || grid.getCell(x, y)==Grid.OBSTACLE) { return false; }

        boolean bState = false;

        while ( (!open.isEmpty())&&(bState != true) )  {

            Node curr = open.pop();//Take top of stack - DFS

            if (curr.equals(grid.getGoal())) {

                bState = true;
            } else {

                curr.visit();
                List<Node> children = grid.getNeigh(curr);

                for (Node c : children){ 
                    
                    if (!c.getVisisted() && !c.getSeen()){//not visisted and not in open or closed

                        open.push(c);
                        c.see();
                    }//END_if
                    
                }//END_c
            }
        }//END_while

        return bState;//Default
    }//END_dfs

    public static void stats(List<Node> path) {

        System.out.print("DFS  Path Length= " + path.size());
        System.out.print(" | Nodes visited= " + nodesVisited);
        System.out.printf(" | Execution time:  %.4f ms%n", exeTime / 1.0);
    }//END_stats
}
