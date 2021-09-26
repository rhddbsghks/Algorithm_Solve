package d0926;

/*
프로그래머스 17676 추석 트래픽

풀이법 : 
	1. 시간계산을 쉽게 하기 위해 1 Millisecond를 1로치환하여 계산하였습니다.
	2. start, end 2개의 int 배열을 선언하여 각 트래픽들의 처리 시작 시간과 종료 시간을 저장합니다.
	3. 트래픽 종료 시간 순으로 정렬되어 있기 때문에 차례대로 탐색을 시작합니다.
	4. 트래픽 종료 시간 + 999(0.999초)를 구간의 끝점으로 잡고 이후 트래픽들의 시작 시간이 끝점 이하이면 같은 구간으로 볼 수 있습니다.
	5. 완전탐색하여 최댓값을 갱신합니다.
	
시간복잡도: O(N^2), N이 2000 이하이므로 2중 for문으로 해결하였습니다.
 */

public class PG_17676 {

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s" }));
	}

	static int solution(String[] lines) {
		int n = lines.length;
		int[] start = new int[n]; // 트래픽 시작 시간
		int[] end = new int[n]; // 트래픽 종료 시간
		int max = 0;

		for (int i = 0; i < n; i++) {
			String s[] = lines[i].split(" ")[1].split(":|\\."); // hh:mm:ss.sss
			String t[] = lines[i].split(" ")[2].split("\\.|s"); // X.XXXs

			// 응답완료 시간값을 정수형으로 치환
			int time = 3600000 * Integer.parseInt(s[0]) + 60000 * Integer.parseInt(s[1]) + 1000 * Integer.parseInt(s[2])
					+ Integer.parseInt(s[3]);

			// 처리시간을 정수형으로 치환
			int processTime = 1000 * Integer.parseInt(t[0]);
			if (t.length == 2) // 처리시간에 밀리초 값이 포함되어 있다면
				processTime += (int) Math.pow(10, 3 - t[1].length()) * Integer.parseInt(t[1]);

			start[i] = time - processTime + 1; // 시작시간 = 종료시간 - 처리시간 + 0.001초
			end[i] = time;
		}

		for (int i = 0; i < n; i++) {
			int sectionEnd = end[i] + 999;
			int cnt = 1;

			for (int j = i + 1; j < n; j++)
				if (start[j] <= sectionEnd)
					cnt++;

			max = Integer.max(max, cnt);
		}
		return max;
	}
}