package d1005;

import java.io.*;
import java.util.*;

public class BJ_15961 {

	static int n, d, k, c, susi[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		susi = new int[n * 2];
		int[] pick = new int[d + 1];
		int result = 0;

		// 스시배열을 2배로 잡아서 모든 점에서 시작할 수 있게 초기화
		for (int i = 0; i < n; i++)
			susi[i] = susi[i + n] = Integer.parseInt(br.readLine());

		// 첫 k개의 스시
		for (int i = 0; i < k; i++)
			if (pick[susi[i]]++ == 0)
				result++;

		int picked = result;

		for (int i = 0; i < n; i++) {
			// 왼쪽 스시를 빼고 만약 해당 번호의 마지막 스시였다면 result 1감소
			if (--pick[susi[i]] == 0)
				result--;

			// 오른쪽 스시를 더하고 만약 처음 집는거면 result 1 증가
			if (pick[susi[i + k]]++ == 0)
				result++;

			// 쿠폰 스시를 이미 집었다면 result와 이전 최대 picked 비교
			if (pick[c] > 0)
				picked = Integer.max(result, picked);
			// 쿠폰 스시를 아직 안집었다면 result+1과 이전 최대 picked 비교
			else
				picked = Integer.max(result + 1, picked);
		}

		System.out.println(picked);
	}
}
