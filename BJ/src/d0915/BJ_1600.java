package d0915;

import java.io.*;
import java.util.*;

/*
백준 1600 말이 되고픈 원숭이

풀이법 : 
		 1. 3차원 배열 visited를 사용해 방문체크와 경로 거리를 저장합니다.
		 2. visited는 [k+1][h][w] 크기로 생성하고 각각 말 이동을 0회~k회 한 H x W 크기의 배열로 볼 수 있습니다.
		 3. BFS를 사용해 목적지까지 최단 경로를 찾고 좌표값만 주던 기존 BFS와 달리 말을 사용한 횟수까지 추가로 전달합니다.
		 4. (0, 0, 0)을 큐에 넣고 BFS를 시작하고 사방탐색한 유효한 좌표를 큐에 삽입합니다. 
		 5. 0인덱스(말 사용횟수)가 K보다 작다면 말 이동을 할 수 있으므로 팔방탐색을 추가로 실시합니다.
		 5. 탐색하려는 좌표가 목적지라면 visited에 저장된 값(이때까지 경로 길이)를 출력하고 큐에 있는 좌표를 모두 소진하면 -1을 출력합니다.
		 
 */


public class BJ_1600 {

	static int k, w, h, map[][];
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int[] hr = { -2, -2, 2, 2, -1, 1, -1, 1 };
	static int[] hc = { -1, 1, -1, 1, -2, -2, 2, 2 };
	static int[][][] visited;

	static final int HORSE = 0;
	static final int R = 1;
	static final int C = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visited = new int[k + 1][h][w];

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int[] start = new int[] { 0, 0, 0 };
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(start);

		while (!q.isEmpty()) {
			int current[] = q.poll();
			
			if (current[R] == h - 1 && current[C] == w - 1) {
				System.out.println(visited[current[HORSE]][current[R]][current[C]]);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = current[R] + dr[d];
				int nc = current[C] + dc[d];

				if (nr < 0 || nc < 0 || nr >= h || nc >= w || map[nr][nc] == 1 || visited[current[HORSE]][nr][nc] != 0)
					continue;

				visited[current[HORSE]][nr][nc] = visited[current[HORSE]][current[R]][current[C]] + 1;
				q.offer(new int[] { current[HORSE], nr, nc });
			}

			if (current[HORSE] < k)
				for (int d = 0; d < 8; d++) {
					int nr = current[R] + hr[d];
					int nc = current[C] + hc[d];

					if (nr < 0 || nc < 0 || nr >= h || nc >= w || map[nr][nc] == 1
							|| visited[current[HORSE] + 1][nr][nc] != 0)
						continue;

					visited[current[HORSE] + 1][nr][nc] = visited[current[HORSE]][current[R]][current[C]] + 1;
					q.offer(new int[] { current[HORSE] + 1, nr, nc });
				}
		}

		System.out.println(-1);
	}
}