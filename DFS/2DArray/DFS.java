import java.io.*;
// import java.math.*;
// import java.security.*;
// import java.text.*;
import java.util.*;
// import java.util.concurrent.*;
// import java.util.function.*;
// import java.util.regex.*;
// import java.util.stream.*;

/*
    2D Array example:
    Maze dimensions: 20 7
    Maze exit: 0 18
    Maze (w/ row & col indices):
     01234567890123456789
    0xxxxxxxxxxxxxxxxxx x
    1x     x       xxxx x
    2x xxxxx xxxxx   xx x
    3x xxxxx xxxxxxx xx x
    4x x @        xx xx x
    5x xxxxxxxxxx xx    x
    6xxxxxxxxxxxxxxxxxxxx

*/

public class DFS {

    /*
        Const to store direction offsets to explore in 2D array maze.
    */
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    /*
        Const to store above direction offsets as char.
    */
    private static final char[] cardinalDirections = { 'E', 'S', 'W', 'N'};

    /*
        Global variables for solving maze.
    */
    private static int rows, cols;
    private static int[] start = new int[2];
    private static int[] exit = new int[2];
    private static char[][] maze = {{}};

    /*
        Helper method to solve maze given a starting point (as int[2]) and exit (as int[2]). 
    */
    private static Stack<Character> solveMaze(char[][] maze, int[] start, int[] exit) {
        int row = start[0], col = start[1];
        int rowExit = exit[0], colExit = exit[1];
        Stack<Character> path = new Stack<Character>();
        boolean [][] visited = new boolean[maze.length][maze[0].length];
        for(int i=0;i<DIRECTIONS.length;i++) {
            int newRow = row, newCol = col;
            int[] directionPair = DIRECTIONS[i];
            char directionChar = cardinalDirections[i];
            newRow += directionPair[0];
            newCol += directionPair[1];
            if(DFSRecursive(maze, newRow, newCol, rowExit, colExit, path, directionChar, visited)) {
                maze[row][col] = '*';
            } else if(maze[row][col] == ' ') {
                maze[row][col] = '+';
            }
        }
        return path;
    }

    /*
        Helper method which uses recursion to explore all paths from given point until exit found, or all paths explored fully. 
    */
    private static boolean DFSRecursive(char[][] maze, int row, int col, int rowExit, int colExit, Stack<Character> path, Character direction, boolean[][] visited) {
        // Check if value is in maze, a wall, a dead end, or already visited
        if(CheckIfWallOrDeadEnd(maze, row, col, direction) || visited[row][col]) {
            return false;
        }
        // If code reaches here, not yet explored, so mark cell as visited
        visited[row][col] = true;
        // Check to see if we have reached exit: if yes, recurse back to start and mark/store successful path
        if(row == rowExit && col == colExit) {
            maze[row][col] = '*';
            path.push(direction);
            return true;
        }
        // Explore all four directions from current cell
        for(int i=0;i<DIRECTIONS.length;i++) {
            int newRow = row, newCol = col;
            int[] directionPair = DIRECTIONS[i];
            char directionChar = cardinalDirections[i];
            newRow += directionPair[0];
            newCol += directionPair[1];
            // check to see if this direction is part of path to exit: if yes, mark/store as part of exit path 
            if(DFSRecursive(maze, newRow, newCol, rowExit, colExit, path, directionChar, visited)) {
                maze[row][col] = '*';
                path.push(directionChar);
                return true;
            }
            // If direction is not part of path to exit and cell was empty, mark as attempted path
            else if(maze[row][col] == ' ') {
                maze[row][col] = '+';
            }
        }
        return false;
    }

    /*
        Helper method to see if given cell is part of wall or at a dead end. 
        Infers if cell is at a dead end based off direction traveled to reach cell.
    */
    private static boolean CheckIfWallOrDeadEnd(char[][] maze, int row, int col, Character direction) {
        if(maze[row][col] == 'x') {
            return true;
        }
        if(direction == 'E' && maze[row][col-1] == 'x' && maze[row+1][col] == 'x' && maze[row-1][col] == 'x') {
            return true;
        }
        else if(direction == 'W' && maze[row][col+1] == 'x' && maze[row+1][col] == 'x' && maze[row-1][col] == 'x') {
            return true;
        }
        else if(direction == 'S' && maze[row][col+1] == 'x' && maze[row][col-1] == 'x' && maze[row-1][col] == 'x') {
            return true;
        }
        else if(direction == 'N' && maze[row][col+1] == 'x' && maze[row][col-1] == 'x' && maze[row+1][col] == 'x') {
            return true;
        }
        return false;
    }

    /*
        Helper method to print path (stored as Stack) as String. 
    */
    private static void printPath(Stack<Character> path) {
        String pathString = "";
        while(!path.isEmpty()) {
            char direction = path.pop();
            pathString += direction;
        }
        System.out.println("Path: " + pathString);
    }

    /*
        Helper method to maze (stored as 2D Array) as String. 
    */
    private static void printMaze(char[][] maze) {
        for(int i=0;i<maze.length;i++) {
            for(int j=0;j<maze[i].length;j++) {
                if(j == maze[i].length-1) {
                    System.out.print(maze[i][j]);
                } else {
                    System.out.print(maze[i][j] + ", ");
                }
            }
            System.out.println();
        }
    }

    /*
        Helper method to take in maze as text file and attempt to solve it.
    */
    private static void tryMaze(String mazeFileName) {
        try {
            File mazeFile = new File(mazeFileName);
            Scanner scanner = new Scanner(mazeFile);
            while(scanner.hasNextLine()) {
                cols = scanner.nextInt();
                rows = scanner.nextInt();
                maze = new char[rows][cols];
                start[0] = scanner.nextInt();
                start[1] = scanner.nextInt();
                exit[0] = scanner.nextInt();
                exit[1] = scanner.nextInt();
                scanner.nextLine();
                for(int row=0;row<rows;row++) {
                    String mazeRow = scanner.nextLine();
                    for(int col=0;col<cols;col++) {
                        maze[row][col] = mazeRow.charAt((col));
                    }
                }
            }
            scanner.close();
            
            System.out.println("\nAttempting to solve maze in: " + mazeFileName);
            Stack<Character> exitPath = solveMaze(maze, start, exit);
            printPath(exitPath);
            printMaze(maze);
            System.out.println();  
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException occurred.");
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        tryMaze("maze1.txt");
        /*
            Example output from above call:

            Attempting to solve maze in: maze1.txt
            Path: EENNNEEEEEESEESSSEEENNNNNN
            x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, *, x
            x,  ,  ,  ,  ,  , x, *, *, *, *, *, *, *, x, x, x, x, *, x
            x,  , x, x, x, x, x, *, x, x, x, x, x, *, *, *, x, x, *, x
            x,  , x, x, x, x, x, *, x, x, x, x, x, x, x, *, x, x, *, x
            x,  , x, +, *, *, *, *, +, +, +, +, +, x, x, *, x, x, *, x
            x,  , x, x, x, x, x, x, x, x, x, x, +, x, x, *, *, *, *, x
            x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x, x
        */
    }
}

