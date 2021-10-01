package d1001;

import java.util.*;
import java.io.*;

/*
백준 4485 녹색 옷 입은 애가 젤다지?
Level: G4

풀이법 : 
	2차원 배열을 사용한 다익스트라 알고리즘 적용
 */


public class BJ_4485 {

	static int n, map[][];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testcase = 0;

		while (true) {
			n = Integer.parseInt(br.readLine());
			testcase++;
			if (n == 0)
				break;

			map = new int[n][n]; // 입력 map
			int[][] cost = new int[n][n]; // 비용 배열
			boolean[][] visited = new boolean[n][n]; // 방문 체크
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					cost[i][j] = Integer.MAX_VALUE;
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작 좌표 (0,0) 세팅 밑 우선순위큐 생성
			cost[0][0] = map[0][0];
			visited[0][0] = true;
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[2], o2[2]));
			pq.offer(new int[] { 0, 0, cost[0][0] });

			// Dijkstra 알고리즘
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				int r = cur[0];
				int c = cur[1];
				int w = cur[2];

				if (r == n - 1 && c == n - 1)
					break;

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nc < 0 || nr == n || nc == n)
						continue;

					if (!visited[nr][nc] && cost[nr][nc] > w + map[nr][nc]) {
						visited[nr][nc] = true;
						cost[nr][nc] = w + map[nr][nc];
						pq.offer(new int[] { nr, nc, w + map[nr][nc] });
					}
				}
			}
			sb.append("Problem " + testcase + ": " + cost[n - 1][n - 1] + "\n");
		}
		System.out.println(sb);
	}
}