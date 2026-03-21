import java.util.*;

public abstract class Algo {

    protected Long exeTime, startTime;
    protected int nodesVisited;
    protected String algoName;

    public abstract List<Node> findPath(Grid grid);
    public abstract void stats(List<Node> path);//Display statistics

    protected void startTimer() { startTime = System.currentTimeMillis(); }//Set current Time for start
    protected void stopTimer() { if (startTime != null) exeTime = System.currentTimeMillis() - startTime; } //Stop timer 



    //Getters
    public Long getExeTime() { return exeTime; }




}//END_Algo