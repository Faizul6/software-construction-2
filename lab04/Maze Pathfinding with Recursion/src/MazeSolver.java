public class MazeSolver {

    static char[][] maze = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '$', '#'}
    };

    public static void main(String[] args) {
        if (findPath(1, 1)) {
            System.out.println("Path found:");
        } else {
            System.out.println("No path found.");
        }
        printMaze();
    }

    static boolean findPath(int row, int col) {

        // 1. Outside maze
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return false;
        }

        // 2. Wall or already visited
        if (maze[row][col] == '#' || maze[row][col] == '*') {
            return false;
        }

        // 3. Goal found
        if (maze[row][col] == '$') {
            return true;
        }

        // 4. Mark current cell as part of path
        maze[row][col] = '*';

        // 5. Try all 4 directions
        if (findPath(row, col + 1)) return true; // right
        if (findPath(row + 1, col)) return true; // down
        if (findPath(row, col - 1)) return true; // left
        if (findPath(row - 1, col)) return true; // up

        // 6. Dead end → backtrack
        maze[row][col] = ' ';
        return false;
    }

    static void printMaze() {
        for (char[] row : maze) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
