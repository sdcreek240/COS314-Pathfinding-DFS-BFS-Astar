import java.util.*;

//Recursion
public class DFS extends Algo {

    private Node start, goal, foundGoal;

    private Deque<Node> stackOpen = new ArrayDeque<>();
    private List<Node> path;

    private boolean[][] arrB;

    public List<Node> findPath(Grid grid) {

        startTimer();

        this.start = grid.getStart(); this.goal = grid.getGoal();
        this.start.see();

        arrB = new boolean[grid.getHeight()][grid.getWidth()];
        arrB[start.getY()][start.getX()] = true;

        stackOpen.push(start);

        if (dfs(grid)) {

            this.path = new ArrayList<>();

            Node n = foundGoal;

            while (n != null){

                path.add(n);
                n = n.getParent();
                if (n!=null && !grid.isStartOrGoal(n.getX(), n.getY())) grid.setCell(n.getX(), n.getY(), grid.PATH);
            }//END_while

            Collections.reverse(path);

            stopTimer();
            exeTime = getExeTime();
            return path;
        }//END_if

        return Collections.emptyList();
    }//END_findPath

    private boolean dfs(Grid grid) {

        // // out-of-bounds cells are treated as obstacles by getCell()
        // if (visited[y][x] || grid.getCell(x, y)==Grid.OBSTACLE) { return false; }

        while ( (!stackOpen.isEmpty()) )  {

            Node curr = stackOpen.pop();//Take top of stack - DFS
            curr.visit(); nodesVisited++;
            if (!grid.isStartOrGoal(curr.getX(), curr.getY())) grid.setCell(curr.getX(), curr.getY(), grid.VISITED);

            if (curr.equals(grid.getGoal())){

                foundGoal = curr;
                return true;
            }  

            List<Node> children = grid.getNeigh(curr);

            for (Node c : children){ 
                
                if (!arrB[c.getY()][c.getX()]){//not visisted and not in open or closed

                    c.setParent(curr);
                    arrB[c.getY()][c.getX()] = true;
                    stackOpen.push(c);
                }//END_if                    
            }//END_c
        }//END_while

        return false;//Default
    }//END_dfs

    @Override
    public void stats(List<Node> path) {

        System.out.print("DFS  Path Length= " + path.size());
        System.out.print(" | Nodes visited= " + nodesVisited);
        System.out.printf(" | Execution time:  %.4f ms%n", exeTime / 1.0);
    }//END_stats
}
