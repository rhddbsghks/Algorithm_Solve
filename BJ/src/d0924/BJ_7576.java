package d0924;

import java.util.*;
import java.io.*;

public class BJ_7576 {


	static int n, m, min, map[][];
    static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		ArrayList<int[]> posList = new ArrayList<int[]>();
		min = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 1)
					posList.add(new int[] { i, j, 0 });
			}
		}

		bfs(posList);
		check();
	}

	static void bfs(ArrayList<int[]> posList) {
		Queue<int[]> q = new ArrayDeque<>();

		for (int[] pos : posList)
			q.offer(pos);

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int i = cur[0];
			int j = cur[1];
			min = cur[2];

			for (int d = 0; d < 4; d++) {
				int nr = i + dr[d];
				int nc = j + dc[d];

				if (nr < 0 || nr == n || nc < 0 || nc == m)
					continue;

				if (map[nr][nc] == 0){
			        map[nr][nc] = 1;
                    q.add(new int[] { nr, nc, min + 1 });
                }
					
			}
		}
	}

	static void check() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
		System.out.println(min);
	}
}