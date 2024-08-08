package com.Game;

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
	static int i, j;
	static char[][] gameBoard;

	public TicTacToe() {
		gameBoard = new char[3][3];
		initBoard();

	}

	void initBoard() {
		for (i = 0; i < gameBoard.length; i++) {
			for (j = 0; j < gameBoard[i].length; j++) {
				gameBoard[i][j] = ' ';

			}
		}
	}

	static void dispBoard() {

		System.out.println("-------------");
		for (i = 0; i < gameBoard.length; i++) {
			System.out.print("| ");
			for (j = 0; j < gameBoard[i].length; j++) {
				System.out.print(gameBoard[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");

		}
	}

	static void placeMark(int row, int col, char mark) {
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			gameBoard[row][col] = mark;
		} else {
			System.out.println("invalid game");
		}
	}

	static boolean checkColWin() {
		for (j = 0; j <= 2; j++) {
			if (gameBoard[0][j] != ' ' && gameBoard[0][j] == gameBoard[1][j] && gameBoard[1][j] == gameBoard[2][j]) {
				return true;
			}
		}
		return false;

	}

	static boolean checkRowWin() {
		for (i = 0; i <= 2; i++) {
			if (gameBoard[i][0] != ' ' && gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2]) {
				return true;
			}
		}
		return false;

	}

	static boolean checkDiagonal() {

		if (gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]
				|| gameBoard[0][0] != ' ' && gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]) {
			return true;
		}

		return false;

	}

	static boolean checkDraw() {
		for (i = 0; i <= 2; i++) {
			for (j = 0; j <= 2; j++) {
				if (gameBoard[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

}

abstract class Player {
	String name;
	char mark;
	Scanner scan = new Scanner(System.in);

	abstract void makeMove();

	boolean isValidMove(int row, int col) {
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			if (TicTacToe.gameBoard[row][col] == ' ') {
				return true;
			}
		}
		return false;
	}
}

class HumanPlayer extends Player {

	public HumanPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {

		int row;
		int col;
		do {
			System.out.println("Enter the row and col");
			row = scan.nextInt(3);
			col = scan.nextInt(3);
		} while (!isValidMove(row, col));
		TicTacToe.placeMark(row, col, mark);

	}

}

class AIPlayer extends Player {

	public AIPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		int row;
		int col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		} while (!isValidMove(row, col));
		TicTacToe.placeMark(row, col, mark);

	}

}

public class LaunchGame {

	public static void main(String[] args) {

		TicTacToe t = new TicTacToe();
		// Display a welcoming message
		System.out.println("************************************");
        System.out.println("*      WELCOME TO TIC-TAC-TOE      *");
        System.out.println("*        Where Strategy Meets      *");
        System.out.println("*            Fun and Excitement!   *");
        System.out.println("************************************");
        System.out.println();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the your name:");
		HumanPlayer p1 = new HumanPlayer(scan.next(), 's');
		// Ask the user to choose between playing against a friend or an AI
		System.out.println("Enter your opposition (FR for Friend/AI for Artificial Intelligence): ");
        String choice = scan.next().toUpperCase();

        Player p2;
        switch (choice) {
            case "FR":
                // Initialize the second player as a human player
                p2 = new HumanPlayer("babu", 'b');
                break;
            case "AI":
                // Initialize the second player as an AI player
                p2 = new AIPlayer("S-AI", '#');
                break;
            default:
                System.out.println("Invalid choice. Exiting the game.");
                return;
        }

		Player cp;
		cp = p1;

		while (true) {
			System.out.println(cp.name + " now your turn play smart");
			cp.makeMove();
			TicTacToe.dispBoard();
			if (TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonal()) {
				System.out.println(cp.name + " has won. cheers!!!");
				break;
			} else if (TicTacToe.checkDraw()) {
				System.out.println("Game is Drawn!!!");
				break;
			} else {
				if (cp == p1) {
					cp = p2;
				} else {
					cp = p1;
				}
			}
		}

	}

}
