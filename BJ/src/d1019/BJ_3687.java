package d1019;

import java.util.*;
import java.io.*;

/*
백준 3687 성냥개비

풀이법 : 
	 1. 최댓값은 간단한 규칙을 통해 계산합니다.
	 2. dp 테이블을 dp[7]까지 채워놓고 이후 숫자들은 가능한 조합 중 최솟값을 계산합니다.

 */


public class BJ_3687 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 0; t < testcase; t++) {
			StringBuilder maxNum = new StringBuilder();
			long[] dp = new long[101];
			Arrays.fill(dp, Long.MAX_VALUE);
			dp[2] = 1;
			dp[3] = 7;
			dp[4] = 4;
			dp[5] = 2;
			dp[6] = 6;
			dp[7] = 8;

			int n = Integer.parseInt(br.readLine());
			
			// 최댓값 구하기
			if (n % 2 == 0)
				for (int i = 0; i < n / 2; i++)
					maxNum.append(1);
			else {
				maxNum.append(7);
				for (int i = 0; i < n / 2 - 1; i++)
					maxNum.append(1);
			}

			// 최솟값 갱신
			for (int i = 8; i <= n; i++) 
				for (int j = 2; j <= i / 2; j++) 
					dp[i] = Long.min(dp[i], add(dp[j], dp[i - j]));
				
			System.out.println(dp[n] + " " + maxNum);
		}
	}

	// ab ba 중 최솟값 리턴
	static long add(long a, long b) {
		// 뒷자리가 6일땐 0으로 넣어주기
		
		// a+b
		long tmp1;
		if (b == 6)
			tmp1 = Long.parseLong(a + "" + 0);
		else
			tmp1 = Long.parseLong(a + "" + b);

		// b+a
		long tmp2;
		if (a == 6)
			tmp2 = Long.parseLong(b + "" + 0);
		else
			tmp2 = Long.parseLong(b + "" + a);

		return Long.min(tmp1, tmp2);
	}
}