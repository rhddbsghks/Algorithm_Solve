package d0805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_5432 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/d0805/input5432.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			int num = 0;
			int stick = 0;
			char[] input = br.readLine().toCharArray();

			for (int i = 0; i < input.length; i++) {

				if (input[i] == '(' && input[i + 1] == ')') {
					num += stick;
					i++;
				}

				else if (input[i] == '(') {
					stick += 1;
					num += 1;
				} else
					stick -= 1;
			}
			System.out.println("#" + t + " " + num);
		}
	}
}