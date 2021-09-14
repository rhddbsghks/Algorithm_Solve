package d0914;

import java.util.*;

public class BJ_1463 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] answer = new int[n + 1];

		answer[1] = 0;
		answer[2] = 1;
		answer[3] = 1;

		for (int i = 4; i <= n; i++)
			answer[i] = Integer.min(answer[i - 1] + 1,
					Integer.min(answer[i / 2] + i % 2 + 1, answer[i / 3] + i % 3 + 1));

		System.out.println(answer[n]);
	}
}