package d0824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3289 {

	static int n, m;
	static int parents[];

	static void make() {
		parents = new int[n + 1];
		Arrays.fill(parents, -1);
	}

	static void union(int a, int b) {
		if (find(a) != find(b)) {
			parents[find(a)] += parents[find(b)];
			parents[find(b)] = find(a);
		}
	}

	static int find(int a) {
		if (parents[a] < 0)
			return a;

		return parents[a] = find(parents[a]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			make();

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				switch (op) {
				case 0:
					union(a, b);
					break;

				case 1:
					if (find(a) == find(b))
						sb.append(1);
					else
						sb.append(0);

					break;
				}
			}
			System.out.println("#" + t + " " + sb);
		}
	}
}