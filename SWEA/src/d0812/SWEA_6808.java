package d0812;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SWEA_6808 {

	static int win, lose;
	static int[] numbers;
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
			numbers = new int[9];

			// 규영이
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < input.length; i++)
				gyu.add(Integer.parseInt(input[i]));

			// 인영이
			in.addAll(cardList);
			in.removeAll(gyu);
			
			permutation(0, 0);
			System.out.println("#" + t + " " + win + " " + lose);
		}
	}

	static void permutation(int cnt, int flag) {
		if (cnt == 9) {
			play();
			return;
		}

		for (int i = 0; i < 9; i++) {
			if ((flag & 1 << i) != 0)
				continue;

			numbers[cnt] = in.get(i);
			permutation(cnt + 1, flag | (1 << i));
		}
	}

	static void play() {
		int gyuScore = 0;
		int inScore = 0;

		for (int i = 0; i < 9; i++) {
			int gs = gyu.get(i);
			int is = numbers[i];
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
}
