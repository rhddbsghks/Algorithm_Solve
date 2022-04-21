package d220421;

import java.io.*;
import java.util.*;

/*
백준17142 연구소3

풀이법 :
	 1. 바이러스 m개를 뽑아 bfs
	 2. 이때 남은 빈칸이 없다면 bfs를 종료해줍니다.

시간: 55m
 */


public class BJ_17142 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static List<int[]> virusPos;
	static int n, m, posLen, answer;
	static boolean used[];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		virusPos = new ArrayList<>();
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virusPos.add(new int[] { i, j });
				}
			}
		}

		posLen = virusPos.size();
		used = new boolean[posLen];

		dfs(0, 0);
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}

	// 바이러스 m개 뽑고 answer 갱신
	static void dfs(int cnt, int start) {
		if (cnt == m) {

			Queue<int[]> q = new ArrayDeque<>();
			int[][] tmp = new int[n][];
			boolean[][] visited = new boolean[n][n];
			int time = -1;
			int empty = 0;

			// 시작 바이러스 초기화 및 빈칸 카운트
			for (int i = 0; i < n; i++) {
				tmp[i] = map[i].clone();

				for (int j = 0; j < n; j++) {
					if (map[i][j] == 3) {
						q.offer(new int[] { i, j });
					}
					if (map[i][j] == 0) {
						empty++;
					}
				}
			}

			while (!q.isEmpty()) {
				int size = q.size();

				while (size-- > 0) {
					int[] cur = q.poll();
					int r = cur[0];
					int c = cur[1];

					if (visited[r][c]) {
						continue;
					}

					visited[r][c] = true;
					
					if (tmp[r][c] == 0) {
						empty--;
					}
					
					tmp[r][c] = 3;

					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (nr < 0 || nc < 0 || nr == n || nc == n || tmp[nr][nc] == 1 || visited[nr][nc]) {
							continue;
						}

						q.offer(new int[] { nr, nc });
					}
				}

				time++;
				
				// 모든 칸 전파 완료
				if(empty==0) {
					break;
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (tmp[i][j] == 0) {
						return;
					}
				}
			}

			answer = Integer.min(answer, time);

			return;
		}

		for (int i = start; i < posLen; i++) {

			int[] virus = virusPos.get(i);
			map[virus[0]][virus[1]] = 3;
			dfs(cnt + 1, i + 1);
			map[virus[0]][virus[1]] = 2;

		}
	}
}
