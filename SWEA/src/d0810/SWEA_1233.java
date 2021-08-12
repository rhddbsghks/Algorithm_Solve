package d0810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1233 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("src/d0810/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			int val = 1;
			for (int i = 0; i < n; i++) {
				String line = br.readLine();
				String[] linearr = line.split(" ");
//				System.out.println(Arrays.toString(linearr));
				if (!isOperator(linearr[1]) && linearr.length > 2)
					val = 0;

			}
			System.out.println("#" + t + " " + val);
		}
	}

	static boolean isOperator(String s) {
		try {
			if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
