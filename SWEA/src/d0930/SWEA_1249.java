package d0930;

import java.io.*;
import java.util.*;

public class SWEA_1249 {

	static int n, map[][], graph[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./src/d0930/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				String[] input = br.readLine().split("");
				for (int j = 0; j < n; j++)
					map[i][j] = Integer.parseInt(input[j]);
			}

			System.out.println("#" + t + " " + dijkstra(0, 0));
		}
	}

	static int dijkstra(int startR, int startC) {
		boolean[][] visited = new boolean[n][n];
		int[][] minTime = new int[n][n];

		for (int i = 0; i < n; i++)
			Arrays.fill(minTime[i], Integer.MAX_VALUE);

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[2], o2[2]));

		minTime[startR][startC] = 0;
		pq.offer(new int[] { startR, startC, minTime[startR][startC] });

		int r = 0, c = 0, minCost = 0, nr, nc;
		while (true) {

			// step1
			int[] cur = pq.poll();
			r = cur[0];
			c = cur[1];
			minCost = cur[2];

			if (visited[r][c])
				continue;

			visited[r][c] = true;
			if (r == n - 1 && c == n - 1)
				return minCost;

			// step2 : step1에서 선택된 정점을 경유지로해서 인접정점 따져보기
			for (int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];

				if (nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]
						&& minTime[nr][nc] > minCost + map[nr][nc]) {
					minTime[nr][nc] = minCost + map[nr][nc];
					pq.offer(new int[] { nr, nc, minTime[nr][nc] });
				}
			}
		}

	}
}
