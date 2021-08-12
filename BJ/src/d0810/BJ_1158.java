package d0810;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BJ_1158 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		Queue<Integer> numList = new ArrayDeque<Integer>();

		for (int i = 1; i <= n; i++)
			numList.offer(i);

		sb.append("<");

		while (!numList.isEmpty()) {
			for (int i = 1; i < k; i++)
				numList.offer(numList.poll());
			sb.append(numList.poll() + ", ");
		}

		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb);
		sc.close();
	}
}
