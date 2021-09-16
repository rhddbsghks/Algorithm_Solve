package d0917;

import java.util.*;
import java.io.*;

public class SWEA_1767 {

	static int n, max, sum, map[][];
	static List<int[]> coreList;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			coreList = new ArrayList<>();
			max = Integer.MIN_VALUE;
			sum = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i != 0 && j != 0 && i != n - 1 && j != n - 1 && map[i][j] == 1)
						coreList.add(new int[] { i, j });
				}
			}
			connect(0, map, 0, 0);
			System.out.println("#" + t + " " + sum);
		}
	}

	static void connect(int cnt, int[][] arr, int power, int length) {
		if (cnt == coreList.size()) {
			if (max < power) {
				max = power;
				sum = length;
				return;
			}

			if (max == power)
				sum = Integer.min(sum, length);

			return;
		}

		int[] pos = coreList.get(cnt);
		int r = pos[0];
		int c = pos[1];

		for (int d = 0; d < 4; d++) {
			int[][] copy = clone(arr);

			int len = line(copy, r, c, d);
			if (len != 0)
				connect(cnt + 1, copy, power + 1, length + len);
		}
		connect(cnt + 1, arr, power, length);
	}

	static int line(int[][] arr, int i, int j, int d) {
		int length = 0;

		while (true) {
			i += dr[d];
			j += dc[d];

			if (i < 0 || j < 0 || i == n || j == n)
				return length;

			if (arr[i][j] == 0) {
				arr[i][j] = 2;
				length++;
				continue;
			}
			return 0;
		}
	}

	static int[][] clone(int[][] arr) {
		int[][] tmp = new int[n][];

		for (int i = 0; i < n; i++)
			tmp[i] = arr[i].clone();
		return tmp;
	}
}
