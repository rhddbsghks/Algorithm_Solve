package d0922;

import java.util.*;
import java.io.*;

public class BJ_12865 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] d = new int[k + 1];

		int[] w = new int[n + 1];
		int[] v = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++)
			for (int j = k; j >= w[i]; j--)
				d[j] = Math.max(d[j], d[j - w[i]] + v[i]);

		System.out.println(d[k]);
	}
}