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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visited = new int[k + 1][h][w]; // 0~k번 말을 사용한 map, 총 k+1개

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int[] start = new int[] { 0, 0, 0 }; // (0,0)까지 말을 0번 씀
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(start);

		// 시작점 넣고 BFS 시작
		while (!q.isEmpty()) {
			int current[] = q.poll();
			int horse = current[0];
			int r = current[1];
			int c = current[2];

			// 목적지 좌표면 해당하는 말 사용횟수에 해당하는 visited map을 참조해 경로 출력
			if (r == h - 1 && c == w - 1) {
				System.out.println(visited[horse][r][c]);
				return;
			}

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nc < 0 || nr >= h || nc >= w || map[nr][nc] == 1 || visited[horse][nr][nc] != 0)
					continue;

				// 현재 좌표의 말 사용횟수에 해당하는 visited[][]를 업데이트
				// 말을 사용하지 않았으므로 현재 좌표와 같은 visited[][]임
				visited[horse][nr][nc] = visited[horse][r][c] + 1;
				q.offer(new int[] { horse, nr, nc });
			}

			// 아직 말 사용 횟수가 남았으면 8방탐색
			if (horse < k)
				for (int d = 0; d < 8; d++) {
					int nr = r + hr[d];
					int nc = c + hc[d];

					if (nr < 0 || nc < 0 || nr >= h || nc >= w || map[nr][nc] == 1 || visited[horse + 1][nr][nc] != 0)
						continue;

					visited[horse + 1][nr][nc] = visited[horse][r][c] + 1;
					q.offer(new int[] { horse + 1, nr, nc });
				}
		}
		
		// 목적지 좌표에 도달하지 못했다면
		System.out.println(-1);
	}
}