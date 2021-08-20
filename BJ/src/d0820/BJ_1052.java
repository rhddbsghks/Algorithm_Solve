package d0820;

import java.util.Scanner;

/*
백준 1052 물병

풀이법 : n을 비트로 나타냈을 때 1의 갯수는 물을 모았을 때 물병의 최소 갯수이다.
		 만약 이 값이 k 보다 크다면 1씩 더해준다.(물병 사오기)
		 비트 갯수가 k보다 작아질 때 까지 반복한다.
		 
아주 신박한 문제네요.. 그래도 정수를 비트로도 보게 된 시간이었습니다.
 */


public class BJ_1052 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		int cnt = 0;

		while (k <= Integer.bitCount(n)) {
			n++;
			cnt++;
			if (k == Integer.bitCount(n))
				break;
		}

		System.out.println(cnt);
	}
}