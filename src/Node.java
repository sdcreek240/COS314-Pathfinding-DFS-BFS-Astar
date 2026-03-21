import java.util.Objects;

public class Node {

    private int x;
    private int y;
    private Node parent;

    //Visited for DFS
    private boolean visited;
    private boolean seen;

    //f-score fo rA*
    private int f;

    //Constr no parent
    public Node(int x, int y){

        this.x = x; this.y =y;
        this.parent = null;
        this.visited = false;
        this.seen = false;
    }//END_Constr-noParent

    //constr with paretn
    public Node(int x, int y, Node p){

        this(x, y);
        this.parent = p;
    }//END_const-with parent

    //Copy constr
    public Node(Node node){

        this.x = node.getX(); this.y = node.getY();
        this.parent = node.getParent();
        this.visited = node.getVisited();
        this.seen = node.getSeen();
    }//END_copyConstr



    //Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public Node getParent() { return parent; }

    public boolean getVisited() { return visited; }
    public boolean getSeen() { return seen; }
    public int getF() { return f; }


    //mutators
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setParent(Node p) { this.parent = p; }

    public void setVisited(boolean b){ this.visited = b; }
    public void see() { this.seen = true; }
    public void visit() { setVisited(true); }
    public void setF(int f) { this.f = f; }

    //
    @Override
    public String toString() { return "("+x+","+y+")"; }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() { return Objects.hash(x, y); }
}//END_Node