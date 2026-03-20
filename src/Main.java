import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("   | Wall-E Pathfinding Simulator |");
        System.out.println("   | DFS  |  BFS  | A* Comparison |");
        System.out.println("========================================\n");

        System.out.print("Enter seed value for noise: ");
        long seed = 0;

        try {

            seed = Long.parseLong(scanner.nextLine().trim());
            System.out.println("Seed: "+seed);
        } catch (NumberFormatException e) {

            System.out.println("Invalid input. Using current time as seed.");
            seed = System.currentTimeMillis();
            System.out.println("Seed: " + seed);
        }

        //Create initial grid with seed value
        Grid grid = new Grid(100, 100, seed);
        grid.buildGrid();

        //Display initial Grid
        System.out.println("Initial grid================================================================================");
        grid.display();
        System.out.println("END_Initial grid================================================================================");

        //DFS
        List<Node> DFSpath = DFS.findPath(grid);

        grid.display();grid.resetSearch();

        // //BFS
        // List<Node> BFSpath = BFS.findPath(grid, grid.getStart(), grid.getGoal());

        // grid.display();grid.resetSearch();

        // //Astar
        // List<Node> Apath = Astar.findPath(grid, grid.getStart(), grid.getGoal());

        // grid.display();grid.resetSearch();

        DFS.stats(DFSpath);
        // BFS.stats(BFSpath);
        // Astar.stats(Apath);
    }
}//END_main