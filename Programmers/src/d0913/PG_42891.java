package d0913;

import java.util.*;

/*
프로그래머스 42891 무지의 먹방라이브

풀이법 : 
	1. 음식 인덱스, 양을 저장한 int[]를 우선순위큐에 음식 양 오름차순으로 저장합니다.
	2. 1 Cycle을 현재 우선순위큐의 크기 즉, 남은 음식수로 생각합니다.
	3. 1 Cycle은 우선순위큐의 제일 앞 음식의 양(제일 적은 양)만큼 돌릴 수 있습니다.(=maxCycle)
	4. maxCycle 만큼 반복하여 남은시간 k에서 Cycle을 뺴줍니다.
	5. 이때 음식 크기만큼 값을 가지는 boolean[]을 사용해 남은 음식 여부를 관리합니다.
	6. 만약 maxCycle 만큼 모두 반복했으면 우선순위큐에서 해당 음식을 빼주고, 큐의 크기, 음식들의 남은 양이 바뀌므로 Cycle과 최대횟수인 maxCycle 값이 갱신됩니다.
	7. 도중에 Cycle 크기보다 남은 시간 k가 작다면 인덱스 0부터 탐색하여 k번 째 음식이 다음 음식이고 이때 boolean[]을 참조하여 남은 음식만 체크합니다.
		 
 */


public class PG_42891 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 3, 1, 1, 1, 2, 4, 3 }, 12));
	}

	static int solution(int[] food_times, long k) {
		boolean[] remain = new boolean[food_times.length]; // 남은 음식 여부
		Arrays.fill(remain, true); // true로 초기화

		// 음식을 {인덱스, 양}으로 우선순위 큐에 저장하고 음식의 양 오름차순으로 정렬
		PriorityQueue<int[]> meokbang = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		for (int i = 0; i < food_times.length; i++)
			meokbang.add(new int[] { i, food_times[i] });

		int start = 0;
		int totalCycle = 0; // 총 사이클 돌린 횟수;  남은 음식의 양 = 기존 양 - totalCycle

		LOOP: while (true) {
			// 시간이 남았으나 음식 배열이 비어있다면 -1 리턴
			if (meokbang.isEmpty())
				return -1;

			int cycleSize = meokbang.size(); // 1 Cycle의 크기는 남아있는 음식 개수
			int maxCycle = meokbang.peek()[1] - totalCycle; // Cycle을 최대로 돌릴 수 있는 횟수 = 제일 적은 음식 양

			// maxCycle 만큼 시도한다.
			for (int i = 0; i < maxCycle; i++) {
				// 1 Cycle <= k 이면 음식을 한바퀴 돌며 1번씩 다 먹고 다시 0번으로 돌아온다.
				if (cycleSize <= k) {
					totalCycle++;
					k -= cycleSize;
					continue;
				}

				// 1 Cycle > k 이면 0번 음식부터 전체 음식을 탐색해본다.
				for (start = 0; start < food_times.length; start++) {
					// 더 이상 남은 시간이 없다면
					if (k == 0) {
						// 다음 남은 음식 인덱스까지 ++
						while (true) {
							if (remain[start])
								break;
							start++;
						}
						break;
					}
					
					// remain 배열을 참조해 음식이 남아 있을 때만 시간 k를 뺀다.
					if (remain[start])
						k--;
				}
				break LOOP;
			}
			remain[meokbang.poll()[0]] = false;
		}

		// 모든 음식을 먹고도 시간이 남으면 -1 리턴
		if (k > 0)
			return -1;

		// start 인덱스 음식이므로 + 1 해줌
		return start + 1;
	}
}