package d0804;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;

/*
백준 1541 잃어버린 괄호
풀이법 : 더하기 끼리 괄호를 쳐주면 최솟값 계산됨. List<String>으로 수식 생성 후 뽑아가며 계산 
시간복잡도 : O(N)

파이썬은 몇줄 안에 끝나던데 
문자열을 바로 계산할 수 있는 신박한 아이디어는 없을까요..
 */

public class BJ_1541 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String exp = br.readLine();

		ArrayList<String> newExp = new ArrayList<String>();

		// 숫자 만들기용 SB
		StringBuilder num = new StringBuilder();

		newExp.add("(");
		for (int i = 0; i < exp.length(); i++) {
			// 연산자가 빼기면
			// 앞 괄호 닫고, 뒤 괄호 열고
			if (exp.charAt(i) == '-') {
				newExp.add(num.toString());
				newExp.add(")");
				newExp.add("-");
				newExp.add("(");
				num = new StringBuilder();

				// 더하기이면 생성한 SB 통째로 리스팅
				// 이후 '+' 리스팅
			} else if (exp.charAt(i) == '+') {
				newExp.add(num.toString());
				num = new StringBuilder();
				newExp.add("+");
			}

			// 숫자이면 '+' 나올 때 까지 SB에 추가
			else
				num.append(exp.charAt(i));
		}
		newExp.add(num.toString());
		newExp.add(")");

		System.out.print(calcString(newExp));

		/*
		 * Java6 이상부터 쓸 수 있는 Java Scripting API 
		 * 자바스크립트의 함수 호출 가능 문자열 식 만들고 바로 계산 때릴 수 있는데... 
		 * 이 문제에선 NullPoint 예외 뜸... 연산시간도 좀 걸리는 것 같긴 함
		 * 
		 * 
		 * StringBuilder sb = new StringBuilder();
		 * 
		 * sb.append("("); for (int i = 0; i < exp.length(); i++) {
		 * 
		 * if (exp.charAt(i) == '-') {
		 * 
		 * sb.append(")"); sb.append(exp.charAt(i)); sb.append("("); } else
		 * sb.append(exp.charAt(i)); } sb.append(")");
		 * 
		 * 
		 * ScriptEngineManager s = new ScriptEngineManager(); ScriptEngine engine =
		 * s.getEngineByName("JavaScript");
		 * System.out.println((int)engine.eval(sb.toString));
		 * 
		 */
	}

	static int calcString(ArrayList<String> exp) {
		int sum = 0;
		int tmp = 0;
		boolean firstMinus = true; // 첫 '-' 판별 flag

		for (String s : exp) {
			// 여는 괄호와 '+'는 무시
			if (s.equals("(") || s.equals("+")) {
			}

			// 괄호 닫히면 괄호안의 합 tmp 빼주기
			// But, 첫 괄호이면 더하기
			else if (s.equals(")")) {
				if (!firstMinus)
					sum -= tmp;
				else
					sum += tmp;
			}

			// '-'면 괄호합 tmp 초기화
			else if (s.equals("-")) {
				tmp = 0;
				firstMinus = false;
			}

			// 괄호 닫힐 때 까지 더해놓기
			else {
				tmp += Integer.parseInt(s);
			}
		}
		return sum;
	}
}
