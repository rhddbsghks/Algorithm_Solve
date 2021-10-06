package d1006;

import java.io.*;
import java.util.*;

/*
백준 16236 아기 상어

풀이법 : 
	 1. BFS로 탐색 가능한 가까운 상어를 찾습니다.
	 2. 0이거나 상어크기인 물고기는 BFS 큐에 넣고, 만약 먹을 수 있는 물고기를 만나면 해당 시간의 물고기까지만 탐색합니다.
	 3. 이때 물고기들의 좌표를 우선순위큐를 통해 관리합니다.
	 4. BFS가 종료되었는데 물고기가 없다면 false를 반환, 있다면 우선순위큐에서 하나를 뽑아 상어 위치를 조정합니다.
	 5. 해당 물고기까지 걸렸던 시간을 result에 더해줍니다. 
	 6. 1~5를 false를 반환할 때까지 반복합니다.
		 
 */

public class BJ_16236 {
	static int n, m, r, c, shark, fishCnt, result, map[][];
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		shark = 2;
		result = 0;
		fishCnt = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 상어 현재 좌표
				if (map[i][j] == 9) {
					r = i;
					c = j;
				}
			}
		}

		// 잡을 수 있는 물고기 없을때 까지 반복
		while (findFish()) {
		}

		System.out.println(result);
	}

	static boolean findFish() {
		Queue<int[]> q = new ArrayDeque<>();

		PriorityQueue<int[]> fishPos = new PriorityQueue<int[]>((o1, o2) -> {
			if (o1[0] != o2[0])
				return Integer.compare(o1[0], o2[0]);
			return Integer.compare(o1[1], o2[1]);
		});

		boolean[][] visited = new boolean[n][n];
		q.offer(new int[] { r, c });
		visited[r][c] = true;

		int i, j, nr, nc, size, time = 0;
		boolean find = false;

		// 물고기를 찾을 때까지 거리를 늘려가며 탐색
		while (!q.isEmpty() && !find) {
			size = q.size();
			while (size-- > 0) {
				int[] cur = q.poll();
				i = cur[0];
				j = cur[1];

				for (int d = 0; d < 4; d++) {
					nr = i + dr[d];
					nc = j + dc[d];

					if (nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc] || map[nr][nc] > shark)
						continue;

					// 나보다 작은 물고기라면 물고기좌표에 넣고 flag 설정
					if (map[nr][nc] != 0 && map[nr][nc] != shark) {
						fishPos.add(new int[] { nr, nc });
						find = true;
					} 
					
					// 아니면 더 가보기
					else {
						visited[nr][nc] = true;
						q.offer(new int[] { nr, nc });
					}
				}
			}
			time++;
		}

		if (!find)
			return false;

		// 물고기 성장
		if (++fishCnt == shark) {
			shark++;
			fishCnt = 0;
		}

		// 물고기 이동
		int[] nextFish = fishPos.poll();
		map[r][c] = 0;
		r = nextFish[0];
		c = nextFish[1];
		map[r][c] = 9;
		result += time;

		return true;
	}
}