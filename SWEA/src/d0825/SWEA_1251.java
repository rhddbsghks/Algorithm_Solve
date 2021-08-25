package d0825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class SWEA_1251 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= testcase; t++) {
			sb.append("#" + t + " ");

			int n = Integer.parseInt(br.readLine());
			long[][] island = new long[n][n];

			int[] x = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] y = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			double rate = Double.parseDouble(br.readLine());

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					island[i][j] = (long) Math.abs(x[i] - x[j]) * Math.abs(x[i] - x[j])
							+ (long) Math.abs(y[i] - y[j]) * Math.abs(y[i] - y[j]);

			long[] distance = new long[n];
			boolean[] visited = new boolean[n];
			double result = 0;

			Arrays.fill(distance, Long.MAX_VALUE);
			distance[0] = 0;

			for (int i = 0; i < n; i++) {
				long min = Long.MAX_VALUE;
				int minIdx = -1;

				for (int j = 0; j < n; j++)
					if (!visited[j] && min > distance[j]) {
						min = distance[j];
						minIdx = j;
					}
				visited[minIdx] = true;
				result += rate * distance[minIdx];

				for (int j = 0; j < n; j++)
					if (!visited[j] && island[minIdx][j] != 0 && distance[j] > island[minIdx][j])
						distance[j] = island[minIdx][j];

			}
			sb.append(Math.round(result) + "\n");
		}
		System.out.println(sb);
	}
}
