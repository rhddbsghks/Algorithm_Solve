package d0923;

import java.util.*;
import java.io.*;

public class JO_1682_2 {

	static int n, min, order[], place[][];
	static boolean[] selected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		place = new int[n][n];
		order = new int[n];
		selected = new boolean[n];

		min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				place[i][j] = Integer.parseInt(st.nextToken());
		}

		per(1, 0);
		System.out.println(min);
	}

	static void per(int cnt, int weight) {
		if (cnt == n) {
			if (place[order[n - 1]][0] != 0)
				min = Integer.min(min, weight + place[order[n - 1]][0]);
			return;
		}

		for (int i = 1; i < n; i++) {
			if (!selected[i]) {
				if (place[order[cnt - 1]][i] == 0 || weight + place[order[cnt - 1]][i] > min)
					continue;

				order[cnt] = i;
				selected[i] = true;
				per(cnt + 1, weight + place[order[cnt - 1]][i]);
				selected[i] = false;
			}
		}
	}
}
