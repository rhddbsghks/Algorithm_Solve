package d220427;

import java.util.*;
import java.io.*;

public class BJ_23289 {

	static int r, c, k, w, answer, map[][];
	static boolean[][][] wall;
	static List<int[]> checkPos, heater;
	static List<int[][]> spreadMap;
	static int[][] dr = { {}, { 0, -1, 1 }, { 0, -1, 1 }, { -1, -1, -1 }, { 1, 1, 1 } };
	static int[][] dc = { {}, { 1, 1, 1 }, { -1, -1, -1 }, { 0, -1, 1 }, { 0, -1, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		wall = new boolean[2][][];
		wall[0] = new boolean[r - 1][c]; // 가로벽
		wall[1] = new boolean[r][c - 1]; // 세로벽
		checkPos = new ArrayList<>();
		heater = new ArrayList<>();
		spreadMap = new ArrayList<>();
		answer = 0;

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < c; j++) {
				int val = Integer.parseInt(st.nextToken());

				if (val == 0) {
					continue;
				}

				if (val == 5) {
					checkPos.add(new int[] { i, j });
					continue;
				}

				heater.add(new int[] { i, j, val });
			}
		}

		w = Integer.parseInt(br.readLine());

		for (int i = 0; i < w; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());

			x = t == 0 ? x - 1 : x;

			wall[t][x][y] = true;
		}

		for (int[] h : heater) {
			spreadMap.add(makeSpread(h));
		}


		while (true) {
			wind();

			adjust();

			decrease();

			if (++answer > 100) {
				System.out.println(101);
				return;
			}

			if (check())
				break;
		}

		System.out.println(answer);
	}

	static int[][] makeSpread(int[] heater) {
		int[][] spread = new int[r][c];
		boolean[][] visited = new boolean[r][c];

		dfs(heater[0] + dr[heater[2]][0], heater[1] + dc[heater[2]][0], heater[2], 5, spread, visited);

		return spread;
	}

	static void dfs(int i, int j, int dir, int cnt, int[][] spread, boolean[][] visited) {

		if (visited[i][j] || cnt == 0) {
			return;
		}

		spread[i][j] = cnt;
		visited[i][j] = true;
		int wallIdx = dir <= 2 ? 1 : 0;
		int wallR = dir == 3 ? i - 1 : i;
		int wallC = dir == 2 ? j - 1 : j;

		for (int d = 0; d < 3; d++) {
			int nr = i + dr[dir][d];
			int nc = j + dc[dir][d];

			if (nr < 0 || nc < 0 || nr == r || nc == c) {
				continue;
			}

			if (d == 0 && !wall[wallIdx][wallR][wallC]) {
				dfs(nr, nc, dir, cnt - 1, spread, visited);
			}

			if (d > 0) {
				// 오 왼
				if (dir <= 2) {
					int tR = d == 1 ? wallR - 1 : wallR;
					int tC = wallC;

					if (!wall[wallIdx ^ 1][tR][tC] && !wall[wallIdx][wallR + dr[dir][d]][wallC]) {
						dfs(nr, nc, dir, cnt - 1, spread, visited);
					}
				}

				// 위 아래
				else {
					int tR = wallR;
					int tC = d == 1 ? wallC - 1 : wallC;

					if (!wall[wallIdx ^ 1][tR][tC] && !wall[wallIdx][wallR][wallC + dc[dir][d]]) {
						dfs(nr, nc, dir, cnt - 1, spread, visited);
					}
				}
			}

		}
	}

	static void wind() {
		for (int[][] s : spreadMap) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					map[i][j] += s[i][j];
				}
			}
		}
	}

	static void adjust() {
		int[][] tmp = new int[r][c];
		boolean[][] visited = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			int j = i % 2 == 0 ? 0 : 1;
			for (; j < c; j += 2) {

				visited[i][j] = true;

				for (int d = 1; d <= 4; d++) {
					int nr = i + dr[d][0];
					int nc = j + dc[d][0];

					if (nr < 0 || nc < 0 || nr == r || nc == c) {
						continue;
					}

					int wallIdx = d <= 2 ? 1 : 0;
					int wallR = d == 3 ? i - 1 : i;
					int wallC = d == 2 ? j - 1 : j;

					if (wall[wallIdx][wallR][wallC]) {
						continue;
					}

					int dif = (map[i][j] - map[nr][nc]) / 4;

					tmp[i][j] -= dif;
					tmp[nr][nc] += dif;
				}
			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] += tmp[i][j];
			}
		}
	}

	static void decrease() {
		for (int i = 0; i < r; i++) {
			map[i][0] = Integer.max(0, map[i][0] - 1);
			map[i][c - 1] = Integer.max(0, map[i][c - 1] - 1);
		}

		for (int j = 1; j < c - 1; j++) {
			map[0][j] = Integer.max(0, map[0][j] - 1);
			map[r - 1][j] = Integer.max(0, map[r - 1][j]-1);
		}
	}

	static boolean check() {
		for (int pos[] : checkPos) {
			if (map[pos[0]][pos[1]] < k) {
				return false;
			}
		}

		return true;
	}

}
