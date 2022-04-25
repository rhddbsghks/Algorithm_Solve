package d220425;

import java.util.*;
import java.io.*;

/*
백준1938 통나무 옮기기

풀이법 :
	 1. bfs로 세 좌표 모두 E가 되는 점을 찾습니다.
	 2. 방문 관리는 3차원 배열로 관리하고 세 좌표가 모두 방문했을때만 방문한 것으로 간주합니다.
	 3. 8방을 체크하여 모두 비어있다면 회전한 좌표도 큐에 추가합니다.

  */

public class BJ_1938 {

	static class Log {
		int type;
		int[] left;
		int[] center;
		int[] right;

		public Log(int type, int[] left, int[] center, int[] right) {
			super();
			this.type = type;
			this.left = left;
			this.center = center;
			this.right = right;
		}
	}

	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[][] map = new char[n][n];
		int[][] start = new int[3][];
		int idx = 0;
		int startType;

		for (int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				map[i][j] = input[j];

				if (map[i][j] == 'B') {
					start[idx++] = new int[] { i, j };
				}
			}
		}

		startType = start[0][0] == start[1][0] ? 0 : 1; // 0: 가로, 1: 세로
		Queue<Log> q = new ArrayDeque<>();
		q.offer(new Log(startType, start[0], start[1], start[2]));
		int dist = 0;
		boolean[][][] visited = new boolean[2][n][n];

		while (!q.isEmpty()) {
			int size = q.size();
			
			LOOP: while (size-- > 0) {
				Log log = q.poll();
				int type = log.type;
				int lr = log.left[0];    // 왼쪽or위 행좌표
				int lc = log.left[1];    // 왼쪽or위 열좌표
				int cr = log.center[0];  // 중앙 행좌표
				int cc = log.center[1];  // 중앙 행좌표
				int rr = log.right[0];   // 오른쪽or아래 행좌표
				int rc = log.right[1];   // 오른쪽or아래 행좌표

				// 세 점 모두 도착
				if (map[lr][lc] == 'E' && map[cr][cc] == 'E' && map[rr][rc] == 'E') {
					System.out.println(dist);
					return;
				}

				// 세 점 모두 동시에 방문한 적 있음
				if (visited[type][lr][lc] && visited[type][cr][cc] && visited[type][rr][rc]) {
					continue;
				}

				visited[type][lr][lc] = visited[type][cr][cc] = visited[type][rr][rc] = true;

				for (int d = 0; d < 4; d++) {
					int nlr = lr + dr[d];
					int nlc = lc + dc[d];
					int ncr = cr + dr[d];
					int ncc = cc + dc[d];
					int nrr = rr + dr[d];
					int nrc = rc + dc[d];

					if (nlr < 0 || nlc < 0 || ncr < 0 || ncc < 0 || nrr < 0 || nrc < 0 || nlr == n || nlc == n
							|| ncr == n || ncc == n || nrr == n || nrc == n || map[nlr][nlc] == '1'
							|| map[ncr][ncc] == '1' || map[nrr][nrc] == '1') {
						continue;
					}

					q.offer(new Log(type, new int[] { nlr, nlc }, new int[] { ncr, ncc }, new int[] { nrr, nrc }));
				}

				// 회전 가능 여부 체크
				for (int d = 0; d < 8; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];

					if (nr < 0 || nc < 0 || nr == n || nc == n || map[nr][nc] == '1') {
						continue LOOP;
					}
				}

				// 회전한 점 큐에 추가
				int nlr = type == 0 ? lr + dr[5] : lr + dr[6];
				int nlc = type == 0 ? lc + dc[5] : lc + dc[6];
				int nrr = type == 0 ? rr + dr[6] : rr + dr[5];
				int nrc = type == 0 ? rc + dc[6] : rc + dc[5];

				q.offer(new Log(type ^ 1, new int[] { nlr, nlc }, new int[] { cr, cc }, new int[] { nrr, nrc }));
			}

			dist++;
		}
		
		System.out.println(0);
	}

}
