package d0920;

import java.util.*;
import java.io.*;

public class BJ_1162 {

	static class Vertex implements Comparable<Vertex> {
		int no;
		long weight;
		int k;
		Vertex link;

		public Vertex(int no, long weight, int k, Vertex link) {
			super();
			this.no = no;
			this.weight = weight;
			this.k = k;
			this.link = link;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(weight, o.weight);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n, m, k;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		Vertex[] graph = new Vertex[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from] = new Vertex(to, weight, 0, graph[from]);
			graph[to] = new Vertex(from, weight, 0, graph[to]);
		}

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		long[][] distance = new long[k + 1][n + 1];

		for (int i = 0; i < k + 1; i++)
			Arrays.fill(distance[i], Long.MAX_VALUE);

		pq.offer(new Vertex(1, 0, 0, null));
		distance[0][1] = 0;

		while (!pq.isEmpty()) {
			Vertex v = pq.poll();
			int curN = v.no;
			long curW = v.weight;
			int curK = v.k;

			if (distance[curK][curN] < curW)
				continue;

			for (Vertex adj = graph[curN]; adj != null; adj = adj.link) {
				if (distance[curK][adj.no] > distance[curK][curN] + adj.weight) {
					distance[curK][adj.no] = distance[curK][curN] + adj.weight;
					pq.add(new Vertex(adj.no, distance[curK][adj.no], curK, null));
				}

				if (curK < k && distance[curK + 1][adj.no] > distance[curK][curN]) {
					distance[curK + 1][adj.no] = distance[curK][curN];
					pq.add(new Vertex(adj.no, distance[curK + 1][adj.no], curK + 1, null));
				}
			}
		}

		long result = Long.MAX_VALUE;
		for (int i = 0; i < k + 1; i++)
			result = Long.min(result, distance[i][n]);
		System.out.println(result);
	}
}
