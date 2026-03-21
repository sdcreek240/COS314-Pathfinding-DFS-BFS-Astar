import java.util.*;

public class BFS extends Algo{

    private boolean[][] arrB;//For keeping track
    // private Node foundGoal;

    public List<Node> findPath(Grid grid){

        startTimer();

        Node s = grid.getStart(); Node goal = grid.getGoal();//get nodes for search

        List<Node> path = new ArrayList<>();//to return
        Queue<Node> open = new LinkedList<>();//used queue for FIFOs

        open.add(s);

        arrB = new boolean[grid.getHeight()][grid.getWidth()];
        arrB[s.getY()][s.getX()] = true;

        while (!open.isEmpty()) {

            Node current = open.poll();

            int x = current.getX(); int y = current.getY();

            if (x==goal.getX() && y==goal.getY()) {

                // goal.setParent(current);
                Node cur = current;

                //Resconstruct path
                while (cur != null) {

                    path.add(cur);
                    cur = cur.getParent();
                }//END_while

                Collections.reverse(path);
                break;
            }

            for (Node neighbour : grid.getNeigh(current)) {

                int nx = neighbour.getX(); int ny = neighbour.getY();

                if (!arrB[ny][nx]) {

                    arrB[ny][nx] = true;
                    nodesVisited++;
                    if (!grid.isStartOrGoal(nx, ny)) grid.setCell(nx, ny, Grid.VISITED);

                    open.add(neighbour);
                }
            }
        }//END_while

        for (Node n : path) {

            int cell = grid.getCell(n.getX(), n.getY());

            if (cell != Grid.START && cell != Grid.GOAL) {
                grid.setCell(n.getX(), n.getY(), Grid.PATH);
            }
        }

        // System.out.println("BFS completed, path length=" + path.size());

        stopTimer();
        exeTime = getExeTime();

        return path;
    }

    public void stats(List<Node> path) {

        System.out.print("BFS  Path Length= " + path.size());
        System.out.print(" | Nodes visited= " + nodesVisited);
        System.out.printf(" | Execution time:  %.4f ms%n", exeTime / 1.0);
    }//END_stats
}