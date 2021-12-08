package d1203;

import java.util.*;
import java.io.*;

/*
SWEA2383 동철이의 일 분배

풀이법 : 
	 1. 16!이므로 가지치기를 하며 dfs를 활용합니다.
	 2. 확률이 0 or 곱했을때 최대값보다 작아지면 가지치기를 해줍니다.

 */

public class SWEA_1865 {

	static double answer, work[][];
	static int n;
	static boolean used[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());
			used = new boolean[n];
			work = new double[n][n]; // 확률들을 %로 저장
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++)
					work[i][j] = (double) Integer.parseInt(st.nextToken()) / 100;
			}

			dfs(0, 1);
			System.out.printf("#" + t + " %.6f\n", answer*100);
		}
	}

	static void dfs(int cnt, double p) {
		if (cnt == n) {
			answer = p;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (used[i])
				continue;

			if (answer > p * work[cnt][i] || work[cnt][i]==0) // 선택한 확률이 0이거나 곱했을때 최댓값보다 작아지면 건너뛰기
				continue;

			used[i] = true;
			dfs(cnt + 1, p * work[cnt][i]);
			used[i] = false;
		}
	}
}
