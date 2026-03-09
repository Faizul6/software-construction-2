/*
+--------------------------------------------------+
        |                    GameOfLife                    |
        +--------------------------------------------------+
        | - grid: boolean[][]                              |
        | - rows: int                                      |
        | - cols: int                                      |
        | - generation: int                                |
        +--------------------------------------------------+
        | + GameOfLife(rows: int, cols: int)               |
        | + printGrid(): void                              |
        | + clear(): void                                  |
        | + initRandom(seed: long, aliveProbability: double): void |
        | + initGlider(startRow: int, startCol: int): void |
        | + initBlinker(centerRow: int, centerCol: int): void |
        | + countNeighbors(row: int, col: int): int        |
        | + nextGeneration(): boolean                      |
        | + run(maxGenerations: int, pauseMs: int): void   |
        | - setAlive(r: int, c: int): void                 |
        | - sleep(ms: int): void                           |
        | + main(args: String[]): void                     |
        +--------------------------------------------------+
*/

import java.util.Random;

public class GameOfLife {

    private boolean[][] grid;
    private int rows, cols;
    private int generation;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
        this.generation = 0;
    }

    // ---------- Display ----------
    public void printGrid() {
        System.out.println("Generation: " + generation);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c] ? "█ " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ---------- Initialization patterns ----------
    public void clear() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = false;
            }
        }
        generation = 0;
    }

    public void initRandom(long seed, double aliveProbability) {
        Random rnd = new Random(seed);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = rnd.nextDouble() < aliveProbability;
            }
        }
    }

    // Glider (top-left corner of the pattern at startRow, startCol)
    public void initGlider(int startRow, int startCol) {
        setAlive(startRow, startCol + 1);
        setAlive(startRow + 1, startCol + 2);
        setAlive(startRow + 2, startCol);
        setAlive(startRow + 2, startCol + 1);
        setAlive(startRow + 2, startCol + 2);
    }

    // Blinker (a simple oscillator)
    public void initBlinker(int centerRow, int centerCol) {
        setAlive(centerRow, centerCol - 1);
        setAlive(centerRow, centerCol);
        setAlive(centerRow, centerCol + 1);
    }

    private void setAlive(int r, int c) {
        if (r >= 0 && r < rows && c >= 0 && c < cols) {
            grid[r][c] = true;
        }
    }

    // ---------- Core logic ----------
    public int countNeighbors(int row, int col) {
        int count = 0;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue; // skip itself

                int nr = row + dr;
                int nc = col + dc;

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Computes the next grid and returns true if anything changed
    public boolean nextGeneration() {
        boolean[][] next = new boolean[rows][cols];
        boolean changed = false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int n = countNeighbors(r, c);

                if (grid[r][c]) {
                    // Alive survives with 2 or 3 neighbors
                    next[r][c] = (n == 2 || n == 3);
                } else {
                    // Dead becomes alive with exactly 3 neighbors
                    next[r][c] = (n == 3);
                }

                if (next[r][c] != grid[r][c]) changed = true;
            }
        }

        grid = next;
        generation++;
        return changed;
    }

    // ---------- Run simulation ----------
    public void run(int maxGenerations, int pauseMs) {
        for (int i = 0; i < maxGenerations; i++) {
            printGrid();
            boolean changed = nextGeneration();

            if (!changed) {
                printGrid();
                System.out.println("Stable state reached. Stopping.");
                break;
            }

            sleep(pauseMs);
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ---------- Demo main ----------
    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife(20, 20);

        // Choose ONE initialization:
        gol.clear();
        gol.initGlider(1, 1);
        // gol.initBlinker(10, 10);
        // gol.initRandom(42L, 0.25);

        gol.run(200, 150);
    }
}
