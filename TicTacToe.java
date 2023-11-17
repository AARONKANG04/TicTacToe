/**
 * Aaron Kang
 */

import java.util.Scanner;

public class TicTacToe {

    private static char[][] board = new char[3][3];
    private static int turnNumber = 1;
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            System.out.println();
        }
    }


    private static void makeMove() {
        Scanner scanner = new Scanner(System.in);
        boolean validMove = false;

        while (!validMove) {
            System.out.println(currentPlayer + ", make your move (row,col): ");
            String move = scanner.nextLine();

            // extract row and column numbers
            int row = Integer.parseInt(move.substring(0, 1)) - 1;
            int col = Integer.parseInt(move.substring(2, 3)) - 1;

            // check if the move is within bounds and the spot is empty
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                validMove = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // row win
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // column win
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // diagonal win
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; // diagonal win
        }
        return false;
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            initializeBoard();

            while (true) {
                System.out.println("Round " + turnNumber + ":");
                displayBoard();
                makeMove();

                if (checkWin()) {
                    System.out.println("Player " + currentPlayer + " wins! Congratulations!");
                    displayBoard();
                    break;
                }

                if (turnNumber == 9) {
                    System.out.println("It's a draw!");
                    displayBoard();
                    break;
                }

                // switch to other player for the next turn
                currentPlayer = (currentPlayer == 'X')? 'O': 'X';
                turnNumber++;
            }

            System.out.println("Play again? (Y/N): ");
            String choice = scanner.nextLine().toLowerCase();

            if (!choice.equals("y")) {
                playAgain = false;
            } else {
                turnNumber = 1;
                currentPlayer = 'X';
            }
        }
    }
}

