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

public class BJ_1987 {
	static int r, c, max, root[][];
	static char map[][];
	static int dr[] = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int dc[] = { 0, 1, 0, -1 };
	static Set<Character> alphaSet;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		max = 0;
		map = new char[r][];
		root = new int[r][c];
		alphaSet = new HashSet<Character>(); // 탐색한 알파벳 set
		for (int i = 0; i < r; i++)
			map[i] = br.readLine().toCharArray();

		alphaSet.add(map[0][0]);
		dfs(0, 0, 1);
		System.out.println(max);
	}

	static void dfs(int i, int j, int dist) {
		// 이동경로 체크에 현재 거리 저장
		root[i][j] = dist;

		boolean dfsCall = false;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];

			if (isValid(nr, nc)) {
				dfsCall = true;
				alphaSet.add(map[nr][nc]);
				dfs(nr, nc, dist + 1);
			}
		}

		// dfs 호출을 못했으면 마지막 점이므로 max 거리 갱신
		if (!dfsCall && dist > max)
			max = dist;

		// 왔던 점 초기화 및 알파벳 set에서 빼주기
		root[i][j] = 0;
		alphaSet.remove(map[i][j]);
	}

	static boolean isValid(int i, int j) {
		if (0 <= i && i < r && 0 <= j && j < c && root[i][j] == 0 && !alphaSet.contains(map[i][j]))
			return true;
		return false;
	}
}