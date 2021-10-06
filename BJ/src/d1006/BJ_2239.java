package d1006;

import java.io.*;

/*
백준 2239 스도쿠

풀이법 : 
	 1. 가로, 세로, 박스의 숫자 중복을 비트마스킹으로 관리하였습니다.
	 2. 입력과 함께 초기 숫자 현황을 비트마스킹합니다.
	 3. DFS를 사용해 0번째 칸부터 80번째 칸까지 1~9를 놓아보고 끝까지 채웠으면 true를 반환합니다. 
	 4. 1부터 차례로 놓아보기 때문에 완성되자마자 바로 종료시키면 자동으로 사전식으로 앞서는 정답이 됩니다.
		 
 */


public class BJ_2239 {

	static int sudoku[][], box[][], garo[], sero[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sudoku = new int[9][9];
		box = new int[3][3]; // 박스형 숫자 비트마스킹
		garo = new int[9]; // 각 가로 숫자 비트마스킹
		sero = new int[9]; // 각 세로 숫자 비트마스킹

		for (int i = 0; i < 9; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = Integer.parseInt(input[j]);
				
				// 초기 숫자가 있으면 비트마스킹
				if (sudoku[i][j] != 0) {
					garo[i] = garo[i] | 1 << sudoku[i][j];
					sero[j] = sero[j] | 1 << sudoku[i][j];
					box[i / 3][j / 3] = box[i / 3][j / 3] | 1 << sudoku[i][j];
				}
			}
		}
		
		fill(0);
		print();
	}

	static boolean fill(int n) {
		if (n == 81)
			return true;

		// 놓아 볼 인덱스
		int r = n / 9;
		int c = n % 9;

		// 빈칸이면 1~9를 차례로 놓아보고 다음칸 채우기
		if (sudoku[r][c] == 0) {
			for (int i = 1; i < 10; i++) {
				if ((garo[r] & 1 << i) != 0 || (sero[c] & 1 << i) != 0 || (box[r / 3][c / 3] & 1 << i) != 0)
					continue;

				// 1~9까지 놓아보고 비트마스킹 해놓기
				sudoku[r][c] = i;
				garo[r] |= 1 << i;
				sero[c] |= 1 << i;
				box[r / 3][c / 3] |= 1 << i;

				// 채우기 성공했다면 true 반환
				if (fill(n + 1))
					return true;

				// 해당 숫자는 실패했으므로 스도쿠와 비트마스킹 초기화
				sudoku[r][c] = 0;
				garo[r] ^= 1 << i;
				sero[c] ^= 1 << i;
				box[r / 3][c / 3] ^= 1 << i;
			}

		} 
		// 이미 입력된 값이면 다음칸으로 넘어가기
		else
			if (fill(n + 1))
				return true;

		return false;
	}

	static void print() {
		for (int[] arr : sudoku) {
			for (int a : arr)
				System.out.print(a);
			System.out.println();
		}
	}
}