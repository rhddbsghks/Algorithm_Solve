package d0916;

import java.util.*;
import java.io.*;

public class SWEA_3307 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] LIS = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = 0;
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());

				for (int index = 0; index < N; index++)
					if (LIS[index] == 0 || LIS[index] > num) {
						LIS[index] = num;
						max = Math.max(max, index + 1);
						break;
					}
			}
			System.out.println("#" + t + " " + max);
		}
	}
}
