package d0904;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
백준 17471 게리맨더링

풀이법 : 
	N의 크기가 10 이하라 인접행렬을 사용했습니다.
	한 선거구에 m개를 뽑으면 반대쪽은 N-m으로 자동으로 결정되므로 N개 중 1개~N/2개를 뽑는 조합을 모두 계산했습니다.
	비트마스킹을 사용해 각 조합별 선택한 지역구 번호를 저장했고, 
	생성된 두 지역구를 각각 BFS 탐색하여 가지 못하는 지점이 있다면 배제하였습니다.
	
	그래프 문제는 처음 아이디어 떠올리기가 너무 힘드네요.
 */

public class BJ_17471 {
	static int n, min, area[];
	static boolean graph[][];

	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		graph = new boolean[n + 1][n + 1]; // 인접행렬
		area = new int[n + 1]; // 지역구 인구 배열
		min = Integer.MAX_VALUE;

		// 인구배열 초기화
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++)
			area[i] = Integer.parseInt(st.nextToken());

		// 인접행렬 초기화
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int adj = Integer.parseInt(st.nextToken());

			for (int j = 0; j < adj; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph[i][to] = true;
				graph[to][i] = true;
			}
		}

		// N개 중 1개, 2개 ... N/2개 뽑는 모든 조합 계산
		for (int i = 1; i <= +n / 2; i++)
			gerry(0, 1, 0, i);

		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}
	/****************** Main End ******************/

	
	/****************** Method ******************/

	// gerry: N개 중 choose개를 뽑는 조합
	static void gerry(int cnt, int start, int bit, int choose) {

		// 다 뽑았을 때
		if (cnt == choose) {
			int sum = 0;
			int other = 0;
			Set<Integer> A = new HashSet<Integer>();
			Set<Integer> B = new HashSet<Integer>();

			// 두 지역구 Set 생성 & 각각 합 계산
			for (int i = 1; i < n + 1; i++) {
				if ((bit & 1 << i) != 0) {
					sum += area[i];
					A.add(i);
				} else {
					other += area[i];
					B.add(i);
				}
			}

			// 두 지역구가 가능한 방법이라면 최솟값 갱신
			if (isPossible(A) && isPossible(B))
				min = Integer.min(Math.abs(other - sum), min);
			return;
		}

		for (int i = start; i < n + 1; i++)
			gerry(cnt + 1, i + 1, bit | 1 << i, choose);
	}

	// isPossible: 지역구가 가능한 방법인지 체크
	static boolean isPossible(Set<Integer> set) {
		boolean visited[] = new boolean[n + 1];

		// Set에서 포함하고 있는 첫 지역구 찾아서 BFS
		for (int i = 1; i < n + 1; i++) {
			
			// i 부터 BFS 시작
			if (set.contains(i)) {
				Queue<Integer> q = new ArrayDeque<Integer>();
				q.add(i);

				while (!q.isEmpty()) {
					int num = q.poll();
					visited[num] = true;
					set.remove(num); // Set에서 해당 지역구 제거
					
					for (int to = 1; to < n + 1; to++) {
						// Set 포함, 미방문, 현재 num에서 갈 수 있으면 큐에 추가
						if (set.contains(to) && !visited[to] && graph[num][to])
							q.add(to);
					}
				}
				
				// 제거하지 못한 지역구 남아있으면 불가능
				if (set.size() != 0)
					return false;
				return true;
			}
			// BFS End
		}
		return false;
	}
	/****************** Method End ******************/
}