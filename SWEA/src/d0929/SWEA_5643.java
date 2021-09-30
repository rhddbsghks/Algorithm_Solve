package d0929;

import java.util.*;
import java.io.*;

public class SWEA_5643 {

	static class Node {
		int to;
		Node link;

		public Node(int to, Node link) {
			this.to = to;
			this.link = link;
		}
	}

	static Node[] friends;
	static int[] upper, lower;
	static boolean[] visited;
	static int call;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			int n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			friends = new Node[n + 1];
			upper = new int[n + 1];
			lower = new int[n + 1];
			visited = new boolean[n + 1];

			Arrays.fill(lower, -1);
			Arrays.fill(upper, -1);
			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				friends[from] = new Node(to, friends[from]);
			}

			for (int i = 1; i <= n; i++) {
				Arrays.fill(visited, false);
				call = -1;
				dfs(i);
				upper[i]=call;
			}

			int result = 0;
			for (int i = 1; i <= n; i++)
				if (upper[i] + lower[i] == n - 1)
					result++;
			System.out.println("#" + t + " " + result);
		}
	}

	static void dfs(int num) {
		if (visited[num])
			return;
		visited[num] = true;
		lower[num]++;
		call++;

		for (Node cur = friends[num]; cur != null; cur = cur.link)
			dfs(cur.to);
	}
}
