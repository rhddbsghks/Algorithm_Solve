package d0819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
백준 1987 알파벳

풀이법 : 탐색한 알파벳을 set으로 관리하고 사방으로 dfs를 호출하고 현재까지 경로가 찍혀있는 점이면 호출하지 않는다. 
		 만약 dfs를 한 번도 호출하지 못했다면 마지막 점이므로 현재까지 거리와 max 값을 비교해 갱신한다. 
		 
 */

public class BJ_1987_BitMasking {
	static int r, c, max, root[][];
	static char map[][];
	static int dr[] = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int dc[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		max = 0;
		map = new char[r][];
		root = new int[r][c];
		for (int i = 0; i < r; i++)
			map[i] = br.readLine().toCharArray();

		dfs(0, 0, 1, 1 << (map[0][0] - 'A'));
		System.out.println(max);
	}

	static void dfs(int i, int j, int cnt, int bit) {
		root[i][j] = bit;
		max = Math.max(cnt, max);

		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];

			if (isValid(nr, nc) && (bit & (1 << map[nr][nc] - 'A')) == 0)
				dfs(nr, nc, cnt + 1, bit | (1 << map[nr][nc] - 'A'));
		}
	}

	static boolean isValid(int i, int j) {
		if (0 <= i && i < r && 0 <= j && j < c)
			return true;
		return false;
	}
}