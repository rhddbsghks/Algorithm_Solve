package d1025;

import java.util.*;

/*
프로그래머스 67258

풀이법 : 
	1. gems 배열을 한번 탐색하여 Map에 저장합니다. 
	2. 투포인터를 사용하여 탐색하고 Map에 보석을 고른 횟수를 저장합니다.
	3. 보석 종류가 모자라면 end를 증가시키며 하나씩 집습니다.
	4. 보석 종류를 모두 집으면 start를 올려가며 보석 종류가 모자랄 때까지 앞쪽 보석을 빼줍니다.
	5. start를 올려가다가 다시 보석 종류가 모자라지면 start~end까지 길이와 구간을 갱신해줍니다. 
	
 */


public class PG_67258 {

	public int[] solution(String[] gems) {
		int[] answer = null;

		Map<String, Integer> picked = new HashMap<>();
		for (String gem : gems) 
			picked.put(gem, 0);

		int min = Integer.MAX_VALUE;
		int n = picked.size(); // 집을 보석 종류
		int gemCnt = 0;
		int start = 0, end = 0;
		boolean isEnd = false;
		
		while (true) {
			// 아직 보석이 모자라면 
			if (gemCnt < n) {
				if (end < gems.length) {
					// end의 보석을 처음 집으면 gemCnt 카운트
					if (picked.get(gems[end]) == 0) 
						gemCnt++;
					
					// Map에 해당 보석 카운트 증가
					picked.put(gems[end], picked.get(gems[end]) + 1);
					end++;
					continue;
				}
				
				// 마지막 보석을 집었으면 더이상 집을 필요없다.
				isEnd = true;
			}

			// 모든 종류 보석 다 집었으면
			if (gemCnt == n) {
				// 앞쪽 보석을 버리며 집은 횟수를 감소시켜준다.
				picked.put(gems[start], picked.get(gems[start]) - 1);
				
				// 만약 버린게 해당 종류의 마지막 보석이었으면 현재 구간의 최소값이다.
				if(picked.get(gems[start])==0) {
					gemCnt--;
					
					int len = end - start;

					// 현재 구간의 길이를 체크하고 구간을 갱신해준다.
					if (min > len) {
						min = len;
						answer = new int[] { start+1, end };
					}
				}
				start++;
			}
			if(isEnd)
				break;
		}
		return answer;
	}
}