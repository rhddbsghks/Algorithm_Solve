package d0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
백준 11339 ATM

풀이법: 출금 순서가 짧은 사람 순으로 진행한다면 대기 시간이 최소가 될 수 있음.

 */

public class BJ_11399 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");

		int[] wait = new int[n];
		for (int i = 0; i < n; i++)
			wait[i] = Integer.parseInt(input[i]);

		Arrays.sort(wait);

		int time = 0;
		int weight = 1;
		for (int i = n - 1; i >= 0; i--)
			time += wait[i] * weight++;

		System.out.println(time);
		br.close();
	}
}