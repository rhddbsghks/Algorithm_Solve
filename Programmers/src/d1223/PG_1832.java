package d1223;

/*
프로그래머스1832 보행자 천국

풀이법 : 
	 1. dfs로 목적지까지 도착하는 경로들을 탐색합니다.
	 2. dp 테이블을 3차원으로 생성하여 접근 방향 별로 경로 수를 저장해둡니다. 
	 3. 경로 수가 정수를 초과할 수 있으므로 dp 테이블에 저장할 때 경로 수%MOD 값을 저장합니다.

 */

public class PG_1832 {

	class Solution {
		int MOD = 20170805;
		boolean visited[][][];
		int M, N, map[][];
		int dp[][][];
		int dr[] = { 1, 0 };
		int dc[] = { 0, 1 };

		public int solution(int m, int n, int[][] cityMap) {
			M = m;
			N = n;
			map = cityMap;
			dp = new int[2][M][N];
			visited = new boolean[2][M][N];

			return dfs(0, 0, 0);
		}

		public int dfs(int i, int j, int prevD) {
			// 목적지 도착 시 경로 수 1 리턴
			if (i == M - 1 && j == N - 1) {
				return 1;
			}

			visited[prevD][i][j] = true;
			int root = 0; // (i,j)에서 경로 = 오른쪽칸 경로 + 아래쪽칸 경로

			// 하, 우 탐색
			for (int d = 0; d < 2; d++) {
				int nr = i + dr[d];
				int nc = j + dc[d];

				// 경계값 체크 및 1 이거나 or 2이면서 이전방향과 현재방향이 다를 때 건너뛰기
				if (oob(nr, nc) || map[nr][nc] == 1 || (map[i][j] == 2 && prevD != d))
					continue;

				// 해당 방향으로 방문한 적 있으면 dp테이블 참조
				if (visited[d][nr][nc])
					root += dp[d][nr][nc];

				// 처음 방문이면 dfs
				else
					root += dfs(nr, nc, d);
			}
			
			// dp 테이블에 저장
			dp[prevD][i][j] = root % MOD;
			
			return dp[prevD][i][j];
		}

		public boolean oob(int i, int j) {
			return i < 0 || j < 0 || i == M || j == N;
		}
	}
}
