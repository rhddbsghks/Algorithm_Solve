package d0916;

import java.util.*;
import java.io.*;

public class SWEA_1263 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./src/d0916/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					int dist = Integer.parseInt(st.nextToken());
					if (dist == 0)
						arr[i][j] = 2 * N;
					else
						arr[i][j] = dist;
				}

			for (int k = 0; k < N; k++)
				for (int i = 0; i < N; i++)
					for (int j = 0; j < N; j++) {
						int Dij = arr[i][j];
						int Dik = arr[i][k];
						int Dkj = arr[k][j];

						arr[i][j] = Integer.min(Dij, Dik + Dkj);
					}

			int min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				int cc = 0;
				for (int j = 0; j < N; j++)
					if (i != j)
						cc += arr[i][j];

				min = Integer.min(cc, min);
			}

			System.out.println("#" + t + " " + min);
		}
	}
}