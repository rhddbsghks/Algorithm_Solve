package d1019;

import java.util.*;

class Solution {
    class Edge implements Comparable<Edge> {
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
			return Integer.compare(weight, o.weight);
		}
	}

	int[] parents;
    public int solution(int n, int[][] costs) {
		int result = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		parents = new int[n];

		for (int i = 0; i < costs.length; i++)
			pq.offer(new Edge(costs[i][0], costs[i][1], costs[i][2]));

		for (int i = 0; i < n; i++)
			parents[i] = i;

		while (!pq.isEmpty()&&n > 0) {
			Edge edge = pq.poll();
			if (!union(edge.from, edge.to))
				continue;

			result += edge.weight;
			n--;
		}
		return result;
	}

	 boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		parents[pb] = pa;

		return true;
	}

	 int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}
}