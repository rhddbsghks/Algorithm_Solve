package d0813;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17135 {

	static int[][] map;
	static int[][] originMap;
	static int n;
	static int m;
	static int d;
	static int enemy = 0;
	static int[][] archer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		originMap = new int[n][m];

		int[] comb = new int[m];
		comb[m - 1] = 1;
		comb[m - 2] = 1;
		comb[m - 3] = 1;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				originMap[i][j] = Integer.parseInt(st.nextToken());
		}

		do {
			for (int i = 0; i < n; i++)
				map[i] = originMap[i].clone();

			archer = new int[3][];
			int idx = 0;
			for (int i = 0; i < m; i++)
				if (comb[i] == 1)
					archer[idx++] = new int[] { n, i };

			play();

		} while (np(comb));
		System.out.println(enemy);
	}

	static void play() {
		int kill = 0;
		while (checkEnemy()) {
			for (int arch = 0; arch < 3; arch++) {
				int leftx = 0;
				int lefty = Integer.MAX_VALUE;
				int md = Integer.MAX_VALUE;
				boolean shoot = false;

				for (int i = n - 1; i > n - 1 - d; i--) {
					if (i < 0)
						break;
					Queue<int[]> enemyList = new ArrayDeque<int[]>();
					for (int j = 0; j < m; j++)
						enemyList.offer(new int[] { i, j });

					while (!enemyList.isEmpty()) {
						int[] e = enemyList.poll();
						int shootDis = Math.abs(e[0] - archer[arch][0]) + Math.abs(e[1] - archer[arch][1]);

						if (map[e[0]][e[1]] >= 1 && shootDis <= d && shootDis <= md) {
							if (md == shootDis) {
								if (e[1] < lefty) {
									leftx = e[0];
									lefty = e[1];
								}
							} else {
								leftx = e[0];
								lefty = e[1];
							}
							md = shootDis;
							shoot = true;
						}
					}
				}
				if (shoot)
					map[leftx][lefty]++;
			}

			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					if (map[i][j] > 1) {
						map[i][j] = 0;
						kill++;
					}

			moveEnemy();
		}

		if (kill > enemy)
			enemy = kill;
	}

	static void moveEnemy() {
		for (int idx = n - 1; idx > 0; idx--)
			map[idx] = map[idx - 1];
//
//		map[0] = new int[m];
	}

	static boolean checkEnemy() {
		for (int[] ene : map)
			for (int e : ene) {
				if (e == 1)
					return true;
			}
		return false;
	}

	static boolean np(int[] comb) {
		int n = comb.length;

		int i = n - 1;

		while (i > 0 && comb[i - 1] >= comb[i])
			i--;

		if (i == 0)
			return false;

		int j = n - 1;

		while (comb[i - 1] >= comb[j])
			j--;

		int tmp = comb[i - 1];
		comb[i - 1] = comb[j];
		comb[j] = tmp;

		Arrays.sort(comb, i, n);

		return true;
	}
}
