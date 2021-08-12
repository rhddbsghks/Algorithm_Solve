package d0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEWA_9229 {
	static int n;
	static int m;
	static int[] snack;
	static boolean[] isSelected;
	static int maxWeight;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= testcase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			maxWeight = -1;
			snack = new int[n];
			isSelected = new boolean[n];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++)
				snack[i] = Integer.parseInt(st.nextToken());

			snack(0, 0);

			sb.append("#" + tc + " " + maxWeight+"\n");
		}
		System.out.println(sb.toString());

	}

	static void snack(int i, int selectedNum) {
		if (i == n & selectedNum < 2)
			return;

		if (selectedNum == 2) {
			int sum = 0;

			for (int idx = 0; idx < n; idx++)
				if (isSelected[idx] == true)
					sum += snack[idx];

			if (sum <= m && sum > maxWeight)
				maxWeight = sum;

			return;
		}

		isSelected[i] = true;
		snack(i + 1, selectedNum + 1);

		isSelected[i] = false;
		snack(i + 1, selectedNum);
	}
}
