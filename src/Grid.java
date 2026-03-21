import java.util.*;

// 0 - traversable & 1 - impassable objects

public class Grid {

    private int w;//width
    private int h;//height
    private int[][] grid;
    private Node sNode;
    private Node gNode;
    private Random random;//random
    private long seed;

    public static final int EMPTY = 0;
    public static final int OBSTACLE = 1;
    public static final int START = 2;
    public static final int GOAL = 3;
    public static final int PATH = 4;
    public static final int VISITED = 5; // search expansion marker

    public Grid(int w, int h, long seed){

        this.w = w; this.h = h; this.seed = seed;
        this.random = new Random(seed);
        this.grid = new int[h][w];

        //Init empty grid
        for (int i=0; i<h; i++) { Arrays.fill(grid[i], EMPTY); };

        //Set start and goal
        this.sNode = new Node(20, 50); this.gNode = new Node(80, 50);

        //Mark on grid
        grid[sNode.getY()][sNode.getX()] = START; grid[gNode.getY()][gNode.getX()] = GOAL;
    }//END_constr

    public void buildGrid() {
        
        addU();
        addRandNoise(15); // 15% noise

        System.out.println("Grid (" + w + "x" + h + ") with U-Trap and 15% noise");
        System.out.println("Start: " + sNode + ", Goal: " + gNode);
        System.out.println("Seed: " + seed);
        System.out.println();
    }//END_buildGrid

    private void addU() {

        // Back wall: x=50 | from y=30 to y=70
        for (int y = 30; y <= 70; y++) { 
            if (isInBounds(50, y) && !isStartOrGoal(50, y)) {
                grid[y][50] = OBSTACLE;  // grid[row=y][col=50]
            }
        }

        // Top arm: (30,30) to (50,30)
        for (int x = 30; x <= 50; x++) { 
            if (isInBounds(x, 30) && !isStartOrGoal(x, 30)) {
                grid[30][x] = OBSTACLE;  // grid[row=30][col=x]
            }
        }

        // Bottom arm: from (30,70) to (50,70)
        for (int x = 30; x <= 50; x++) { 
            if (isInBounds(x, 70) && !isStartOrGoal(x, 70)) {
                grid[70][x] = OBSTACLE;
            }
        }
    }

    private void addRandNoise(int percentage) {

        int totalCells = w * h;
        int noiseCells = (totalCells*percentage)/100;
        int c = 0; //Counter

        while (c<noiseCells){

            int nX = random.nextInt(w); int nY = random.nextInt(h);

            if (grid[nY][nX]==EMPTY && !isStartOrGoal(nX, nY)) {

                grid[nY][nX] = OBSTACLE; c++;
            }//END_if
        }//END_while
    }//END_addRandNoise

    public boolean isStartOrGoal(int x, int y) {

        return (x==sNode.getX() && y==sNode.getY()) || (x==gNode.getX() && y==gNode.getY());
    }//END_isStartOrGoal

    private boolean isInBounds(int x, int y) {

        return (x>=0 && x<w  &&  y>=0 && y<h);
    }//END_isInBounds

//is grid position available to traverse - maybe change EMPTY to NOT-Obstacle
    private boolean isTrav(int x, int y) {

        return (isInBounds(x, y) &&
                (grid[y][x] == EMPTY || grid[y][x] == GOAL));
    }

    //Getters
    public int getWidth() { return w; }
    public int getHeight() { return h; }
    public Node getStart() { return sNode; }
    public Node getGoal() { return gNode; }

    public List<Node> getNeigh(Node n){

        List<Node> neigh = new ArrayList<>();
        int x = n.getX(); int y = n.getY();

        //Directions array - up | down | left | right
        int[][] directions= {{0,-1}, {0,1}, {-1,0}, {1,0}};

        for (int[] d : directions) {

            int nX = x + d[0]; int nY = y + d[1];

            if (isTrav(nX, nY)) neigh.add(new Node(nX, nY, n));
        }//END_d

        return neigh;
    }//END_getNeigh

    //Utility
    public void display() {
        
        // Print top border with column numbers (every 10 columns)
        System.out.print("    ");
        for (int x=0; x<w; x += 10) System.out.printf("%-10d", x);//END_x

        System.out.println();
        
        // Print grid
        for (int y=0; y<h; y++) {

            // Print row number
            System.out.printf("%3d ", y);
            
            for (int x=0; x<w; x++) {

                switch (grid[y][x]) {

                    case EMPTY:
                        System.out.print(".");
                        break;
                    case OBSTACLE:
                        // red
                        System.out.print("\u001B[31m#\u001B[0m");
                        break;
                    case START:
                        // green
                        System.out.print("\u001B[32mS\u001B[0m");
                        break;
                    case GOAL:
                        // yellow
                        System.out.print("\u001B[33mG\u001B[0m");
                        break;
                    case PATH:
                        System.out.print("|");
                        break;
                    case 5:
                        // visited marker (blue dot)
                        System.out.print("\u001B[34m.\u001B[0m");
                        break;
                }//END_switch
            }//END_x
            System.out.println();
        }//END_y
    }//END_display

    public void printPercentageNoise() {

        int iObstacles = 0; int iEmpty = 0;

        for (int y=0; y<h; y++){

            for (int x=0; x<w; x++){

                if (grid[y][x]==OBSTACLE) iObstacles++;
                else iEmpty++;
            }//END_x

        }//END_y

        float stat = iObstacles/(float)(iObstacles+iEmpty) * 100;

        System.out.print(stat + "%");
    }

    /* utility getters & setters for search algorithms */
    public void resetSearch() {

        for (int y=0; y<h; y++) {

            for (int x=0; x<w; x++) {

                if ((grid[y][x]==PATH) || (grid[y][x]==VISITED)){

                  grid[y][x] = EMPTY;  
                }
            }//END_x
        }//END_y

        grid[sNode.getY()][sNode.getX()] = START;
        grid[gNode.getY()][gNode.getX()] = GOAL;
    }

    public void setCell(int x, int y, int value) {
        if (isInBounds(x, y)) grid[y][x] = value;
    }

    public int getCell(int x, int y) {
        if (isInBounds(x, y)) return grid[y][x];
        else return OBSTACLE;
    }

}//END_Grid