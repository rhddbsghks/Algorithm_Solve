package d0804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_2805 {
	static int[][] map;
	static int nr, nc;

	static int[] dr = { 1, 1, -1, -1 }; // 우하, 좌하, 좌상, 우상
	static int[] dc = { 1, -1, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/d0804/input2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		int n;

		String str;
		for (int t = 1; t < testcase + 1; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				str = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = Character.getNumericValue(str.charAt(j));
				}
			}

			nr = 0;
			nc = n / 2;

			System.out.println("#" + t + " " + getProfit(n / 2));
		}
	}

	static int getProfit(int n) {
		int profit = 0;
		int leftEnd = nc - n;
		int rightEnd = nc + n;

		if (n == 0)
			return map[nr][nc];

		for (int i = 0; i < 4; i++) {
			nr += dr[i];
			nc += dc[i];

			while (true) {
				profit += map[nr][nc];
				nr += dr[i];
				nc += dc[i];

				if (!(leftEnd <= nr && nr <= rightEnd && leftEnd <= nc && nc <= rightEnd)) {
					nr -= dr[i];
					nc -= dc[i];
					break;
				}
			}
		}
		nr += 1;

		return profit + getProfit(n - 1);
	}
}
