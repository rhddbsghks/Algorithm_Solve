package d0824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7465 {

	static int n, m;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int t = 1; t <= testcase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			make();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				union(a, b);
			}
			
			System.out.println("#" + t + " " + countSet());
		}
	}

	static int countSet() {
		int cnt = 0;
		for (int i = 1; i < n + 1; i++)
			if (parents[i] == i)
				cnt++;
		return cnt;
	}

	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa != pb)
			parents[pb] = pa;
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static void make() {
		parents = new int[n + 1];
		for (int i = 1; i < n + 1; i++)
			parents[i] = i;
	}
}