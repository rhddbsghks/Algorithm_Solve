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

	static Node[] friendsU, friendsD;
	static int[] upper, lower;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			int n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			friendsU = new Node[n + 1];
			friendsD = new Node[n + 1];
			upper = new int[n + 1];
			lower = new int[n + 1];
			visited = new boolean[n + 1];

			Arrays.fill(lower, -1);
			Arrays.fill(upper, -1);
			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				friendsU[from] = new Node(to, friendsU[from]);
				friendsD[to] = new Node(from, friendsD[to]);
			}

			for (int i = 1; i <= n; i++) {
				Arrays.fill(visited, false);
				dfsU(i);
				Arrays.fill(visited, false);
				dfsD(i);
			}

			int result = 0;
			for (int i = 1; i <= n; i++)
				if (upper[i] + lower[i] == n - 1)
					result++;
			System.out.println("#" + t + " " + result);
		}
	}

	static void dfsU(int num) {
		if (visited[num])
			return;
		visited[num] = true;
		lower[num]++;

		for (Node cur = friendsU[num]; cur != null; cur = cur.link)
			dfsU(cur.to);
	}

	static void dfsD(int num) {
		if (visited[num])
			return;
		visited[num] = true;
		upper[num]++;

		for (Node cur = friendsD[num]; cur != null; cur = cur.link)
			dfsD(cur.to);
	}
}
