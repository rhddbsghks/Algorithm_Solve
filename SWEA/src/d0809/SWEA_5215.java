package d0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5215 {
	static int[][] burger;
	static int n;
	static int maxCal;
	static boolean[] isSelected;
	static int score = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			maxCal = Integer.parseInt(st.nextToken());
			burger = new int[n][2];
			isSelected = new boolean[n];
			score = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				burger[i][0] = Integer.parseInt(st.nextToken());
				burger[i][1] = Integer.parseInt(st.nextToken());
			}

			recur(0);
			System.out.println("#" + t + " " + score);
		}
	}

	static void recur(int i) {
		int sum = 0;
		int cal = 0;
		if (i == n) {
			for (int a = 0; a < n; a++)
				if (isSelected[a] == true) {
					sum += burger[a][0];
					cal += burger[a][1];
				}

			if (cal <= maxCal && sum > score)
				score = sum;
			return;
		}

		isSelected[i] = true;
		recur(i + 1);
		isSelected[i] = false;
		recur(i + 1);
	}
}
