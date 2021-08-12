package d0804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1873 {
	static char[][] map;
	static final int U = 0;
	static final int D = 1;
	static final int L = 2;
	static final int R = 3;
	static final char[] tank = { '^', 'v', '<', '>' };
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int nr, nc;
	static int h, w;
	static int td;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t < testcase + 1; t++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];

			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j);

					switch (map[i][j]) {
					case '^':
						td = U;
						nr = i;
						nc = j;
						break;
					case 'v':
						td = D;
						nr = i;
						nc = j;
						break;
					case '<':
						td = L;
						nr = i;
						nc = j;
						break;
					case '>':
						td = R;
						nr = i;
						nc = j;
						break;
					}
				}
			}

			int m = Integer.parseInt(br.readLine());
			String cmds = br.readLine();

			for (int i = 0; i < m; i++) {
				char cmd = cmds.charAt(i);

				switch (cmd) {
				case 'U':
					move(U);
					break;
				case 'D':
					move(D);
					break;
				case 'L':
					move(L);
					break;
				case 'R':
					move(R);
					break;

				case 'S':
					shoot();
					break;
				}
			}

			System.out.print("#" + t + " ");
			printMap();
		}
	}

	static void printMap() {
		for (int x = 0; x < h; x++)
			for (int y = 0; y < w; y++)
				System.out.print(map[x][y]);
		System.out.println();
	}

	static void shoot() {
		int r = nr + dr[td];
		int c = nc + dc[td];

		while (0 <= r && 0 <= c && r < h && c < w) {
			if (map[r][c] == '.' || map[r][c] == '-') {
				r += dr[td];
				c += dc[td];
				continue;
			}

			if (map[r][c] == '*') {
				map[r][c] = '.';
				break;
			}

			if (map[r][c] == '#')
				break;
		}
	}

	static void move(int dir) {
		map[nr][nc] = tank[dir];
		td = dir;
		int r = nr + dr[dir];
		int c = nc + dc[dir];

		if (0 <= r && 0 <= c && r < h && c < w && map[r][c] != '*' && map[r][c] != '#' && map[r][c] != '-') {
			map[r][c] = tank[dir];
			map[nr][nc] = '.';
			nr = r;
			nc = c;
		}
	}
}