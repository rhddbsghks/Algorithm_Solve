package d0930;

import java.util.*;
import java.io.*;

public class SWEA_1953 {

	static int n, m, map[][];
	static int[] dr = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] pipe = { {}, { 1, 1, 1, 1 }, { 1, 0, 1, 0 }, { 0, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 },
			{ 0, 0, 1, 1 }, { 1, 0, 0, 1 } };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./src/d0930/input1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			map = new int[n][m];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			int[] start = new int[] { startR, startC, map[startR][startC], 1 };
			Queue<int[]> q = new ArrayDeque<int[]>();
			boolean[][] visited = new boolean[n][m];
			q.offer(start);
			visited[startR][startC] = true;
			int result = 0;
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				int type = cur[2];
				int curTime = cur[3];

				if (curTime > time)
					break;

				result++;
				for (int d = 0; d < 4; d++) {
					if (pipe[type][d] != 1)
						continue;

					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nc < 0 || nr == n || nc == m)
						continue;

					if (map[nr][nc] != 0 && !visited[nr][nc] && pipe[map[nr][nc]][(d + 2) % 4] == 1) {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc, map[nr][nc], curTime + 1 });
					}
				}
			}

			System.out.println("#" + " " + t + " " + result);
		}
	}
}