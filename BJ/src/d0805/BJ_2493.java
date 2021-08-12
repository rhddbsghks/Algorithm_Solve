package d0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2493 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] tower = new int[n];
		Stack<Integer> stack = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			tower[i] = Integer.parseInt(st.nextToken());

			while (!stack.isEmpty()) {
				if (tower[stack.peek()] < tower[i])
					stack.pop();
				else {
					sb.append(stack.peek() + 1 + " ");
					break;
				}

			}

			if (stack.isEmpty())
				sb.append(0 + " ");

			stack.push(i);
		}

		System.out.println(sb.toString());
	}
}
