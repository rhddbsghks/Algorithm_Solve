package d220120;

import java.util.*;

/*
백준 4811 알약

풀이법 :
	 1. 2차원 배열 dp[][]를 먼저 30까지 채워줍니다.  dp[h][w] = h를 h개, w를 w개 선택한 문자열 개수
	 2. 점화식: dp[h][w] = dp[h-1][w]+dp[h][w-1]

 */


public class BJ_4811 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long[][] dp;
        dp = new long[31][31];

        for (int h = 0; h <= 30; h++) {
            for (int w = 0; w <= 30; w++) {
                // h를 0개 고르면 문자열 종류는 항상 1  ex)w, ww, www...
                if (h == 0) {
                    dp[h][w] = 1;
                    continue;
                }

                // h를 더 많이 고를수는 없음
                if (h > w) {
                    dp[h][w] = 0;
                } else {
                    dp[h][w] = dp[h - 1][w] + dp[h][w - 1];
                }
            }
        }

        while (true) {
            int n = sc.nextInt();

            if (n == 0)
                return;

            System.out.println(dp[n][n]);
        }
    }
}