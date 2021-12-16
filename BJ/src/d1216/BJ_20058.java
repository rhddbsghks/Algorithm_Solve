package d1216;

import java.util.*;
import java.io.*;

/*
백준 20058 마법사 상어와 파이어스톰 (1h 24m)

풀이법 : 
	 1. 주어진 대로 시뮬레이션을 진행하고 얼음을 녹일 때 마다 총합에서 1씩 빼줍니다.
	 2. 모두 진행한 후 dfs로 최대 영역 칸수를 갱신합니다.

 */


public class BJ_20058 {

	static int map[][], inputN, n, q, sum, area;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		inputN = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		n = (int) Math.pow(2, inputN); // map = n X n 배열
		map = new int[n][n];
		visited = new boolean[n][n];
		sum = area = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum += map[i][j];
			}
		}

		st = new StringTokenizer(br.readLine());
		simulate(st);
		checkArea();
		System.out.println(sum);
		System.out.println(area);
	}

	// simulate: 레벨 별 파이어스톰 + 얼음 녹이기 사이클
	static void simulate(StringTokenizer st) {
		for (int i = 0; i < q; i++) {
			int level = Integer.parseInt(st.nextToken());
			fireStorm(level);
			melt();
		}
	}

	// fireStorm: 레벨에 맞게 map 회전
	static void fireStorm(int level) {
		int l = (int) Math.pow(2, level);

		for (int i = 0; i < n; i += l)
			for (int j = 0; j < n; j += l)
				rotate(i, j, l);
	}
	
	// rotate: (r,c)에서 l 사이즈로 회전
	static void rotate(int r, int c, int l) {
		int cnt = l - 1;

		for (int depth = 0; depth < l / 2; depth++) {
			for (int x = 0; x < cnt; x++) {
				int tmp = map[r + depth][c + depth];
				// 좌
				for (int i = r + depth; i < r + l - depth - 1; i++)
					map[i][c + depth] = map[i + 1][c + depth];

				// 하
				for (int j = c + depth; j < c + l - depth - 1; j++)
					map[r + l - depth - 1][j] = map[r + l - depth - 1][j + 1];

				// 우
				for (int i = r + l - depth - 1; i > r + depth; i--)
					map[i][c + l - depth - 1] = map[i - 1][c + l - depth - 1];

				// 상
				for (int j = c + l - depth - 1; j > c + depth; j--)
					map[r + depth][j] = map[r + depth][j - 1];

				map[r + depth][c + depth + 1] = tmp;
			}
			cnt -= 2;
		}
	}

	// melt: 파이어스톰 후 얼음 녹이기
	static void melt() {
		Queue<int[]> q = new ArrayDeque<>(); // 1씩 녹일 좌표 위치 저장

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0)
					continue;

				int adj = 0;

				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];

					if (oob(nr, nc))
						continue;

					if (map[nr][nc] > 0)
						adj++;
				}

				// 사방탐색 후 인접 얼음 3개 미만이면 좌표 추가
				if (adj < 3) 
					q.offer(new int[] { i, j });
			}

		// 저장했던 좌표들 1씩 빼주고 총합에서도 1씩 빼주기
		for (int[] pos : q) {
			map[pos[0]][pos[1]] -= 1;
			sum -= 1;
		}
	}

	// checkArea: dfs로 인접한 영역들 계산 후 최댓값 갱신
	static void checkArea() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (map[i][j] > 0 && !visited[i][j])
					area = Integer.max(area, dfs(i, j));
	}

	// 얼음덩어리 칸수 리턴
	static int dfs(int i, int j) {
		int total = 0;
		visited[i][j] = true;

		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];

			if (oob(nr, nc) || visited[nr][nc]||map[nr][nc]==0)
				continue;

			total += dfs(nr, nc);
		}

		return total + 1;
	}

	static boolean oob(int i, int j) {
		return i < 0 || j < 0 || i == n || j == n;
	}
}