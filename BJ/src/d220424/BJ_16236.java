package d220424;

import java.util.*;
import java.io.*;

/*
백준16236 아기 상어

풀이법 :
	 1. bfs로 조건에 맞는 물고기 좌표를 찾습니다.
	 2. 해당 물고기 까지 거리만큼 time을 더해줍니다.
	 3. 조건에 맞는 물고기 좌표가 더이상 없다면 종료합니다.

시간: 27m
  */

public class BJ_16236 {

	static int n, m, shark, eat, r, c, time, map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		shark = 2;
		eat = 0;
		time = 0;
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					map[i][j] = 0;
					r = i;
					c = j;
				}
			}
		}

		while (true) {
			int[] fish = bfs(); // 시간, i, j

			if (fish == null) {
				break;
			}

			time += fish[0];
			r = fish[1]; // 상어 이동
			c = fish[2];

			if (++eat == shark) {
				shark++;
				eat = 0;
			}
		}

		System.out.println(time);
	}

	static int[] bfs() {

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1[0] != o2[0]) {
				return Integer.compare(o1[0], o2[0]);
			}

			if (o1[1] != o2[1]) {
				return Integer.compare(o1[1], o2[1]);
			}

			return Integer.compare(o1[2], o2[2]);
		});
		boolean[][] visited = new boolean[n][n];
		pq.offer(new int[] { 0, r, c });

		while (!pq.isEmpty()) {
			int size = pq.size();

			while (size-- > 0) {
				int[] cur = pq.poll();
				int dist = cur[0];
				int i = cur[1];
				int j = cur[2];

				if (map[i][j] > 0 && shark > map[i][j]) {
					map[i][j] = 0;
					return new int[] { dist, i, j };
				}

				if (visited[i][j]) {
					continue;
				}

				visited[i][j] = true;

				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];

					if (nr < 0 || nc < 0 || nr == n || nc == n || visited[nr][nc] || shark < map[nr][nc]) {
						continue;
					}

					pq.offer(new int[] { dist + 1, nr, nc });
				}
			}
		}

		return null;
	}
}
