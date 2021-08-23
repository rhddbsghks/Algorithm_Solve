package d0823;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1260 {

	static class Node {
		int vertex;
		Node link;

		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}

	static Node[] graph;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		graph = new Node[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[from] = new Node(to, graph[from]);
			graph[to] = new Node(from, graph[to]);
		}
		System.out.println(dfs(v, new StringBuilder(), new boolean[n + 1]));
		System.out.println(bfs(v));
	}

	static String dfs(int vertex, StringBuilder sb, boolean[] visited) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		visited[vertex] = true;
		sb.append(vertex + " ");

		// 자식들 우선순위 큐에 다 넣고
		for (Node node = graph[vertex]; node != null; node = node.link)
			pq.offer(node.vertex);

		// 하나씩 dfs
		while (!pq.isEmpty()) {
			int nv = pq.poll();
			if (!visited[nv])
				dfs(nv, sb, visited);
		}
		return sb.toString();
	}

	static String bfs(int vertex) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[n + 1];
		StringBuilder sb = new StringBuilder();

		q.offer(vertex);
		visited[vertex] = true;

		while (!q.isEmpty()) {
			int v = q.poll();
			sb.append(v + " ");

			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			for (Node node = graph[v]; node != null; node = node.link)
				if (!visited[node.vertex]) {
					pq.offer(node.vertex);
					visited[node.vertex] = true;
				}

			while (!pq.isEmpty())
				q.offer(pq.poll());

		}
		return sb.toString();
	}
}