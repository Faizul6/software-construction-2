public class SudokuSolver {

    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    // Example puzzle (0 = empty)
    private final int[][] grid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();

        System.out.println("Initial Sudoku:");
        solver.printGrid();

        if (solver.solveSudoku()) {
            System.out.println("\nSolved Sudoku:");
            solver.printGrid();
        } else {
            System.out.println("\nNo solution exists for this Sudoku.");
        }
    }

    // Backtracking solver
    public boolean solveSudoku() {
        // Find next empty cell
        int[] empty = findEmptyCell();
        if (empty == null) {
            return true; // no empty cells => solved
        }

        int row = empty[0];
        int col = empty[1];

        // Try numbers 1..9
        for (int num = 1; num <= SIZE; num++) {
            if (isValid(row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku()) {
                    return true;
                }

                // Backtrack
                grid[row][col] = EMPTY;
            }
        }

        // No number fits here
        return false;
    }

    // Check row, column, and 3x3 box constraints
    private boolean isValid(int row, int col, int num) {
        // Check row
        for (int x = 0; x < SIZE; x++) {
            if (grid[row][x] == num) return false;
        }

        // Check column
        for (int x = 0; x < SIZE; x++) {
            if (grid[x][col] == num) return false;
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) return false;
            }
        }

        return true;
    }

    // Find the first empty cell (returns {row, col} or null if none)
    private int[] findEmptyCell() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == EMPTY) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // Print Sudoku nicely
    private void printGrid() {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(grid[i][j] == 0 ? ". " : (grid[i][j] + " "));
            }
            System.out.println();
        }
    }
}

