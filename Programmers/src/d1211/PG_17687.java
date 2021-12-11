package d1211;


/*
프로그래머스17687 n진수 게임

풀이법 : 
	 1. t개의 문자를 구할때까지 나올 수들을 String에 미리 저장합니다.
	 2. 튜브 순서 p에 맞게 해당문자들을 더해줍니다.

상당히 비효율적인거 같긴 합니다...
 */


public class PG_17687 {

	public static void main(String[] args) {
		System.out.println(solution(2, 4, 2, 1));
	}

	public static String solution(int n, int t, int m, int p) {
		String game = "";
		String tube = "";

		// t*m개의 숫자를 n진수로 변환해서 전체 문장 생성
		for (int i = 0; i < t * m; i++)
			game += Integer.toString(i, n).toUpperCase();

		char tmp[] = game.toCharArray();

		// 튜브 순서에 맞는 문자들 추출
		for (int i = 0; i < t; i++) 
			tube += tmp[(p - 1) + i * m];

		return tube;
	}
}
