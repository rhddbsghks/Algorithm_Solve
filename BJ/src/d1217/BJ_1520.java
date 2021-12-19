package d1217;

import java.util.*;
import java.io.*;

/*
백준 1520 내리막길

풀이법 : 
	 1. dfs로 내리막을 탐색합니다.
	 2. 해당 지점에서 목적지까지 가능한 경우를 dp테이블에 저장해둡니다. 
	 3. 이후 해당지점을 다시 방문한 경우 dfs를 진행하지 않고 dp 테이블을 참조합니다.

 */

public class BJ_1520 {

	static int n, m, map[][], dp[][];
	static boolean visited[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dp = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0);
		System.out.println(dp[0][0]);
	}

	static int dfs(int r, int c) {
		// 목적지 도착하면 경우 1 리턴
		if (r == n - 1 && c == m - 1) {
			return 1;
		}

		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (oob(nr, nc) || map[r][c] <= map[nr][nc])
				continue;

			// (nr,nc)가 이전에 방문한 지점이면 dp 테이블을 참조해 현재지점 dp에 더함
			if (visited[nr][nc]) {
				dp[r][c] += dp[nr][nc];
				continue;
			}

			// 다음 지점 안가봤으면 dfs 진행해서 현재지점 dp에 더함
			dp[r][c] += dfs(nr, nc);
		}

		// 내 지점에서 목적지까지 가능한 경우의 수 리턴
		return dp[r][c];
	}

	static boolean oob(int r, int c) {
		return r < 0 || c < 0 || r == n || c == m;
	}
}