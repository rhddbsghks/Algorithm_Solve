package d0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_16926 {

	static int[][] arr;
	static int n, m;
	static int dep;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		int r = Integer.parseInt(input[2]);
		arr = new int[n][];

		// 배열 생성
		for (int i = 0; i < n; i++)
			arr[i] = Arrays.asList(br.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();

		// 재귀 depth
		dep = Math.min(n, m) / 2 - 1;

		// r번 회전
		for (int i = 0; i < r; i++)
			rotate(0);

		print();
	}

	static void rotate(int depth) {
		int tmp = arr[depth][depth];

		for (int i = 0 + depth; i < m - depth - 1; i++)
			arr[depth][i] = arr[depth][i + 1];

		for (int i = 0 + depth; i < n - depth - 1; i++)
			arr[i][m - depth - 1] = arr[i + 1][m - depth - 1];

		for (int i = m - depth - 1; i > 0 + depth; i--)
			arr[n - depth - 1][i] = arr[n - depth - 1][i - 1];

		for (int i = n - depth - 1; i > 0 + depth + 1; i--)
			arr[i][depth] = arr[i - 1][depth];

		arr[depth + 1][depth] = tmp;
		if (depth >= dep)
			return;

		rotate(depth + 1);
	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				sb.append(arr[i][j] + " ");
			sb.append("\n");
		}

		System.out.println(sb);
	}
}