package d220228;

import java.io.*;
import java.util.*;

/*
백준2616 소형기관차

풀이법 :
	 1. 2차원 dp 배열을 선언해줍니다.
	 2. 기관차가 1개일때, 2개, 3개일때를 차례로 dp 배열을 채워줍니다.

 */


public class BJ_2616 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int train[] = new int[n];
        int sum[] = new int[n];

        // 열차 입력 및 누적합 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                sum[i] = train[i];
            } else {
                sum[i] = sum[i - 1] + train[i];
            }
        }

        int k = Integer.parseInt(br.readLine());

        int[][] dp = new int[4][n];
        dp[1][k - 1] = sum[k - 1];
        dp[2][2 * k - 1] = sum[2 * k - 1];
        dp[3][3 * k - 1] = sum[3 * k - 1];

        for (int i = 1; i < 4; i++) {
            for (int j = i * k; j < n; j++) {
                // 열차 i개
                // j를 끝으로 하는 열차 놓아보기
                // 안놓고 j-1까지의 최댓값 vs 새로 열차 놓고 + 그 열차 이전까지의 최댓값
                dp[i][j] = Integer.max(dp[i][j - 1], dp[i - 1][j - k] + (sum[j] - sum[j - k]));
            }
        }

        System.out.println(dp[3][n-1]);
    }
}