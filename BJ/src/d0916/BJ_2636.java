package d0916;

import java.util.*;
import java.io.*;

public class BJ_2636 {

	static int n, m, cheese, map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		int preCheese = -1;
		int time = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1)
					cheese++;
			}
		}

		while (cheese != 0) {
			preCheese = cheese;
			makeOutside();
			melt();
			time++;
		}

		System.out.println(time+"\n"+preCheese);
	}

	static void melt() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 1)
					continue;

				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];

					if (nr < 0 || nc < 0 || nr >= n || nc >= m)
						continue;

					if (map[nr][nc] == 9) {
						map[i][j] = 0;
						cheese--;
						break;
					}
				}
			}
	}

	static void makeOutside() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { 0, 0 });
		boolean[][] visited = new boolean[n][m];

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			if (visited[r][c])
				continue;

			visited[r][c] = true;
			map[r][c] = 9;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nc < 0 || nr >= n || nc >= m || (map[nr][nc] != 0 && map[nr][nc] != 9))
					continue;

				q.offer(new int[] { nr, nc });
			}
		}
	}
}