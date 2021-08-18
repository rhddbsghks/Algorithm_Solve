package d0818;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
백준 1992 쿼드트리

풀이법 : 영역 탐색 후 모두 같은 색이라면 압축, 아니라면 4등분하는 함수를 생성
		 이후 재귀호출을 통해 분할 정복
		 
시간복잡도: O(N^2)..?

 */

public class BJ_1992 {

	static StringBuilder sb;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		sb = new StringBuilder();

		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();

		compress(0, 0, n);

		System.out.println(sb);
	}

	// 주어진 점부터 크기 n 만큼 탐색. 모두 같으면 StringBuilder에 추가, 아니면 4등분
	static void compress(int r, int c, int n) {

		char bit = checkArea(r, c, n);

		if (bit != 'x')
			sb.append(bit);

		else {
			sb.append("(");
			compress(r, c, n / 2);
			compress(r, c + n / 2, n / 2);
			compress(r + n / 2, c, n / 2);
			compress(r + n / 2, c + n / 2, n / 2);
			sb.append(")");
		}
	}

	// 주어진 점부터 크기 n 만큼 탐색했을 때 다른게 하나라도 있으면 'x' 리턴
	static char checkArea(int r, int c, int n) {

		char bit = ' ';

		for (int i = r; i < r + n; i++)
			for (int j = c; j < c + n; j++) {
				if (i == r && j == c)
					bit = map[r][c];
				else if (map[i][j] != bit)
					return 'x';
			}
		return bit;
	}
}