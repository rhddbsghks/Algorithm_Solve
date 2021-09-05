package d0905;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 17472 다리 만들기2

풀이법 : 
		 1. bfs를 사용해 섬 별로 번호를 붙여 country[][]를 생성합니다. 
		 2. country[][]를 탐색하여 0이 아닐 시(섬이면) 4방을 탐색하고 만약 0이라면 해당 섬의 가장자리라는 뜻입니다.
		 3. 해당방향으로 다리 생성을 시도하고 성공한다면 Bridge를 생성하여 pq에 삽입합니다.
		 4. 가능한 모든 Bridge를 생성한 다음(간선 생성 완료) Kruskal 알고리즘을 사용해 최소 신장트리를 생성합니다.
		 5. 만약 선택된 Bridge가 섬 개수-1 보다 작다면 모든 섬을 연결하지 못한 것이므로 -1을 출력합니다.
		 
주의할 점: 
	섬 별로 번호를 붙일 때 다리 생성을 같이 시도하여 엄청난 삽질을 했네요...
 */


public class BJ_17472 {

	// Bridge 클래스
	static class Bridge implements Comparable<Bridge> {
		int from;
		int to;
		int length;

		public Bridge(int from, int to, int length) {
			this.from = from;
			this.to = to;
			this.length = length;
		}

		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.length, o.length);
		}
	}

	static int n, m, island, min;  // N, M, 섬 개수, 최솟값
	static int[][] input;  // NxM 입력 배열
	static int[][] country;  // 각 섬 별로 1부터 번호를 매긴 NxM 배열
	static boolean[][] visited;  // bfs시 사용하는 방문체크배열
	
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static PriorityQueue<Bridge> bridge;  // Kruskal을 위한 Bridge 우선순위 큐

	static int[] parents;  // Kruskal 용 parents

	
	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		input = new int[n][m];
		country = new int[n][m];
		visited = new boolean[n][m];
		min = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				input[i][j] = Integer.parseInt(st.nextToken());
		}

		island = 0;
		bridge = new PriorityQueue<>();
		
		// input 배열을 토대로 island들 생성
		makeIsland();

		// country[][] 배열을 탐색
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				// 섬 번호가 있으면 4방탐색
				if (country[i][j] != 0)
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];

						// 해당부분이 0이면 섬의 가장자리이므로 다리 생성 시도
						if (isInBoundary(nr, nc) && country[nr][nc] == 0)
							makeBridge(i, j, d);
					}
		
		// 생성된 다리들로 크루스칼
		kruskal();
	}
	/****************** Main End ******************/
	
	
	/****************** Method ******************/
	
	// makeIsland: input을 탐색하여 country[][] 배열에 섬 생성하기
	static void makeIsland() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				if (input[i][j] == 1 && !visited[i][j])
					bfs(i, j, ++island);
			}
	}

	// bfs: island 번호로 섬 생성
	static void bfs(int i, int j, int island) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { i, j });

		while (!q.isEmpty()) {
			int[] pos = q.poll();
			int r = pos[0];
			int c = pos[1];

			if (visited[r][c])
				continue;

			country[r][c] = island;
			visited[r][c] = true;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (isInBoundary(nr, nc) && input[nr][nc] == 1 && !visited[nr][nc])
					q.add(new int[] { nr, nc });
			}
		}
	}
	
	// makeBridge: i,j 에서 d 방향으로 탐색후 가능하면 Bridge 생성
	static void makeBridge(int i, int j, int d) {
		int from = country[i][j];
		int length = 0;

		while (true) {
			// i, j를 dr, dc 만큼 더해가며 탐색
			i += dr[d];
			j += dc[d];

			if (isInBoundary(i, j)) {
				// 섬을 만나면 return, 이때 길이가 2 이상이면 다리 생성하기
				if (country[i][j] != 0) {
					if (length >= 2) {
						int to = country[i][j];
						bridge.add(new Bridge(from, to, length));
					}
					return;
				}
				// 0이면 다리길이 1 증가
				length++;
				continue;
			}
			// 경계를 벗어나면 종료
			break;
		}
	}

	// kruskal: 생성된 bridge 큐에서 꺼내가며 MST 생성
	static void kruskal() {
		parents = new int[island + 1];
		for (int i = 1; i <= island; i++)
			parents[i] = i;

		while (!bridge.isEmpty()) {
			Bridge b = bridge.poll();

			if (union(b.from, b.to)) {
				min += b.length;
				island--;
			}

			// 간선 개수가 island-1이 되면 최솟값 출력 후 return
			if (island == 1) {
				System.out.println(min);
				return;
			}
		}
		
		// bridge 큐를 모두 사용해도 간선 개수가 모자라면 -1 출력
		System.out.println(-1);
	}

	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		parents[pb] = pa;
		return true;
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean isInBoundary(int i, int j) {
		if (0 <= i && i < n && 0 <= j && j < m)
			return true;
		return false;
	}
	/****************** Method End ******************/
}