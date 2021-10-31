package d1031;

import java.util.*;

/*
프로그래머스 17678 셔틀버스

풀이법 : 
	1. 정답은 막차시간 or 막차가 만석이라면 마지막 사람 -1분 입니다.
	2. timetable을 오름차순으로 정렬하고 차례로 버스를 태웁니다. 
	3. 마지막 버스 계산 시 자리가 있다면 정답은 막차시간이고, 만석이라면 마지막 사람 -1분이 정답입니다.

다른 풀이 중에 시작부터 시간을 int로 변환하고 푸는 방법도 괜찮아 보였네요.
 */

public class PG_17678 {

	public String solution(int n, int t, int m, String[] timetable) {
		Arrays.sort(timetable);
		int people = timetable.length; // 총 승객수
		int index = 0; // 사람 인덱스

		for (int i = 0; i < n; i++) {
			String start = addMinutes("09:00", t * i); // 출발시간 설정
			String[] bus = new String[m]; // 각 시간별 버스

			// 매 버스마다 최대 m명까지 태워본다.
			for (int j = 0; j < m; j++) {
				// 아직 사람이 남았고, 도착시간이 해당 버스 출발시간보다 이하일 경우만
				if (index < people && timetable[index].compareTo(start) <= 0)
					bus[j] = timetable[index++];
			}

			// 마지막 버스일 때 
			if (i == n - 1) {
				// 만석이 아니면 막차 출발시간 리턴
				if (bus[m - 1] == null)
					return start;
				// 만석이면 마지막 사람 -1분 리턴
				else
					return addMinutes(bus[m - 1], -1);
			}
		}
		return "";
	}

	// 문자열 시간에 분 더하기
	public String addMinutes(String time, int minutes) {
		int nowH = Integer.parseInt(time.substring(0, 2));
		int nowM = Integer.parseInt(time.substring(3, 5));
		String result = "";

		nowM += minutes;

		if (nowM >= 60) {
			int tmpM = nowM % 60;
			int tmpH = nowM / 60;
			nowM = tmpM;
			nowH += tmpH;
		} else if (nowM < 0) {
			nowM += 60;
			nowH -= 1;
		}

		return String.format("%02d:%02d", nowH, nowM);
	}
}
