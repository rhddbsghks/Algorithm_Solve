package d0929;

import java.util.*;
import java.io.*;

/*
백준 14719 빗물

풀이법 : 
	 1. 제일 끝점들은 빗물이 찰 수 없으므로 i=1~w-1 까지 탐색합니다.
	 2. 해당 인덱스에서 가능한 빗물 양은 왼쪽 중 최고높이, 오른쪽 중 최고높이 중 최솟값-현재블록높이입니다.
	 3. 왼쪽 오른쪽을 각각 최고높이를 탐색하며 이때 현재 높이보다 높은 블록들만 탐색합니다.
		 
시간복잡도: O(N^2);
 */

public class BJ_14719 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int[] height = new int[w];
		int rain = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < w; i++)
			height[i] = Integer.parseInt(st.nextToken());

		// 끝점 빼고 탐색
		for (int i = 1; i < w - 1; i++) {
			int leftMax = -1;
			int rightMax = -1;

			// 내 왼쪽 블록들 중 나보다 높은 값들 중 최댓값
			for (int j = i - 1; j >= 0; j--)
				if (height[j] > height[i])
					leftMax = Integer.max(leftMax, height[j]);

			// 내 오른쪽 블록들 중 나보다 높은 값들 중 최댓값
			for (int j = i + 1; j < w; j++)
				if (height[j] > height[i])
					rightMax = Integer.max(rightMax, height[j]);

			// 양쪽 최고높이가 계산되었을때만 빗물 저장
			if (leftMax != -1 && rightMax != -1)
				rain += Integer.min(leftMax, rightMax) - height[i];
		}

		System.out.println(rain);
	}
}