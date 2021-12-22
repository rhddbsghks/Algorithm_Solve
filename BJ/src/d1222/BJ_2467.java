package d1222;

import java.util.*;
import java.io.*;

/*
백준 2467 용액

풀이법 : 
	 1. 투포인터를 사용해 용액 배열을 탐색합니다.
	 2. 합이 음수일 때는 left++, 양수일 때는 right--를 해줍니다.
	 3. 합의 절댓값이 최소일 때 용액 값을 갱신해줍니다. 

 */

public class BJ_2467 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int sum = Integer.MAX_VALUE;
		int[] solution = new int[n]; // 용액 배열
		int[] answer = new int[2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			solution[i] = Integer.parseInt(st.nextToken());

		int left = 0, right = n - 1;
		while (left != right) {
			int tmp = solution[left] + solution[right];
			
			// 두 용액 합 절댓값이 기존 최솟값보다 작을 때
			if(Math.abs(tmp)<Math.abs(sum)) {
				// 두 용액 값 갱신
				answer[0]=solution[left];
				answer[1]=solution[right];
				sum = tmp;
				if(tmp==0)
					break; // 합이 0이면 바로 종료
			}
			
			if(tmp<0)
				left++;
			else
				right--;
		}
		
		System.out.println(answer[0]+" "+answer[1]);
	}
}
