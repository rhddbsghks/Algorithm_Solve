package d0915;

import java.io.*;
import java.util.*;

public class SWEA_3124 {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			long result = 0;

			make(v);
			PriorityQueue<Edge> pq = new PriorityQueue<>();

			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				pq.offer(new Edge(from, to, weight));
			}

			while (!pq.isEmpty()) {
				if (v == 1)
					break;
				Edge edge = pq.poll();

				if (union(edge.from, edge.to)) {
					v--;
					result += edge.weight;
				}
			}
			
			System.out.println("#"+t+" "+result);
		}
	}

	static void make(int v) {
		parents = new int[v + 1];
		for (int i = 1; i < v + 1; i++)
			parents[i] = i;
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		parents[pa] = pb;
		return true;
	}
}