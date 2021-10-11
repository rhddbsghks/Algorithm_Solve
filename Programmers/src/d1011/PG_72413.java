package d1011;

/*
프로그래머스 72413 합승 택시 요금

풀이법 : 
	1. 플로이드워셜을 사용해 모든 쌍 최단 경로를 계산합니다.
	2. 출발지에서 a,b로 바로 가는 비용과 1~n번까지 합승한 뒤 a,b로 가는 비용들을 비교해 최소비용을 갱신합니다.
	
시간복잡도 O(N^3)
N이 200이하이므로 플로이들워셜 후 완전탐색을 해도 충분합니다.
 */

public class PG_72413 {

	int INF = 100000000; // 200 x 100,000 이상의 큰 값 설정

	int solution(int n, int s, int a, int b, int[][] fares) {

		int[][] dist = new int[n + 1][n + 1];

		// 모든 거리 INF로 초기화
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (i != j)
					dist[i][j] = INF;

		// 초기 비용 생성
		for (int[] fare : fares) {
			int from = fare[0];
			int to = fare[1];
			int w = fare[2];
			dist[to][from] = dist[from][to] = w;
		}

		// 플로이드워셜
		for (int k = 1; k <= n; k++)
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);

		int answer = dist[s][a] + dist[s][b]; // 합승 안하는 비용

		 // 각 지점까지 합승해보기
		for (int i = 1; i < n + 1; i++)
			answer = Integer.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);

		return answer;
	}
}