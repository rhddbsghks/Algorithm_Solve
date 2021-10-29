package d1030;

import java.util.*;
import java.io.*;

/*
백준 20002 사과나무

풀이법 : 
	 1. 입력값을 누적합 2차원 배열로 선언해줍니다. 
	 2. 4중 for문... 으로 매우 비효율적으로 최댓값을 갱신해줍니다.

풀이가 상당히 잘못된 것 같습니다...
 */


public class BJ_20002 {

	static int n, result, sum[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		sum = new int[n][n + 1];
		result = Integer.MIN_VALUE;
		int tmp = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++)
				sum[i][j] = sum[i][j - 1] + Integer.parseInt(st.nextToken());
		}
		
		// k: 사각형 크기 i, j: 누적합할 인덱스  l: 한번 총합할 길이
		for (int k = 1; k <= n; k++) {
			for (int j = k; j <= n; j++) {
				for (int i = 0; i <= n - k; i++) {
					tmp = 0;
					for (int l = 0; l < k; l++) {
						tmp += sum[i + l][j] - sum[i + l][j - k];
					}
					result = Integer.max(result, tmp);
				}
			}
		}

		System.out.println(result);
	}
}
