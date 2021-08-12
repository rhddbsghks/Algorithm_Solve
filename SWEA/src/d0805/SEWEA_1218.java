package d0805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SEWEA_1218 {
	static char[] bracket;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			bracket = br.readLine().toCharArray();
			System.out.println("#" + t + " " + isValid());
		}
	}

	static int isValid() {
		Stack<Character> st = new Stack<>();

		for (char b : bracket) {
			if (b == '(' || b == '[' || b == '{' || b == '<')
				st.push(b);

			else {
				if (st.isEmpty())
					return 0;

				if (b == ')' && st.pop() != '(')
					return 0;
				if (b == ']' && st.pop() != '[')
					return 0;
				if (b == '}' && st.pop() != '{')
					return 0;
				if (b == '>' && st.pop() != '<')
					return 0;
			}
		}
		if (st.isEmpty())
			return 1;

		return 0;
	}
}
