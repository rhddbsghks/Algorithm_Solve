package d0829;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 11657 타임머신

풀이법 : 
	Bellman-Ford 알고리즘 기본 문제이었던 것 같습니다.
	여러 블로그 글들과 유튜버 동빈나님의 영상을 참조했습니다.
	처음에는 잘 이해가 안되서 헤맸지만 Dijkstra의 연장선으로 생각해보니 조금 더 와닿았습니다.
	Dijkstra와 Bellman-Ford 모두 n-1번 최단거리를 업데이트합니다.
	Dijkstra 알고리즘에서는 양의 가중치만 존재하기 때문에 한번 경유한 정점은 고려하지 않아도 되지만
	Bellman-Ford 알고리즘에서는 추후에 음의 가중치가 등장할 수도 있기 때문에 매번 모든 간선을 체크해야합니다.
	또한 모든 업데이트를 마친 후 다시한번 최단거리를 업데이트 했을 때 최단거리의 갱신이 발생한다면 음의 사이클이 존재한다고 판단할 수 있습니다.
		 
주의할 점: 
	distance 배열을 int 형으로 준다면 overflow가 발생할 수 있습니다. 
	예를 들어 정점이 500개, 가중치가 -10,000이고 1->2인 간선과 2->1인 간선이 각각 3,000개라고 가정을 해 봅시다.
	한 번 최단거리를 업데이트하면 모든 간선을 고려하기 때문에 1번 정점과 2번 정점 까지의 최단거리는 -30,000,000으로 갱신됩니다.
	이를 499번 즉, 약 500번 반복하므로 500 x -30,000,000인 가중치는 int 자료형의 범위를 벗어나게 됩니다.
 */


public class BJ_11657 {

	// Edge 클래스: 간선 정보 클래스
	static class Edge {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Edge[] route = new Edge[m]; // 버스 노선들 정보

		// 버스 노선들 추가하기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			route[i] = new Edge(from, to, weight);
		}

		// Bellman-Ford
		long distance[] = new long[n + 1];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[1] = 0;

		// n-1번동안 모든 간선들을 매번 업데이트하고 n번째에도 최단거리가 갱신된다면 음의 사이클이 존재한다는 뜻임
		for (int i = 1; i < n + 1; i++)
			// 모든 간선 체크
			for (int j = 0; j < m; j++) {
				int from = route[j].from;
				int to = route[j].to;
				int weight = route[j].weight;

				// to 정점까지의 최단거리 갱신
				if (distance[from] != Long.MAX_VALUE && distance[to] > distance[from] + weight) {
					distance[to] = distance[from] + weight;

					// n-1번 마친 후 한번 더 갱신이 된다면 음의 사이클이 존재
					if (i == n) {
						System.out.println(-1);
						return;
					}
				}
			}
		// Bellman-Ford

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < n + 1; i++) {
			if (distance[i] != Long.MAX_VALUE)
				sb.append(distance[i] + "\n");
			else
				sb.append(-1 + "\n");
		}
		System.out.println(sb);
	}
}