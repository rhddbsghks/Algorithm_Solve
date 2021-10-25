package d1025;

/*
프로그래머스 67258

풀이법 : 
	1. 시간을 초로 환산하여 timeTable을 생성합니다.
	2. timeTable[x] = x-1초 ~ x초 사이의 시청자수로 계산합니다. 
	3. 이후 투포인터를 사용하여 광고윈도우를 옮겨가며 최댓값을 갱신합니다.
	
풀이 방향은 잡았으나 구현에 막혀 구신의 도움을...
도대체 이런 구현은 어떻게 하는걸까요
 */

public class PG_72414 {

	public static String solution(String play_time, String adv_time, String[] logs) {
		// 초로 환산한 타임테이블 생성(최대 360000)
		int[] timeTable = new int[timeToSec(play_time) + 1];

		// timeTable[x] = x-1초 ~ x초인 테이블 생성
		for (String time : logs) {
			String[] log = time.split("\\-");
			int start = timeToSec(log[0]);
			int end = timeToSec(log[1]);
			timeTable[start] += 1;
			timeTable[end] -= 1;
		}
		for (int i = 1; i < timeTable.length; i++)
			timeTable[i] += timeTable[i - 1];

		// 윈도우 옮겨보기
		int advStart = 0, advEnd = 0,  start=0, advLen = timeToSec(adv_time);
		long max = 0;
		
		// 첫 윈도우 세팅
		for(;advEnd<advLen;advEnd++) 
			max+=timeTable[advEnd];

		long sum = max;
		
		// 끝점이 다다를때까지 탐색 및 최댓값 갱신
		while (advEnd < timeTable.length-1) {
			sum-=timeTable[advStart++];
			sum+=timeTable[advEnd++];
			
			if(max<sum) {
				max=sum;
				start=advStart;
			}
		}

		return secToTime(start);
	}

	static int timeToSec(String time) {
		String[] t = time.split("\\:");
		return 3600 * Integer.parseInt(t[0]) + 60 * Integer.parseInt(t[1]) + Integer.parseInt(t[2]);
	}
	
	static String secToTime(int sec) {
		String time = "";
		
		int hour = sec/3600;
		sec-=3600*hour;
		int minute = sec/60;
		sec-=60*minute;
		
		if(hour<10)
			time+="0"+hour+":";
		else
			time+=hour+":";
		
		if(minute<10)
			time+="0"+minute+":";
		else
			time+=minute+":";
		
		if(sec<10)
			time+="0"+sec;
		else
			time+=sec;
		
		return time;
	}
}