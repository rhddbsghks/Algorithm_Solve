package d0825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 1753 최단경로

풀이법: 
	수업시간에 배운 다익스트라 알고리즘에 우선순위큐를 이용한 최적화를 적용하면 쉽게 풀 수 있었던 문제 같습니다.
		 
공부해야할 점: 그래프 문제는 아직 많이 부족한 것 같네요. 문제 양치기 좀 해야할 것 같습니다.
 */


public class BJ_1753 {

	// Vertex 클래스
	static class Vertex implements Comparable<Vertex> {
		int to;
		int weight;
		Vertex link;

		public Vertex(int to, int weight, Vertex link) {
			this.to = to;
			this.weight = weight;
			this.link = link;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());

		Vertex[] graph = new Vertex[v + 1];

		// 그래프 생성
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from] = new Vertex(to, weight, graph[from]);
		}

		// Dijkstra
		int[] distance = new int[v + 1];
		boolean[] visited = new boolean[v + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(start, 0, null));

		while (!pq.isEmpty()) {
			Vertex vertex = pq.poll();

			// 이미 경유한 정점이면 pass
			if (visited[vertex.to])
				continue;

			visited[vertex.to] = true;

			for (Vertex adjVertex = graph[vertex.to]; adjVertex != null; adjVertex = adjVertex.link) {
				if (distance[adjVertex.to] > vertex.weight + adjVertex.weight)
					distance[adjVertex.to] = vertex.weight + adjVertex.weight;

				pq.add(new Vertex(adjVertex.to, distance[adjVertex.to], null));
			}
		}
		// Dijkstra

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < v + 1; i++) {
			if (distance[i] == Integer.MAX_VALUE)
				sb.append("INF\n");
			else
				sb.append(distance[i] + "\n");
		}
		System.out.println(sb);
	}
}