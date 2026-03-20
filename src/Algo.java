import java.util.*;

public abstract class Algo {

    protected Long exeTime;
    protected int nodesVisited;
    protected String algoName;

    public abstract List<Node> findPath(Grid grid, Node start, Node goal);
    public abstract void stats(List<Node> path);//Display statistics

    protected void startTimer() { exeTime = System.currentTimeMillis(); }//Set current Time for start
    protected void stopTimer() { if (exeTime != null) exeTime -= System.currentTimeMillis(); } //Stop timer 

    //Getters
    public Long getExeTime() { return exeTime; }
}//END_Algo