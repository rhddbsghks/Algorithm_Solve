package d0812;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SWEA_1974 {

	static int isProper;
	static String[][] sudoku;
	static ArrayList<String> numList = new ArrayList<String>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("src/d0812/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int i = 1; i <= 9; i++)
			numList.add(Integer.toString(i));

		LOOP: for (int t = 1; t <= testcase; t++) {
			sudoku = new String[9][];
			isProper = 1;
			for (int i = 0; i < 9; i++)
				sudoku[i] = br.readLine().split(" ");

			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++) {
					checkBox(i * 3, j * 3);
					if (isProper == 0) {
						System.out.println("#" + t + " " + isProper);
						continue LOOP;
					}
				}
			if (isProper == 1)
				checkCol();
			if (isProper == 1)
				checkRow();
			System.out.println("#" + t + " " + isProper);
		}
	}

	static void checkRow() {
		ArrayList<String> list;

		for (int i = 0; i < 9; i++) {
			list = new ArrayList<String>();
			for (int j = 0; j < 9; j++)
				list.add(sudoku[j][i]);

			if (!list.containsAll(numList)) {
				isProper = 0;
				return;
			}
		}
	}

	static void checkCol() {
		ArrayList<String> list;
		for (int i = 0; i < 9; i++) {
			list = new ArrayList<String>(Arrays.asList(sudoku[i]));

			if (!list.containsAll(numList)) {
				isProper = 0;
				return;
			}
		}
	}

	static void checkBox(int x, int y) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				list.add(sudoku[x + i][y + j]);

		if (!list.containsAll(numList))
			isProper = 0;
	}
}