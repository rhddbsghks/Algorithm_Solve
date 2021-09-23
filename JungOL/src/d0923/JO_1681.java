package d0923;

import java.util.*;
import java.io.*;

public class JO_1681 {

	static int[] order;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int place[][] = new int[n][n];
		order = new int[n - 1];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n - 1; i++)
			order[i] = i + 1;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				place[i][j] = Integer.parseInt(st.nextToken());
		}

		LOOP: do {
			int weight = place[0][order[0]];
			if (weight == 0)
				continue;

			for (int i = 0; i < n - 2; i++) {
				int from = order[i];
				int to = order[i + 1];

				if (place[from][to] == 0)
					continue LOOP;

				weight += place[from][to];
				if(weight>=min)
					continue LOOP;
			}

			weight += place[order[n - 2]][0];
			min = Integer.min(weight, min);

		} while (np(order));

		System.out.println(min);

	}

	static boolean np(int[] arr) {
		int n = arr.length;
		int i = n - 1;

		while (i > 0 && arr[i - 1] >= arr[i])
			i--;

		if (i == 0)
			return false;

		int j = n - 1;

		while (arr[i - 1] >= arr[j])
			j--;

		int tmp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = tmp;

		int k = n - 1;

		while (k >= i) {
			int t = arr[k];
			arr[k] = arr[i];
			arr[i] = t;
			i++;
			k--;
		}

		return true;
	}
}
