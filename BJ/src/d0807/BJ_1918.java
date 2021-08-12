package d0807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
백준 1918 후위표기식
풀이법 : 연산자들의 우선순위를 고려하고 스택을 사용해 식 변환

 */

public class BJ_1918 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] exp = br.readLine().toCharArray();
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();

		// 입력된 식 하나씩 확인
		for (char e : exp) {
			// 알파벳이면 바로 추가
			if (!isOperator(e))
				sb.append(e);

			// 연산자일 때
			else {
				// 연산자 스택이 비어있으면 스택에 추가
				if (stack.isEmpty())
					stack.push(e);

				else {
					switch (e) {
					// *나 /일 때
					case '*':
					case '/':
						// 우선순위가 같은 *나 /는 pop하여 추가
						if (stack.peek() == '*' || stack.peek() == '/')
							sb.append(stack.pop());
						stack.push(e);
						break;

					case '+':
					case '-':
						// +와 -는 우선순위가 낮으므로 ( 나오기 전까지 pop하여 추가
						while (!stack.isEmpty() && stack.peek() != '(')
							sb.append(stack.pop());
						stack.push(e);
						break;

					case '(':
						stack.push(e);
						break;
						
					case ')':
						// ( 나올 때까지 pop하여 추가
						while (!stack.isEmpty() && stack.peek() != '(')
							sb.append(stack.pop());
						stack.pop();  // 스택에서 ( 제거
						break;
					}
				}
			}
		}
		// 스택에 남은 연산자 추가
		while (!stack.isEmpty())
			sb.append(stack.pop());
		System.out.println(sb.toString());
	}

	// 연산자 여부 체크
	static boolean isOperator(char o) {
		if (o == '+' || o == '-' || o == '*' || o == '/' || o == '(' || o == ')')
			return true;
		return false;
	}
}
