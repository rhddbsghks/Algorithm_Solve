package d0828;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 1774 우주신과의 교감

풀이법 : 
	Kruskal 알고리즘을 사용하였습니다. 
	좌표를 차례로 입력 받아 이전 좌표들과의 거리를 계산해 pq에 넣고
	이미 연결된 좌표들을 union을 해주고 
	남은 edge 개수를 채울 때 까지 Kruskal 알고리즘을 사용해 최소비용을 계산합니다.
		 
주의할 점: 
또 문제를 제대로 읽지 않고 이미 연결된 점들이 cycle을 이루지 않을 것이라 넘겨짚어 고생 좀 했네요.
문제는 있는 그대로만 봐야겠다고 다시한번  배웠습니다. 
 */


public class BJ_1774 {

	// Edge: 간선 class
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}

	static int[] parents;

	
	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] pos = new int[n + 1][];  // 입력된 우주신 좌표 array
		int edge = 0;

		parents = new int[n + 1];
		for (int i = 1; i < n + 1; i++)
			parents[i] = i;

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		// 우주신들의 좌표를 pos[][]에 저장하고 직전의 모든 좌표들과의 거리를 계산해 간선을 생성합니다.
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pos[i] = new int[] { x, y };

			// pos[i] 이전에 있는 모든 좌표들과 거리를 계산해 pq에 저장
			for (int j = 1; j < i; j++) {
				double dist = Math.sqrt(Math.pow(x - pos[j][0], 2) + Math.pow(y - pos[j][1], 2));
				pq.offer(new Edge(i, j, dist));
			}
		}

		// 이미 연결된 통로들 처리
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// 통로들이 cycle을 이루지 않을 때만 edge 카운트
			if (union(start, to))
				edge++;
		}

		double bbangsang = 0;  // 통로 길이

		// Kruskal 알고리즘
		// 총 통로가 n-1이 될 때까지 반복
		while (edge<n-1) {
			Edge e = pq.poll();
			int start = e.from;
			int end = e.to;

			if (!union(start, end))
				continue;

			bbangsang += e.weight;
			edge++;
		}
		System.out.println(String.format("%.2f", bbangsang));
	}
	/****************** Main End******************/
	
	
	// union
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;
		parents[pb] = pa;
		return true;
	}

	// find
	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}
}