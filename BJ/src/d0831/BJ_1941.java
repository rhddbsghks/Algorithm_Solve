package d0831;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
백준 1941 소문난 칠공주

풀이법 : 
		 1. 25명 중 7명을 뽑고 임도연파 인원이 3명 이하일 때만 진행합니다.
		 2. 7명의 좌표가 모두 연결되어 있다면 result를 카운트 합니다.
		 

한탄: 
	난이도가 올라갈수록 접근이 너무 어려워지네요... 
	이번 문제는 25명 중 7명을 뽑아 진행하는 아이디어는 구글링으로 참고했습니다..ㅠ
 */

public class BJ_1941 {

	static char[][] map; // 입력 map
	static int[][] pos; // 25개의 좌표
	static int[][] princess; // 선택된 7명의 좌표
	static int result;
	static int[] dr = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dc = { 0, 1, 0, -1 };

	
	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		pos = new int[25][];
		princess = new int[7][];
		int index = 0;
		result = 0;

		// map과 pos 초기화
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = input.charAt(j);
				pos[index++] = new int[] { i, j };
			}
		}

		// 25명의 좌표 중 7개를 뽑기
		pick7(0, 0);

		System.out.println(result);
	}
	/****************** Main End ******************/
	
	
	/****************** Method ******************/
	
	// pick7: 25명의 좌표 중 7개를 뽑는 조합
	static void pick7(int cnt, int start) {
		// 다 뽑았을 때
		if (cnt == 7) {
			// 도연파가 3명 이하일 때만 진행
			if (checkYeon()) {
				// 7명 배치를 표현한 boolean[][]
				boolean[][] pickedPrincess = new boolean[5][5];
				for (int i = 0; i < 7; i++) {
					int x = princess[i][0];
					int y = princess[i][1];
					pickedPrincess[x][y] = true;
				}

				// 해당 boolean[][]이 모두 연결되어 있는지 체크
				checkConnect(pickedPrincess);
			}
			return;
		}

		for (int i = start; i < 25; i++) {
			princess[cnt] = pos[i];
			pick7(cnt + 1, i + 1);
		}
	}

	// checkYeon: 선택된 princess 중 도연파 인원이 3이하일 때만 true
	static boolean checkYeon() {
		int yeon = 0;
		for (int i = 0; i < 7; i++) {
			int x = princess[i][0];
			int y = princess[i][1];

			if (map[x][y] == 'Y')
				yeon++;
		}
		if (yeon >= 4)
			return false;
		return true;
	}

	// checkConnect: bfs를 사용해 7명이 모두 연결되어있다면 result 카운트
	static void checkConnect(boolean[][] pickedPrincess) {
		int cnt = 0;

		// 0,0부터 탐색해서 첫 사람부터 시작
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				if (pickedPrincess[i][j]) {
					Queue<int[]> q = new ArrayDeque<int[]>();
					q.add(new int[] { i, j }); // 첫 사람 좌표

					while (!q.isEmpty()) {
						int[] pos = q.poll();
						int x = pos[0];
						int y = pos[1];

						if (!pickedPrincess[x][y])
							continue;

						pickedPrincess[x][y] = false;
						cnt++; // 사람 카운트

						for (int d = 0; d < 4; d++) {
							int nr = x + dr[d];
							int nc = y + dc[d];

							if (isValid(nr, nc) && pickedPrincess[nr][nc])
								q.add(new int[] { nr, nc });
						}
					}

					// cnt가 7이면 7명 모두 연결되어 있는 것임.
					if (cnt == 7)
						result++;

					return;
				}
	}

	static boolean isValid(int i, int j) {
		if (0 <= i && i < 5 && 0 <= j && j < 5)
			return true;
		return false;
	}
	/****************** Method End ******************/
}