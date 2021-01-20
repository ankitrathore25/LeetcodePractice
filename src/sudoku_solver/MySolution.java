package sudoku_solver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MySolution {

	private char[][] result = new char[9][9];
	private int flag = 0;

	public void solveSudoku(char[][] board) {

		Set[] h = new Set[9]; // set to keep all numbers in horizontal row
		Set[] v = new Set[9]; // set to keep all numbers in vertical column
		Set[] m = new Set[9]; // set to keep all numbers in 3x3 matrix
		List<Integer> emptySpace = new ArrayList<>(); // keeps all the empty space to traverse on

		for (int i = 0; i < 9; i++) {
			h[i] = new HashSet<>();
			v[i] = new HashSet<>();
			m[i] = new HashSet<>();
		}

		/* first we will extract all the available values in intial board */
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					int w = i * 9 + j % 9;
					emptySpace.add(w);
				} else {
					int k = (i / 3) * 3 + (j / 3) % 3;
					h[i].add(board[i][j]);
					v[j].add(board[i][j]);
					m[k].add(board[i][j]);
				}
			}
		}
		// backtrack the board and try to fill each empty space with avaialable possible
		// value
		backtrack(board, h, v, m, emptySpace, 0);

		/* copy final result in the board */
		for (int w = 0; w < 9; w++)
			board[w] = result[w].clone();

	}

	public void backtrack(char[][] curr, Set[] h, Set[] v, Set[] m, List<Integer> emptySpace, int ind) {

		if (flag == 1) // this check will avoid unnecessary calls after we've got the unique result
			return;

		// emptySpace contains all the position of empty space ranging from 0-80
		// inclusive
		int index = emptySpace.get(ind);
		int i = index / 9; // convert the index into i, j for 9X9 matrix;
		int j = index % 9;
		int k = (i / 3) * 3 + (j / 3) % 3;

		// we don't have to traverse on the values which are already taken either in
		// same column or in same row or in same box
		Set<Character> notAvailable = new HashSet<>();
		notAvailable.addAll(h[i]);
		notAvailable.addAll(v[j]);
		notAvailable.addAll(m[k]);

		// if there is no available value then return as it is invalid board
		if (notAvailable.size() == 9) {
			return;
		}

		// if we reach the last empty space then we need to return from there and there
		// will be only 1 possible value for that last empty space
		if (ind == emptySpace.size() - 1) {
			for (char num = '1'; num <= '9'; num++) {
				if (!notAvailable.contains(num)) {
					curr[i][j] = num;
					for (int w = 0; w < 9; w++) {
						result[w] = curr[w].clone();
					}
					flag = 1; // set the flag to avoid any further calls as solution is unique
					return;
				}
			}
			return;
		}

		/* Iterate over all the available possible value for given empty space */
		for (char num = '1'; num <= '9'; num++) {
			if (!notAvailable.contains(num)) {
				curr[i][j] = num;
				h[i].add(num);
				v[j].add(num);
				m[k].add(num);

				backtrack(curr, h, v, m, emptySpace, ind + 1);

				h[i].remove(num);
				v[j].remove(num);
				m[k].remove(num);
			}
		}

		return;
	}
}
