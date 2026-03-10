import java.util.*;
import java.lang.Math;

public class Astar {

    private static Long exeTime;
    private static int nodesVisited = 0;

    public static List<Node> findPath(Grid grid, Node start, Node goal) {

        Long startTime = System.currentTimeMillis();
        int w=grid.getWidth(); int h=grid.getHeight();

        boolean[][] visited = new boolean[h][w];

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(n -> f(n, goal)));

        open.add(start);

        while (!open.isEmpty()){

            Node curr = open.poll();

            int x = curr.getX(); int y = curr.getY();

            if (visited[y][x]) continue;

            visited[y][x] = true;
            nodesVisited++;
            if (grid.getCell(x, y)!=Grid.START && grid.getCell(x, y)!=Grid.GOAL) grid.setCell(x,y,Grid.VISITED);

            if ( (x==goal.getX())&&(y==goal.getY())){

                goal.setParent(curr);
                break;
            }

            for (Node n:grid.getNeigh(curr)){

                int nX = n.getX(); int nY = n.getY();

                if (!visited[nY][nX]) {

                    n.setParent(curr);   
                    open.add(n);
                }
            }//END_n - neighbours
        }//END_while

        List<Node> path = new ArrayList<>();
        Node cur = goal;

        while (cur!=null){

            path.add(cur);
            cur = cur.getParent();
        }//END_while

        Collections.reverse(path);

        for (Node n:path){

            int cell = grid.getCell(n.getX(), n.getY());

            if ( (cell != grid.START )&&(cell !=Grid.GOAL) ) grid.setCell(n.getX(), n.getY(), Grid.PATH);
        }//END_n - node

        // System.out.println("A* done, pad length= "+path.size());

        exeTime = System.currentTimeMillis() - startTime;

        return path;
    }

    private static int f(Node curr, Node goal) {

        return g(curr) + h(curr, goal);
    }//END_f

    private static int h(Node curr, Node goal){

        //Manhatten distance 
        return Math.abs(curr.getX() - goal.getX()) + Math.abs(curr.getY() - goal.getY());
    }//END_h

    //Calculate path from curretn node to start node
    private static int g(Node curr) {

        int iPath = 0;

        while (curr.getParent()!=null){

            curr = curr.getParent();
            iPath++;
        }//END_while

        return iPath;
    }//END_g

    
    public static void stats(List<Node> path) {

        System.out.print("A*  Path Length= " + path.size());
        System.out.print(" | Nodes visited= " + nodesVisited);
        System.out.printf(" | Execution time:  %.4f ms%n", exeTime / 1.0);
    }//END_stats
}//END_Astar