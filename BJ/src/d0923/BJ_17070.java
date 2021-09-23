package d0923;

import java.util.*;
import java.io.*;

/*
백준 17070 파이프 옮기기 1

풀이법 : 
		 1. 파이프 끝점 좌표만 고려합니다.
		 2. 현재 놓여있는 방향 별로 가능한 방향으로 모두 밀어봅니다.
		 3. 끝점이 N,N에 도착하면 result를 1 증가시킵니다.

굳이 DP 테이블 안써도 될 법한 문제였네요.
 */

public class BJ_17070 {

	static int n, result, map[][];
	static int[] dr = { 0, 1 }; // 가로 세로
	static int[] dc = { 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		result = 0;

		// map 초기화
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		slide(0, 1, 0);
		System.out.println(result);
	}

	static void slide(int r, int c, int type) { // type 0 = 가로, 1 = 세로, 2 = 대각\

		// 끝점 도착
		if (r == n - 1 && c == n - 1) {
			result++;
			return;
		}

		// 대각처리(3개 type 공통)
		int nr = r + 1;
		int nc = c + 1;
		if (valid(nr, nc) && map[r][nc] == 0 && map[nr][c] == 0)
			slide(nr, nc, 2);

		// type = 대각
		if (type == 2) {
			// 가로, 세로 모두 밀기
			for (int d = 0; d < 2; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				if (valid(nr, nc))
					slide(nr, nc, d);
			}
		}

		// type = 가로 or 세로
		// 각자 type 방향에 맞게 한번 밀기
		else {
			nr = r + dr[type];
			nc = c + dc[type];
			if (valid(nr, nc))
				slide(nr, nc, type);
		}
	}

	static boolean valid(int r, int c) {
		return 0 <= r && r < n && 0 <= c && c < n && map[r][c] == 0;
	}
}