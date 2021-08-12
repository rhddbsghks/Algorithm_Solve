package d0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2563 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[][] check = new boolean[100][100];
		int wide = 0;
		for (int t = 0; t < n; t++) {
			String[] input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);

			for (int i = x; i < x + 10; i++)
				for (int j = y; j < y + 10; j++)
					if (check[i][j] == false) {
						wide++;
						check[i][j] = true;
					}
		}

		System.out.println(wide);
	}
}