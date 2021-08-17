package d0817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 14500 테트로미노

풀이법 : n,m이 500이하이기 때문에 map의 점들을 완전탐색하며 5개의 타일을 다 놓아보고 최댓값 갱신
		 각 점마다 타일 합 계산하는 함수 5개를 호출.
		 checkLine(): 일자타일 합 구하기
		 checkSquare(): 사각형 타일 합 구하기
		 checkL(): L자 타일 합 구하기
		 checkZ(): Z자 타일 합 구하기
		 checkT(): T자 타일 합 구하기
		 
시간복잡도: O(N^2)..?

 */

public class BJ_14500 {

	static int n, m, map[][];
	static int max = 0;
	static int[] dr = { -1, 0, 1, 0 }; // 상, 우, 하, 좌 (시계방향)
	static int[] dc = { 0, 1, 0, -1 };

	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		// map 초기화
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		// map의 모든 점을 순회하며 타일 놓아보기
		findMax();

		System.out.println(max);
	}
	/****************** Main End ******************/

	
	
	/****************** Method ******************/

	// findMax: map의 각 점마다 5개의 체크함수 호출. 각 체크함수는 타일을 돌려가며 합을 계산한다.
	static void findMax() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				checkLine(i, j);
				checkSquare(i, j);
				checkT(i, j);
				checkL(i, j);
				checkZ(i, j);
			}
	}

	// checkLine: 해당 점에서 가로 & 세로 합을 체크
	static void checkLine(int r, int c) {
		int sum;

		// 가로합 체크하기
		if (c <= m - 4) {
			sum = map[r][c] + map[r][c + 1] + map[r][c + 2] + map[r][c + 3];
			if (sum > max)
				max = sum;
		}

		// 세로합 체크하기
		if (r <= n - 4) {
			sum = map[r][c] + map[r + 1][c] + map[r + 2][c] + map[r + 3][c];
			if (sum > max)
				max = sum;
		}
	}

	// checkSquare: 해당 점에서 사각형 합 체크
	static void checkSquare(int r, int c) {
		int sum;

		if (r < n - 1 && c < m - 1) {
			sum = map[r][c] + map[r][c + 1] + map[r + 1][c] + map[r + 1][c + 1];
			if (sum > max)
				max = sum;
		}
	}

	// checkT: 해당 점 + 3방향을 계산해 T자 합 체크
	static void checkT(int r, int c) {
		int sum;

		// 4방 탐색
		LOOP: for (int i = 0; i < 4; i++) {

			sum = map[r][c]; // 현재 점 값

			// 탐색한 방향부터 시계방향으로 3개 합
			for (int j = 0; j < 3; j++) {
				int nr = r + dr[(i + j) % 4];
				int nc = c + dc[(i + j) % 4];

				// 점 하나라도 유효하지 않으면 계산안함
				if (!(isInBoundary(nr, nc)))
					continue LOOP;
				sum += map[nr][nc];
			}
			if (sum > max)
				max = sum;
		}
	}

	// checkL: 해당점에서 L자 합 체크. 4방별로 3칸 합을 구한 후 오른쪽 또는 왼쪽 2가지 경우가 있다.
	static void checkL(int r, int c) {
		int sum;

		for (int i = 0; i < 4; i++) {

			// 탐색할 방향으로 2칸 갔을 때 유효하지 않다면 타일을 놓을 수 없다.
			if (!(isInBoundary(r + dr[i] * 2, c + dc[i] * 2)))
				continue;

			// 일자로 3칸 합
			sum = map[r][c] + map[r + dr[i]][c + dc[i]] + map[r + dr[i] * 2][c + dc[i] * 2];

			// 3칸 합 구한 뒤 오른쪽
			int nr = r + 2 * dr[i] + dr[(i + 1) % 4];
			int nc = r + 2 * dc[i] + dc[(i + 1) % 4];

			if (isInBoundary(nr, nc)) {
				int Rsum = sum + map[nr][nc];
				if (Rsum > max)
					max = Rsum;
			}

			// 3칸 합 구한 뒤 왼쪽
			nr = r + 2 * dr[i] + dr[(i + 3) % 4];
			nc = r + 2 * dc[i] + dc[(i + 3) % 4];

			if (isInBoundary(nr, nc)) {
				int Lsum = sum + map[nr][nc];
				if (Lsum > max)
					max = Lsum;
			}
		}
	}

	// checkZ: 사방별로 방향 i, i-1 i 탐색 혹은 i, i+1, i 총 2가지로 Z 타일을 놓을 수 있다
	// 		   이놈이 제일 더럽네요...
	static void checkZ(int r, int c) {
		int sum;

		for (int i = 0; i < 4; i++) {

			// i, i+1, i로 놓을 때 끝점이 유효하다면
			if (isInBoundary(r + 2 * dr[i] + dr[(i + 1) % 4], c + 2 * dc[i] + dc[(i + 1) % 4])) {

				sum = map[r][c] + map[r + dr[i]][c + dc[i]]
						+ map[r + dr[i] + dr[(i + 1) % 4]][c + dc[i] + dc[(i + 1) % 4]]
						+ map[r + 2 * dr[i] + dr[(i + 1) % 4]][c + 2 * dc[i] + dc[(i + 1) % 4]];
				if (sum > max)
					max = sum;
			}

			// i, i-1, i로 놓을 때 끝점이 유효하다면
			if (isInBoundary(r + 2 * dr[i] + dr[(i + 3) % 4], c + 2 * dc[i] + dc[(i + 3) % 4])) {

				sum = map[r][c] + map[r + dr[i]][c + dc[i]]
						+ map[r + dr[i] + dr[(i + 3) % 4]][c + dc[i] + dc[(i + 3) % 4]]
						+ map[r + 2 * dr[i] + dr[(i + 3) % 4]][c + 2 * dc[i] + dc[(i + 3) % 4]];
				if (sum > max)
					max = sum;
			}
		}
	}

	// isInBoundary: 유효좌표 체크
	static boolean isInBoundary(int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < m)
			return true;
		return false;
	}
	/****************** Method End ******************/
}