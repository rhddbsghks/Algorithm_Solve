package d0824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 17144 미세먼지 안녕!

풀이법 : 
	이 문제의 핵심은 먼지들을 동시에 확산시켜 갱신하는 부분인 것 같습니다.
	기존 map과 별개로 spread[][] 라는 임시 공간을 만들어 확산된 먼지들을 더해놓고
	모든 먼지 확산이 끝나면 map과 spread를 합치고 spread 공간을 0으로 초기화시켜주었습니다.
	먼지확산 + 공기청정기 가동을 1cycle로 놓고 time 만큼 반복하면 할만했던 시뮬 같습니다.
		 
시뮬에 점점 중독되어 갑니다... 저희 4명 모두 변태가 되는 그날까지...!
 */


public class BJ_17144 {
	
	static int r, c, cleaner, time, map[][], spread[][];
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		cleaner = 0; // 공기청정기 위쪽 행좌표
		map = new int[r][c];
		spread = new int[r][c]; // 먼지 확산 임시 map

		// map, 공기청정기 초기화
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1 && cleaner == 0)
					cleaner = i;
			}
		}

		// time 만큼 시뮬레이션
		for (int i = 0; i < time; i++)
			simulate();

		System.out.println(dustAmount());
	}
	/****************** Main End ******************/

	
	
	/****************** Method ******************/
	
	// simulate: 먼지확산, 공기청정기 가동 1cycle
	static void simulate() {
		spreadDust();
		cleanDust();
	}

	// spreadDust: map의 먼지들 확산 값을 spread에 임시 저장
	static void spreadDust() {
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				// 먼지라면 
				if (map[i][j] > 0) {
					int amount = map[i][j] / 5;

					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];

						// 확산될 수 있으면 spread 공간에 임시 저장 후 map에서 그만큼 빼주기
						if (isInBoundary(nr, nc)) {
							spread[nr][nc] += amount;
							map[i][j] -= amount;
						}
					}
				}
		mergeDust(); // map에 반영하기
	}

	// mergeDust: map과 먼지확산 임시인 spread 합쳐주기
	static void mergeDust() {
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++) {
				map[i][j] += spread[i][j];
				spread[i][j] = 0; // 해당 spread 영역 초기화
			}
	}

	// cleanDust: 공기청정기 가동
	static void cleanDust() {
		// 위쪽 반시계 방향 
		for (int i = cleaner - 1; i > 0; i--)  // 왼
			map[i][0] = map[i - 1][0];
		for (int j = 0; j < c - 1; j++)  // 위
			map[0][j] = map[0][j + 1];
		for (int i = 0; i < cleaner; i++)  // 오
			map[i][c - 1] = map[i + 1][c - 1];
		for (int j = c - 1; j > 1; j--)  // 아래
			map[cleaner][j] = map[cleaner][j - 1];
		map[cleaner][1] = 0;  // 청정된 공기

		
		// 아래쪽 시계방향
		for (int i = cleaner + 2; i < r - 1; i++)  // 왼
			map[i][0] = map[i + 1][0];
		for (int j = 0; j < c - 1; j++)  // 아래
			map[r - 1][j] = map[r - 1][j + 1];
		for (int i = r - 1; i > cleaner + 1; i--)  // 오
			map[i][c - 1] = map[i - 1][c - 1];
		for (int j = c - 1; j > 1; j--)  // 위
			map[cleaner + 1][j] = map[cleaner + 1][j - 1];
		map[cleaner + 1][1] = 0;  // 청정된 공기
	}

	// dustAmount: 먼지 총합 계산
	static int dustAmount() {
		int cnt = 0;
		for (int[] arr : map)
			for (int a : arr)
				if (a > 0)
					cnt += a;
		return cnt;
	}

	// isInBoundary: 유표좌표 체크
	static boolean isInBoundary(int i, int j) {
		if (0 <= i && i < r && 0 <= j && j < c && map[i][j] != -1)
			return true;
		return false;
	}
	/****************** Method End ******************/
}