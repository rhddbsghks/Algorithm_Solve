package d0819;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3109 {

	static char map[][];
	static boolean[][] isVisited;
	static int r, c;
	static int dr[] = { -1, 0, 1 };
	static int pipe;
	static boolean flag; // pipe 생성 여부 flag

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][];
		pipe = 0;
		isVisited = new boolean[r][c];

		for (int i = 0; i < r; i++)
			map[i] = br.readLine().toCharArray();

		for (int i = 0; i < r; i++) {
			flag = false;
			makePipe(i, 0); // 근처 빵집부터 pipe 생성 시작
		}

		System.out.println(pipe);
	}

	static void makePipe(int i, int j) {

		// 끝까지 도착했다면 pipe 확정
		if (j == c - 1) {
			flag = true;
			pipe++;
			return;
		}

		// 위 옆 아래 순으로 pipe를 놓아본다
		for (int d = 0; d < 3; d++) {
			int nr = i + dr[d];
			int nc = j + 1;

			// pipe가 아직 생성되지 않았고 유효한 좌표이면 pipe를 놓아본다
			if (!flag && isProper(nr, nc)) {
				map[nr][nc] = 'x';
				isVisited[nr][nc] = true;
				makePipe(nr, nc);

				// pipe를 생성하지 못하고 끝나는 거라면 다시 '.'으로 돌려놓기
				if (!flag)
					map[nr][nc] = '.';
			}
		}
	}

	// row 값이 유효하고, 값이 '.' 이면서, 아직 pipe를 놓아보지 않은 곳이면 true
	static boolean isProper(int i, int j) {
		if (0 <= i && i < r && map[i][j] == '.' && !isVisited[i][j])
			return true;
		return false;
	}
}