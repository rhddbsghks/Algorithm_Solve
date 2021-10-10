package d1010;

import java.util.*;
import java.io.*;

/*
백준 11066

풀이법 : 
	 1. 2차원 dp 테이블을 생성하고 연속된 2개, 3개, 4개... 파일 비용의 최솟값을 갱신해나갑니다.
	 2. dp 테이블을 완성 후 dp[1][k] 값을 출력합니다.

시간복잡도 : O(N^3)

DP 접근하기가 너무 어렵네요. 
 */


public class BJ_11066 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 0; t < testcase; t++) {
			int k = Integer.parseInt(br.readLine());
			int[] data = new int[k + 1]; // 입력 데이터
			int[] sum = new int[k + 1]; // 누적합
			int[][] dp = new int[k + 1][k + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= k; i++) {
				data[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + data[i];
			}
			
			// 연속된 f개의 데이터 최소 비용
			for (int f = 1; f < k; f++) {
				// i ~ i+f 데이터를 합칠 때 소요되는 비용 갱신
				for (int i = 1; i <= k - f; i++) {
					dp[i][i + f] = Integer.MAX_VALUE;
					
					for (int j = i; j < i + f; j++)
						dp[i][i + f] = Integer.min(dp[i][i + f], dp[i][j] + dp[j + 1][i + f] + sum[i + f] - sum[i - 1]);

				}
			}
			System.out.println(dp[1][k]);
		}
	}
}