package d0816;

import java.util.Scanner;

/*
백준 2851 슈퍼마리오

풀이법: 차례대로 점수를 누적하며 현재 합과 다음을 먹었을 때 합을 비교해가며 진행
 */

public class BJ_2851 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] mush = new int[10];

		for (int i = 0; i < 10; i++)
			mush[i] = sc.nextInt();

		int score = mush[0];

		for (int i = 1; i < 10; i++) {
			int nextScore = score + mush[i];
			
			if (Math.abs(100 - score) >= Math.abs(100 - (nextScore))) {
				score += mush[i];
			}
			if (nextScore > 100)
				break;
		}

		System.out.println(score);
	}
}