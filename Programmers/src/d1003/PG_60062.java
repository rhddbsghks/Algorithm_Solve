package d1003;

import java.util.*;

/*
프로그래머스 60062 외벽 점검

풀이법 : 
	1. n이 200으로 충분히 작기 때문에 완전 탐색으로 해결하였습니다.
	2. 취약지점을 일렬로 놓고 0번인덱스를 시작점으로 하는 경우부터 마지막 취약점을 시작점으로 하는 모든 경우의 수를 탐색합니다.
	3. 매번 탐색을 할 때 dist 배열 또한 가능한 모든 순열을 고려해봅니다.
	4. check 함수는 각 경우별 최솟값을 반환하고 점검에 실패하면 MAX_VALUE를 반환합니다.
	5. 모든 경우의 수를 탐색하고 갱신된 최솟값을 출력합니다.
	
해설을 참고해서 풀어도 어렵네요...
 */

public class PG_60062 {

	public static void main(String[] args) {
		System.out.println(solution(200, new int[] { 0, 10, 50, 80, 120, 160 }, new int[] { 1, 10, 5, 40, 30 }));
	}

	static int solution(int n, int[] weak, int[] dist) {
		List<Integer> wall = new ArrayList<Integer>(); // 입력 weak[]를 List 형으로 저장
		for (int i = 0; i < weak.length; i++)
			wall.add(weak[i]);

		int answer = Integer.MAX_VALUE;
		Arrays.sort(dist);

		for (int i = 0; i < weak.length; i++) {

			// Next-Permutation을 사용해 가능한 dist 순열을 모두 고려
			do {
				answer = Integer.min(answer, check(wall, dist));
			} while (np(dist));

			Arrays.sort(dist); // 다음 NP를 위해 dist 정렬
			wall.add(wall.remove(0) + n); // 시작점으로 한 지점을 n만큼 더하고 리스트 끝으로 보냄
		}

		if (answer == Integer.MAX_VALUE)
			return -1;

		return answer;
	}

	// check: 해당 취약지점 리스트와 dist 순열로 점검가능한 최소 인원 반환
	static int check(List<Integer> wall, int[] dist) {
		int n = dist.length - 1;
		int result = 0;
		int covered = -1; // 커버된 마지막 지점
		int start = 0; // 점검 시작 지점

		while (n >= 0) {
			// 점검된 지점의 바로 다음 값이 시작 지점이다.
			for (int w : wall) {
				start = w;
				if (covered < w)
					break;
			}

			covered = start + dist[n]; // 점검완료된 지점 갱신
			result++;
			n--;

			// 만약 모든 값이 커버됐으면 소요된 친구 반환
			if (covered >= wall.get(wall.size() - 1))
				return result;
		}
		
		// 점검 실패
		return Integer.MAX_VALUE;
	}

	// Next-Permutaion
	static boolean np(int[] arr) {
		int n = arr.length;
		int i = n - 1;

		while (i > 0 && arr[i - 1] >= arr[i])
			i--;

		if (i == 0)
			return false;

		int j = n - 1;

		while (arr[i - 1] >= arr[j])
			j--;

		int tmp = arr[j];
		arr[j] = arr[i - 1];
		arr[i - 1] = tmp;

		Arrays.sort(arr, i, n);
		return true;
	}
}