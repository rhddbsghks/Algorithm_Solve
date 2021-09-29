package d0929;

import java.util.*;
import java.io.*;

/*
백준 17472 다리 만들기2

풀이법 : 
	 1. BFS를 사용하며, 비트마스킹을 통해 현재 키 보유 상태를 관리합니다. 
	 2. 최대키 개수는 6개이므로 방문관리를 boolean[64][N][M] 3차원 배열로 관리해줍니다.
	 3. map 값이 1인 점을 만나면 BFS를 종료합니다.
	 4. 큐의 모든 좌표를 소진해도 도착하지 못하면 result 값을 -1로 바꿔줍니다.
		 
 */

public class BJ_1194 {
	static int n, m;
	static char map[][];
	static boolean visited[][][];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new boolean[64][n][m];
		int[] start = null;
		result = 0;

		for (int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();

			for (int j = 0; j < m; j++) {
				map[i][j] = input[j];

				if (map[i][j] == '0')
					start = new int[] { i, j, 0, 0 }; // 시작점 저장
			}
		}

		bfs(start);
		System.out.println(result);
	}

	static void bfs(int[] start) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(start);

		while (!q.isEmpty()) {
			int[] pos = q.poll();
			int r = pos[0];
			int c = pos[1];
			int dist = result = pos[2]; // 최단거리 갱신
			int bit = pos[3];

			// 목적지 도착했으면 종료
			if (map[r][c] == '1')
				return;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 탐색 가능 체크
				if (nr < 0 || nr == n || nc < 0 || nc == m || map[nr][nc] == '#' || visited[bit][nr][nc])
					continue;

				// 키 획득
				if ('a' <= map[nr][nc] && map[nr][nc] <= 'f') {
					visited[bit | 1 << (map[nr][nc] - 'a')][nr][nc] = true;
					q.offer(new int[] { nr, nc, dist + 1, bit | 1 << (map[nr][nc] - 'a') });
				}

				// 키 아닐때
				else {
					// 문인데 키가 없으면 건너뛰기
					if ('A' <= map[nr][nc] && map[nr][nc] <= 'F' && (bit & 1 << (map[nr][nc] - 'A')) == 0)
						continue;

					visited[bit][nr][nc] = true;
					q.offer(new int[] { nr, nc, dist + 1, bit });
				}
			}
		}

		result = -1;
	}
}
