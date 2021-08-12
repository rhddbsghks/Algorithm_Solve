package d0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_17406 {
	static int[][] arr;
	static int n, m, k;
	static int result = Integer.MAX_VALUE;
	static String[] cmds;
	static boolean isSelected[];
	static String[] order;
	static int[][] origin;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		k = Integer.parseInt(input[2]);
		arr = new int[n][m];
		origin = new int[n][m];

		for (int i = 0; i < n; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
				origin[i][j] = Integer.parseInt(input[j]);
			}
		}

		cmds = new String[k];
		isSelected = new boolean[k];
		order = new String[k];

		for (int i = 0; i < k; i++)
			cmds[i] = br.readLine();

		findMinValue(0);

		System.out.println(result);
	}

	static void findMinValue(int call) {
		if (call == k) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					arr[i][j] = origin[i][j];

			for (int i = 0; i < k; i++)
				rotate(order[i]);

			if (calMin() < result)
				result = calMin();
			return;
		}

		for (int i = 0; i < k; i++) {
			if (isSelected[i])
				continue;

			order[call] = cmds[i];
			isSelected[i] = true;
			findMinValue(call + 1);
			isSelected[i] = false;
		}
	}

	static void rotate(String cmd) {
		String[] input = cmd.split(" ");
		int startR = Integer.parseInt(input[0]) - Integer.parseInt(input[2]) - 1;
		int startC = Integer.parseInt(input[1]) - Integer.parseInt(input[2]) - 1;
		int endR = Integer.parseInt(input[0]) + Integer.parseInt(input[2]) - 1;
		int endC = Integer.parseInt(input[1]) + Integer.parseInt(input[2]) - 1;

		int endPoint = (endR - startR) / 2 + 1;
		int cnt = 0;

		while (true) {
			int tmp = arr[startR + cnt][startC + cnt];

			for (int i = startR + cnt; i < endR - cnt; i++)
				arr[i][startC + cnt] = arr[i + 1][startC + cnt];

			for (int i = startC + cnt; i < endC - cnt; i++)
				arr[endR - cnt][i] = arr[endR - cnt][i + 1];

			for (int i = endR - cnt; i > startR + cnt; i--)
				arr[i][endC - cnt] = arr[i - 1][endC - cnt];

			for (int i = endC - cnt; i > startC + cnt; i--)
				arr[startR + cnt][i] = arr[startR + cnt][i - 1];

			if (cnt + 1 == endPoint) {
				if (endR - startR % 2 == 1)
					arr[startR + cnt][startC + cnt + 1] = tmp;
				break;
			}

			arr[startR + cnt][startC + cnt + 1] = tmp;
			cnt++;
		}
	}

	static int calMin() {
		int min = Integer.MAX_VALUE;

		for (int[] ar : arr) {
			int sum = 0;
			for (int a : ar)
				sum += a;
			if (sum < min)
				min = sum;
		}
		return min;
	}
}
