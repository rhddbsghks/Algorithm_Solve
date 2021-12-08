package d1208;

/*
백준 1245 농장 관리

풀이법 : 
	 1. dfs로 같은 높이인 점들 좌표를 큐에 저장합니다.
	 2. 해당 점들을 8방탐색하여 하나라도 더 높은 점이 있다면 false를 리턴합니다. 
	 3. 같은 높이인 모든 점들이 주변 점들보다 높다면 개수를 하나 카운트합니다.  

 */


import java.io.*;
import java.util.*;

public class BJ_1245 {

	static int n, m, answer, map[][];
	static boolean checked[][];
	static Queue<int[]> pos;
	static int dr[] = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int dc[] = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		checked = new boolean[n][m];
		pos = new ArrayDeque<int[]>(); // dfs로 같은 높이의 점들 좌표 임시 저장
		answer = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				if (checked[i][j]) // 방문체크
					continue;

				pos.clear(); // 임시 큐 초기화
				dfs(i, j); // 현재 점과 인접한 같은 높이 점들 큐에 저장
				if (posCheck()) // 큐에서 모든 점들이 인근 점들보다 높다면 true
					answer++;
			}

		System.out.println(answer);
	}

	// dfs: 8방탐색하여 같은 높이 점들 좌표 임시 저장
	static void dfs(int i, int j) {
		checked[i][j] = true;
		pos.offer(new int[] { i, j });

		for (int d = 0; d < 8; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];

			if (!oob(nr, nc) && map[i][j] == map[nr][nc] && !checked[nr][nc])
				dfs(nr, nc);
		}
	}

	// posCheck: pos에 임시저장된 모든 점들이 산봉우리인지 체크
	static boolean posCheck() {
		while (!pos.isEmpty()) {
			if (!isPeak(pos.poll()))
				return false;
		}
		return true;
	}

	// isPeak: 하나의 점을 8방탐색하여 봉우리 체크
	static boolean isPeak(int[] p) {
		int i = p[0];
		int j = p[1];

		for (int d = 0; d < 8; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];

			if (oob(nr, nc))
				continue;

			if (map[i][j] < map[nr][nc])
				return false;
		}
		return true;
	}

	static boolean oob(int i, int j) {
		return i < 0 || j < 0 || i == n || j == m;
	}
}
