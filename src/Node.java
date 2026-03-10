public class Node {

    private int x;
    private int y;
    private Node parent;

    //Constr no parent
    public Node(int x, int y){

        this.x = x; this.y =y;
        this.parent = null;
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
    }//END_copyConstr



    //Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public Node getParent() { return parent; }


    //mutators
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setParent(Node p) { this.parent = p; }

    //
    @Override
    public String toString() { return "("+x+","+y+")"; }
}//END_Node