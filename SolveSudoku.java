import java.util.Scanner;

public class SolveSudoku {
    public static boolean solveSudoku(int[][] board) {
        int[] emptyLocation = findEmptyLocation(board);
        int row = emptyLocation[0];
        int col = emptyLocation[1];

        if (row == -1 && col == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidLocation(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true;
                }

                board[row][col] = 0;
            }
        }

        return false;
    }

    public static int[] findEmptyLocation(int[][] board) {
        int[] location = {-1, -1};
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    location[0] = row;
                    location[1] = col;
                    return location;
                }
            }
        }
        return location;
    }

    public static boolean isValidLocation(int[][] board, int row, int col, int num) {
        return !usedInRow(board, row, num) &&
               !usedInColumn(board, col, num) &&
               !usedInBox(board, row - row % 3, col - col % 3, num);
    }

    public static boolean usedInRow(int[][] board, int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean usedInColumn(int[][] board, int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean usedInBox(int[][] board, int startRow, int startCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[9][9];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Sudoku puzzle (9x9 grid):");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        if (solveSudoku(board)) {
            System.out.println("Sudoku Solved:");
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }
}
