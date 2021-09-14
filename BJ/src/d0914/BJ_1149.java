package d0914;

import java.io.*;

public class BJ_1149 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String[] input = br.readLine().split(" ");
		int[] pre = new int[3];
		int[] rgb = new int[3];

		for (int j = 0; j < 3; j++)
			pre[j] = Integer.parseInt(input[j]);

		for (int i = 1; i < n; i++) {
			input = br.readLine().split(" ");

			rgb[0] = Integer.parseInt(input[0]) + Integer.min(pre[1], pre[2]);
			rgb[1] = Integer.parseInt(input[1]) + Integer.min(pre[2], pre[0]);
			rgb[2] = Integer.parseInt(input[2]) + Integer.min(pre[1], pre[0]);
			pre = rgb.clone();
		}
		
		System.out.println(Integer.min(rgb[0], Integer.min(rgb[1], rgb[2])));
	}
}