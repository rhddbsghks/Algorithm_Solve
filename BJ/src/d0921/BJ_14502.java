package d0921;

import java.util.*;
import java.io.*;

public class BJ_14502 {
	static int n, m, map[][], copy[][];
	static List<Integer> virus;
	static List<Integer> empty;
	static int[] location;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		copy = new int[n][m];
		virus = new ArrayList<Integer>();
		empty = new ArrayList<Integer>();
		location = new int[3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0)
					empty.add(i * m + j);

				else if (map[i][j] == 2)
					virus.add(i * m + j);
			}
		}

		setWall(0, 0);
		System.out.println(max);
	}

	static void setWall(int cnt, int start) {
		if (cnt == 3) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					copy[i][j] = map[i][j];

			for (int i = 0; i < 3; i++) {
				int r = location[i] / m;
				int c = location[i] % m;
				copy[r][c] = 1;
			}

			for (int v : virus)
				dfs(v / m, v % m);

			checkSafe();
			return;
		}

		for (int i = start; i < empty.size(); i++) {
			location[cnt] = empty.get(i);
			setWall(cnt + 1, i + 1);
		}
	}

	static void dfs(int r, int c) {
		copy[r][c] = 2;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nc < 0 || nr == n || nc == m || copy[nr][nc] != 0)
				continue;

			dfs(nr, nc);
		}
	}

	static void checkSafe() {
		int cnt = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (copy[i][j] == 0)
					cnt++;
		max = Integer.max(max, cnt);
	}
}