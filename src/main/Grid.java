package main;

import java.util.*;

public class Grid {

    private int w;//width
    private int h;//height
    private int[][] grid;
    private Node sNode;
    private Node gNode;
    private Random rand;//random
    private long seed;

    public static final int EMPTY = 0;
    public static final int OBSTACLE = 1;
    public static final int START = 2;
    public static final int GOAL = 3;
    public static final int PATH = 4;

    public Grid(int w, int h, long seed){

        this.w = w; this.h = h; this.seed = seed;
        this.random = new Random(seed);
        this.grid = new int[h][w];

        //Init empty grid
        for (int i=0; i<h; i++) { Arrays.fill(grid[i], EMPTY) };

        //Set start and goal
        this.sNode = new Node(20, 50); this.gNode = new Node(80, 50);

        //Mark on grid
        grid[sNode.getY()][sNode.getX()] = START; grid[gNode.getY()][gNode.getX()] = GOAL;
    }//END_constr

    public void buildGrid() {
        
        addU();
        addRandNoise(15); // 15% noise
    }//END_buildGrid

    private void addU() {

        //Back wall
        for (int y=30; y<=70; y++) { if (isInBounds(50,y) && !isStartOrGoal(50, y)) {grid[y][50] = OBSTACLE; } }//END_y

        //Top arm
        for (int x=30; x<=50; x++){ if (isInBounds(x,30) && !isStartOrGoal(x,30)) {grid[30][x] = OBSTACLE; } }//END_x

        //Bottom arm
        for (int x=30; x<=50; x++){ if (isInBounds(x,70) && !isStartOrGoal(x,70)) {grid[70][x] = OBSTACLE; } }//END_x
    }//END_addU







}//END_Grid