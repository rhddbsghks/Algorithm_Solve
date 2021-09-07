package d0907;

/*
프로그래머스 67256 키패드 누르기

풀이법 : 
		 1. 각 숫자패드 인덱스에 맞춰서 pos[] 값을 저장해둡니다.
		 2. 왼손과 오른손의 현재 좌표를 저장해두고 차례로 진행합니다.
		 
 */


public class PG_67256 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 }, "right"));
	}

	static String solution(int[] numbers, String hand) {
		StringBuilder sb = new StringBuilder();
		
		// 숫자키패드 좌표 0~9
		int[][] posN = new int[][] { { 3, 1 }, { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 },
				{ 2, 1 }, { 2, 2 } };
				
		int[] posL = new int[] { 3, 0 }; // 왼손 현재 좌표
		int[] posR = new int[] { 3, 2 }; // 오른손 현재 좌표

		for (int num : numbers) {

			switch (line(num)) {

			case 'L':
				sb.append("L");
				posL = posN[num];
				break;
			case 'R':
				sb.append("R");
				posR = posN[num];
				break;
			case 'C':
				int ld = dist(posN[num], posL);
				int rd = dist(posN[num], posR);

				// 왼손이 가까움
				if (ld < rd) {
					sb.append("L");
					posL = posN[num];
					break;
				}
				
				// 오른손이 가까움
				else if (rd < ld) {
					sb.append("R");
					posR = posN[num];
					break;
				}

				// 거리가 같을 때
				if (hand.equals("right")) {
					sb.append("R");
					posR = posN[num];
					break;
				} else {
					sb.append("L");
					posL = posN[num];
					break;
				}
			}
		}
		return sb.toString();
	}

	// line 숫자패드 라인 정보
	static char line(int num) {
		if (num == 1 || num == 4 || num == 7)
			return 'L';

		else if (num == 3 || num == 6 || num == 9)
			return 'R';
		else
			return 'C';
	}

	static int dist(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
}
