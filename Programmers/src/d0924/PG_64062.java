package d0924;

import java.util.*;

/*
프로그래머스 64062 징검다리 건너기

풀이법 : 
	1. 우선순위큐에 int[] {디딤돌 횟수, 인덱스}를 저장하고 디딤돌 횟수로 오름차순 정렬합니다.
	2. 거리를 저장하는 dist 배열을 선언합니다. (dist[i]는 stones[i]까지 필요한 거리, 마지막 도착지도 고려해야하므로 크기는 stones사이즈+1)
	3. dist배열은 1로 초기화합니다.
	4. pq.peek()로 제일 적은 디딤돌 횟수를 체크합니다.
	5. 건널 수 있는 친구수 = 디딤돌 횟수 - 이미 건넌 친구수입니다.
	6. 같은 횟수를 가지는 디딤돌을 모두 빼가며 dist 배열을 갱신해줍니다.
	7. dist를 갱신하다가 이 값이 k를 넘어가면 flag를 true로 바꾸고 현재 건널수 있는 친구수까지 더해주고 종료합니다.
	
시간복잡도: 우선순위큐 정렬(NlogN)

먹방라이브 문제랑 비슷하게 생각하고 풀어봤습니다.
 */

public class PG_64062 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 3, 1, 1, 1, 2, 1, 1, 4 }, 4));
	}

	static int solution(int[] stones, int k) {
		int friends = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
		int dist[] = new int[stones.length + 1];
		boolean flag = false; // 거리가 k 넘어가는지 체크

		// pq에 {횟수, 인덱스} 저장
		for (int i = 0; i < stones.length; i++)
			pq.add(new int[] { stones[i], i });

		Arrays.fill(dist, 1); // 모든 거리 1로 초기화

		while (!pq.isEmpty() && !flag) {
			int weight = pq.peek()[0]; // 최소 횟수 디딤돌
			int canGo = weight - friends; // 건널 수 있는 친구 = 디딤돌 횟수 - 이미 건넌 친구 수

			// 같은 weight인 돌들 빼가며 탐색
			while (!pq.isEmpty() && pq.peek()[0] == weight) {
				int[] nowStone = pq.poll();
				int index = nowStone[1]; // 현재 돌 인덱스
				stones[index] = 0; // 돌 횟수 0으로 변경
				int prevDist = dist[index]; // 지금 돌까지의 거리

				// 다음 돌 인덱스 찾기
				while (true) {
					index++;
					// 도착지 인덱스이거나 stones[index] 값이 0이 아니면(다음 돌)이면 break
					if (index == stones.length || stones[index] != 0)
						break;
				}

				dist[index] += prevDist; // 다음 돌까지 거리에 prevDist 추가 

				// 만약 이 거리가 k보다 커지면 다음 친구들은 올 수 없다.
				if (dist[index] > k) {
					flag = true;
					break;
				}
			}
			
			friends += canGo; // 돌들 다 처리하고 canGo만큼 friends 추가
		}
		
		return friends;
	}
}