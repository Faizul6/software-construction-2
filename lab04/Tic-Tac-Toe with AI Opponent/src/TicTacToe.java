import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private final char[][] board = new char[3][3];

    private static final char EMPTY = ' ';
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public TicTacToe() {
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
        System.out.println();
    }

    public boolean checkWin(char p) {
        // rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true;
        }
        // cols
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == p && board[1][j] == p && board[2][j] == p) return true;
        }
        // diagonals
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p) return true;
        if (board[0][2] == p && board[1][1] == p && board[2][0] == p) return true;

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    private boolean isValidMove(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3 && board[r][c] == EMPTY;
    }

    private void playerMove() {
        while (true) {
            System.out.print("Your move (row col): ");
            if (!scanner.hasNextInt()) {
                scanner.next(); // throw away bad token
                System.out.println("Please enter numbers like: 1 2");
                continue;
            }
            int r = scanner.nextInt();

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter two numbers like: 1 2");
                continue;
            }
            int c = scanner.nextInt();

            if (isValidMove(r, c)) {
                board[r][c] = PLAYER;
                return;
            }
            System.out.println("Invalid move. Choose an empty cell inside 0..2.");
        }
    }

    // Simple AI: (1) win if possible, (2) block player, (3) center, (4) corners, (5) random
    public void computerMove() {
        // 1) Try to win
        int[] winMove = findWinningMove(COMPUTER);
        if (winMove != null) {
            board[winMove[0]][winMove[1]] = COMPUTER;
            return;
        }

        // 2) Block player
        int[] blockMove = findWinningMove(PLAYER);
        if (blockMove != null) {
            board[blockMove[0]][blockMove[1]] = COMPUTER;
            return;
        }

        // 3) Take center
        if (board[1][1] == EMPTY) {
            board[1][1] = COMPUTER;
            return;
        }

        // 4) Take a corner if possible
        int[][] corners = {{0,0},{0,2},{2,0},{2,2}};
        List<int[]> availableCorners = new ArrayList<>();
        for (int[] c : corners) {
            if (board[c[0]][c[1]] == EMPTY) availableCorners.add(c);
        }
        if (!availableCorners.isEmpty()) {
            int[] choice = availableCorners.get(random.nextInt(availableCorners.size()));
            board[choice[0]][choice[1]] = COMPUTER;
            return;
        }

        // 5) Random available cell
        List<int[]> emptyCells = getEmptyCells();
        if (!emptyCells.isEmpty()) {
            int[] choice = emptyCells.get(random.nextInt(emptyCells.size()));
            board[choice[0]][choice[1]] = COMPUTER;
        }
    }

    private int[] findWinningMove(char p) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = p;                 // pretend
                    boolean wins = checkWin(p);
                    board[i][j] = EMPTY;             // undo
                    if (wins) return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private List<int[]> getEmptyCells() {
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) cells.add(new int[]{i, j});
            }
        }
        return cells;
    }

    public void play() {
        System.out.println("Tic-Tac-Toe (You = X, Computer = O)");
        while (true) {
            resetBoard();

            boolean playerTurn = true;

            while (true) {
                printBoard();

                if (playerTurn) {
                    playerMove();
                    if (checkWin(PLAYER)) {
                        printBoard();
                        System.out.println("You win!");
                        break;
                    }
                } else {
                    computerMove();
                    if (checkWin(COMPUTER)) {
                        printBoard();
                        System.out.println("Computer wins!");
                        break;
                    }
                }

                if (isBoardFull()) {
                    printBoard();
                    System.out.println("Draw!");
                    break;
                }

                playerTurn = !playerTurn;
            }

            System.out.print("Play again? (y/n): ");
            String ans = scanner.next().trim().toLowerCase();
            if (!ans.equals("y")) {
                System.out.println("Bye!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe().play();
    }
}
