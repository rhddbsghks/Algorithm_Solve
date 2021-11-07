package d1107;

/*
백준 13459 구슬 탈출

풀이법 : 
	 1. BFS를 사용해 구슬들을 굴려봅니다. 
	 2. 구슬 위치에 따라 먼저 좌표를 옮길 구슬이 달라지고 매번 map을 복사해 계산해줍니다. 
	 3. 빨간 구슬만 O에 도착했을 때 종료합니다.  

 */


import java.util.*;
import java.io.*;

public class BJ_13459 {
	static int n, m;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	// 구슬을 굴린 후 Board 상태
	static class Board {
		int[] rPos;
		int[] bPos;
		char[][] map;

		public Board() {
		}

		public Board(int[] rPos, int[] bPos, char[][] map) {
			this.rPos = rPos;
			this.bPos = bPos;
			this.map = map;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		Board start = new Board();

		// 입력처리 & 빨간구슬, 파란구슬 위치 저장
		for (int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = input[j];
				if (map[i][j] == 'R')
					start.rPos = new int[] { i, j };
				else if (map[i][j] == 'B')
					start.bPos = new int[] { i, j };
			}
		}
		start.map = map;
		Queue<Board> q = new ArrayDeque<>();
		q.offer(start);
		int cnt = 0;

		//BFS
		while (!q.isEmpty()) {
			int size = q.size();
			
			// 10번 굴려도 안되면 0 출력
			if (cnt == 10) {
				System.out.println(0);
				return;
			}

			while (size-- > 0) {
				Board cur = q.poll();
				
				// 현재 board에서 상,하,좌,우로 기울일 때 빨간구슬이 먼저인지 true or false
				boolean[] order = new boolean[4];
				order[0] = cur.rPos[0] < cur.bPos[0]; // 위로 기울일 때 빨간구슬 행좌표가 더 작으면 true(빨간구슬 먼저 옮기기)
				order[1] = cur.rPos[0] > cur.bPos[0]; // 하
				order[2] = cur.rPos[1] < cur.bPos[1]; // 좌
				order[3] = cur.rPos[1] > cur.bPos[1]; // 우

				for (int d = 0; d < 4; d++) {
					char[][] next = copy(cur.map);  // map 복사
					
					// 현재 빨간구슬, 파란구슬은 .으로 비우기
					next[cur.rPos[0]][cur.rPos[1]] = '.';
					next[cur.bPos[0]][cur.bPos[1]] = '.';
					int[] nextRed;
					int[] nextBlue;

					// order[d]가 true이면 빨간구슬부터, 아니면 파란구슬부터
					if (order[d]) {
						nextRed = tilt(d, cur.rPos, next);
						nextBlue = tilt(d, cur.bPos, next);
					} else {
						nextBlue = tilt(d, cur.bPos, next);
						nextRed = tilt(d, cur.rPos, next);
					}

					// 빨간공만 O에 도착했으면 1출력
					if (nextRed[0] == -1 && nextBlue[0] != -1) {
						System.out.println(1);
						return;
					}

					// 파란공이 O에 도착했으면 패스
					if (nextBlue[0] == -1)
						continue;

					// 두 구슬 모두 안움직였으면 패스
					if (!same(cur.rPos, nextRed) || !same(cur.bPos, nextBlue))
						q.offer(new Board(nextRed, nextBlue, next));
				}
			}
			cnt++;
		}
		// 가능한 경우 없어도 0 출력
		System.out.println(0);
	}

	// d 방향으로 구슬 기울이기, 빨강/파랑 모두 map에 M으로 마킹
	static int[] tilt(int d, int[] pos, char[][] map) {
		int r = pos[0];
		int c = pos[1];

		while (true) {
			if (map[r][c] != '.') {
				// 구멍이면 (-1,-1) 리턴
				if (map[r][c] == 'O')
					return new int[] { -1, -1 };
				// 아니면 이전좌표에 M 마킹후 리턴
				map[r - dr[d]][c - dc[d]] = 'M';
				return new int[] { r - dr[d], c - dc[d] };
			}
			r += dr[d];
			c += dc[d];
		}
	}

	static char[][] copy(char[][] map) {
		char[][] tmp = new char[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				tmp[i][j] = map[i][j];
		return tmp;
	}

	static boolean same(int[] posA, int[] posB) {
		return posA[0] == posB[0] && posA[1] == posB[1];
	}
}