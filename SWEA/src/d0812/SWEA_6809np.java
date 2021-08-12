package d0812;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SWEA_6809np {

	static int win, lose;
	static List<Integer> gyu;
	static List<Integer> in;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/d0812/s_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		Integer[] card = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
		List<Integer> cardList = new ArrayList<Integer>(Arrays.asList(card));

		for (int t = 1; t <= testcase; t++) {
			gyu = new ArrayList<Integer>();
			in = new ArrayList<Integer>();
			win = 0;
			lose = 0;

			// 규영이
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < input.length; i++)
				gyu.add(Integer.parseInt(input[i]));

			// 인영이
			in.addAll(cardList);
			in.removeAll(gyu);
			Collections.sort(in);

			do {
				play();
			} while (np(in));

			System.out.println("#" + t + " " + win + " " + lose);
		}
	}

	static void play() {
		int gyuScore = 0;
		int inScore = 0;

		for (int i = 0; i < 9; i++) {
			int gs = gyu.get(i);
			int is = in.get(i);
			if (gs > is)
				gyuScore += gs + is;

			else
				inScore += gs + is;
		}

		if (gyuScore > inScore)
			win++;

		else if (gyuScore < inScore)
			lose++;
	}

	static boolean np(List<Integer> list) {
		int n = list.size();

		int i = n - 1;

		while (i > 0 && list.get(i - 1) > list.get(i))
			i--;

		if (i == 0)
			return false;

		int j = n - 1;

		while (list.get(i - 1) > list.get(j))
			j--;

		swap(list, i - 1, j);

		int k = (n - i) / 2;

		for (int x = 0; x < k; x++)
			swap(list, i + x, n - 1 - x);

		return true;
	}

	static void swap(List<Integer> list, int i, int j) {
		int tmp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, tmp);
	}
}
