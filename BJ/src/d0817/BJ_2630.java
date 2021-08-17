package d0817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준2630 색종이 만들기

public class BJ_2630 {
	static char[][] map;
	static int blue = 0, white = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = st.nextToken().charAt(0);
		}

		cut(0, 0, n);

		System.out.println(white);
		System.out.println(blue);
	}

	// 영역 탐색 후 모든 색이 같으면 숫자에 따라 색 카운트
	static void cut(int r, int c, int len) {
		char num = map[r][c];

		for (int i = r; i < r + len; i++)
			for (int j = c; j < c + len; j++) {
				// 해당 영역에서 하나라도 다른 값이 나오면 4등분
				if (num != map[i][j]) {
					if (len / 2 == 0) // 한칸짜리라면 자르지 않음
						break;
					cut(r, c, len / 2);
					cut(r, c + len / 2, len / 2);
					cut(r + len / 2, c, len / 2);
					cut(r + len / 2, c + len / 2, len / 2);
					return;
				}
			}
		if (num == '1')
			blue++;
		else
			white++;
	}
}