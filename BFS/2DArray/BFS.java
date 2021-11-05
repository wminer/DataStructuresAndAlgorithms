import java.io.*;
// import java.math.*;
// import java.security.*;
// import java.text.*;
import java.util.*;
// import java.util.concurrent.*;
// import java.util.function.*;
// import java.util.regex.*;
// import java.util.stream.*;

public class BFS {

    /*
        Const to store direction offsets to explore in 2D array maze.
    */
    private static final int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

    /*
        Const to store above direction offsets as char.
    */
    private static final char[] cardinalDirections = {'W', 'E', 'S', 'N'};

    /*
        Global variables for solving maze.
    */
    private static int rows, cols;
    private static int[] start = new int[2];
    private static char[][] maze = {{}};

    /*
        Method in LeetCode #1926 to implement 
    */
    private static int nearestExit(char[][] maze, int[] entrance) {
        int row = start[0], col = start[1];
        System.out.println("start is: " + Arrays.toString(start));
        LinkedList<Coordinate> path = new LinkedList<Coordinate>();
        boolean [][] visited = new boolean[maze.length][maze[0].length];
        return BFSIterative(maze, visited, row, col, path);
    }

    /*
        Iterative implementation of BFS with path backtracking included
    */
    private static int BFSIterative(char[][] maze, boolean[][] visited, int row, int col, LinkedList<Coordinate> q) {
        visited[row][col] = true;
        q.add(new Coordinate(row, col, null, ""));
        int stepCount = 0;
        String pathString;
        int newRow, newCol;
        while(!q.isEmpty()) {
            Coordinate curr = q.remove();
            for(int i=0;i<DIRECTIONS.length;i++) {
                newRow = curr.row;
                newCol = curr.col;
                int[] directionPair = DIRECTIONS[i];
                String directionChar = cardinalDirections[i] + "";
                newRow += directionPair[0];
                newCol += directionPair[1];
                if(IsValid(newRow, newCol) && CheckIfExit(maze, newRow, newCol)) {
                    pathString = directionChar;
                    while(curr != null) {
                        pathString = curr.directionTaken + pathString;
                        stepCount++;
                        curr = curr.parent;
                    }
                    System.out.println("Path taken was: " + pathString);
                    return stepCount;
                }
                if(IsValid(newRow, newCol) && !CheckIfWallOrDeadEnd(maze, newRow, newCol) && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    q.add(new Coordinate(newRow, newCol, curr, directionChar));
                }
            } 
        }
        return -1;
    }
    /*
        Method to implement BFS recursively -- TO-DO
    */
    // private static int BFSRecursive(char[][] maze, boolean[][] visited, int row, int col, Queue<Coordinate> path) {
    //     return -1;
    // }

    /*
        Helper method to check if given cell is a wall or dead end.
    */
    private static boolean CheckIfWallOrDeadEnd(char[][] maze, int row, int col) {
        return IsValid(row, col) && maze[row][col] == '+';
    }

    /*
        Helper method to check if given cell is valid.
    */
    private static boolean IsValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /*
        Helper method to check if given cell is an exit from maze.
    */
    private static boolean CheckIfExit(char[][] maze, int row, int col) {
        return IsValid(row, col) && (row != start[0] || col != start[1]) && maze[row][col] == '.' && (row == rows-1 || row == 0 || col == cols-1 || col == 0);
    }

    /*
        Helper method to print maze (stored as 2D Array) as String. 
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
                rows = scanner.nextInt();
                cols = scanner.nextInt();
                maze = new char[rows][cols];
                start[0] = scanner.nextInt();
                start[1] = scanner.nextInt();
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
            printMaze(maze);
            int moveCount = nearestExit(maze, start);
            System.out.println("Nearest exit is " + moveCount + " steps away");
            System.out.println(); 
        } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException occurred when trying to open" + mazeFileName + ".");
                e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        tryMaze("maze1.txt");
        tryMaze("maze2.txt");
        tryMaze("maze3.txt");
        tryMaze("maze4.txt");
        tryMaze("maze5.txt");
        tryMaze("maze6.txt");
        tryMaze("maze7.txt");
    }
}

class Coordinate {
    int row, col;
    Coordinate parent;
    String directionTaken;

    public Coordinate() {
    }

    public Coordinate(int row, int col, Coordinate parent, String directionTaken) {
        this.row = row;
        this.col = col;
        this.parent = parent;
        this.directionTaken = directionTaken;
    }

    public void modifyCoordinate(int rowOffest, int colOffset) {
        this.row += rowOffest;
        this.col += colOffset;
    }

    public void setCoordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "(" + this.row + ", " + this.col + ")";
    }
}
