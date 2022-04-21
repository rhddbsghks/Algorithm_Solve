package d220421;

import java.util.*;
import java.io.*;

/*
백준20061 모노미노도미노 2

풀이법 :
	 1. 파랑, 초록 영역 모두 6x4로 관리합니다.
	 2. 행 내리는 작업은 그냥 참조값 바꿔주는 식으로 작성했습니다.

시간: 2h 10m
  */

public class BJ_20061 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int score = 0;
		boolean[][] blue = new boolean[6][4];
		boolean[][] green = new boolean[6][4];

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int blueCol = type != 3 ? 4 - x - 1 : 4 - x - 2;
			int blueType = type;

			if (type != 1) {
				blueType = type == 3 ? 2 : 3;
			}

			down(green, y, type);
			down(blue, blueCol, blueType);

			score += removeRow(green) + removeRow(blue);

			checkSpecial(green);
			checkSpecial(blue);
		}

		System.out.println(score);
		System.out.println(getCnt(green) + getCnt(blue));
	}

	// 초록, 파랑 영역에 블록 위치시키기
	static void down(boolean[][] board, int col, int type) {
		switch (type) {

		case 1:
			for (int i = 0; i < 6; i++) {
				board[i][col] = true;

				if (i == 5) {
					break;
				}

				if (i < 5 && board[i + 1][col]) {
					break;
				}

				board[i][col] = false;
			}
			break;

		case 2:
			for (int i = 0; i < 6; i++) {
				board[i][col] = board[i][col + 1] = true;

				if (i == 5) {
					break;
				}

				if (i < 5 && (board[i + 1][col] || board[i + 1][col + 1])) {
					break;
				}

				board[i][col] = board[i][col + 1] = false;
			}
			break;

		case 3:
			for (int i = 0; i < 5; i++) {
				board[i][col] = true;
				board[i + 1][col] = true;

				if (i == 4) {
					break;
				}

				if (i < 4 && board[i + 2][col]) {
					break;
				}

				board[i][col] = board[i + 1][col] = false;
			}
			break;
		}

	}

	// 이어진 행 있으면 카운트 및 배열 행 조정
	static int removeRow(boolean[][] board) {

		int cnt = 0;
		int start = -1;

		LOOP: for (int i = 5; i >= 0; i--) {

			for (int j = 0; j < 4; j++) {
				if (!board[i][j]) {
					continue LOOP;
				}
			}

			if (cnt == 0) {
				start = i;
			}
			cnt++;
		}

		if (cnt == 0) {
			return 0;
		}

		// 삭제한 행 개수만큼 참조값 조작
		for (int k = start; k >= 2; k--) {
			board[k] = board[k - cnt];
		}

		for (int k = 0; k < cnt; k++) {
			board[1 - k] = new boolean[4];
		}

		return cnt;
	}

	// 0행, 1행 영역 확인
	static void checkSpecial(boolean[][] board) {
		int cnt = 0;

		LOOP: for (int i = 1; i >= 0; i--) {

			for (int j = 0; j < 4; j++) {
				if (board[i][j]) {
					cnt++;
					continue LOOP;
				}
			}
		}

		if (cnt == 0) {
			return;
		}

		for (int k = 5; k >= 2; k--) {
			board[k] = board[k - cnt];
		}

		for (int k = 0; k < 2; k++) {
			board[k] = new boolean[4];
		}

		return;
	}

	static int getCnt(boolean[][] board) {
		int cnt = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j])
					cnt++;
			}
		}

		return cnt;
	}
}
