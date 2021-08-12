package d0804;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_9012 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Stack<Character> st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		LOOP: for (int t = 0; t < n; t++) {
			st = new Stack<Character>();
			char[] str = br.readLine().toCharArray();

			for (int i = 0; i < str.length; i++) {
				if (str[i] == '(')
					st.push('(');

				else {
					if (st.empty()) {
						System.out.println("NO");
						continue LOOP;
					}
					st.pop();
				}
			}

			if (st.empty())
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
