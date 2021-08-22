package d0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
백준 1759 암호 만들기

풀이법 : 얼핏 보면 순열 같지만, 암호는 항상 오름차순 이므로 조합으로 봐야한다.
		 패스워드 조합을 구해보고 자음과 모음 개수가 조건에 맞지 않으면 가지를 치며 진행한다.
		 근데 이번 문제는 가지 치는 의미가 크게 없네요.
		 
시간복잡도: 최대 15C7..?   대략 6500 미만
 */

public class BJ_1759 {
	static int l, c;
	static StringBuilder sb;
	static String[] input;
	static String[] pw;

	
	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");

		// l, c 초기화 및 알파벳 저장
		l = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);
		input = br.readLine().split(" ");

		sb = new StringBuilder();
		pw = new String[l];

		Arrays.sort(input); // 알파벳들 오름차순 정렬

		makePassword(0, 0, 0, 0); // 패스워드 조합 시도

		System.out.println(sb);
	}
	/****************** Main End ******************/

	
	
	/****************** Method ******************/
	static void makePassword(int cnt, int start, int vowel, int consonant) {
		if (cnt == l) {
			sb.append(String.join("", pw) + "\n");
			return;
		}

		for (int i = start; i < c; i++) {
			pw[cnt] = input[i];

			// 해당 문자가 모음이고, 추가했을 때 모음 개수가 l-1이라면 자음 최소 2개를 만족할 수 없다.
			if (isVowel(pw[cnt]) && (vowel + 1) < l - 1)
				makePassword(cnt + 1, i + 1, vowel + 1, consonant);

			// 해당 문자가 자음이고, 추가했을 때 자음 개수가 l개이면 진행하지 않는다.
			else if (!isVowel(pw[cnt]) && (consonant + 1) < l)
				makePassword(cnt + 1, i + 1, vowel, consonant + 1);
		}
	}

	static boolean isVowel(String str) {
		if (str.equals("a") || str.equals("e") || str.equals("i") || str.equals("o") || str.equals("u"))
			return true;
		return false;
	}
	/****************** Method End ******************/
}