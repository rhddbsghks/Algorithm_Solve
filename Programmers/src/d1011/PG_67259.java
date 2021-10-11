package d1011;

import java.util.*;

/*
프로그래머스 67259 경주로 건설

풀이법 : 
	1. BFS로 해결하였고 각 지점마다 4개의 방향에서 들어올 수 있기 때문에 int[][][4]로 방문체크를 해줍니다.
	2. 다음 좌표를 탐색할 때 visited에 저장되어 있는 비용보다 현재 비용이 더 적을 경우만 진행합니다. 
	3. 거리는 더 멀지만 적은 비용이 존재할 수 있으므로 끝점 도착시 종료하지 않고 최솟값만 갱신해줍니다. 
	
 */

public class PG_67259 {

	static int[] dr = { 1, -1, 0, 0 }; // 하, 상, 우, 좌
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }));
		System.out.println(solution(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 0, 0, 1, 0, 0, 0, 1, 0 }, { 0, 1, 0, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0 } }));

		System.out.println(solution(new int[][] { { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 0, 1 }, { 1, 0, 0, 0 } }));
		System.out.println(solution(new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 0 }, { 0, 0, 1, 0, 0, 0 },
				{ 1, 0, 0, 1, 0, 1 }, { 0, 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0 } }));
	}

	static int solution(int[][] board) {
		int answer = Integer.MAX_VALUE;
		int n = board.length;
		// visited 선언 후 MAX로 초기화
		int[][][] visited = new int[n][n][4];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);

		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { 0, 0, -500, -1 }); // 시작점에서 두 방향 모두 코너로 간주하기 위해 비용 -500으로 설정

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int cost = cur[2];
			int prevD = cur[3]; // 직전에 탐색한 방향

			// 끝점 도달하면 최솟값 갱신
			if (r == n - 1 && c == n - 1) {
				answer = Integer.min(answer, cost);
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nc < 0 || nr >= n || nc >= n || board[nr][nc] == 1)
					continue;

				// 직전 탐색한 방향과 같은 방향으로 진행
				// 직선 도로를 놓았을 때 visited 보다 적은 비용일 때만 진행
				if (d == prevD && visited[nr][nc][d] >= cost + 100) {
					visited[nr][nc][d] = cost + 100;
					q.offer(new int[] { nr, nc, cost + 100, d });
				}

				// 코너
				// 직선 도로 + 코너를 놓았을 때 visited 보다 적은 비용일 때만 진행
				else if (d != prevD && visited[nr][nc][d] >= cost + 600) {
					visited[nr][nc][d] = cost + 600;
					q.offer(new int[] { nr, nc, cost + 600, d });
				}
			}
		}

		return answer;
	}
}