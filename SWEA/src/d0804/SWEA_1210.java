package d0804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1210 {
	static int[][] map;
	static int nr, nc;
	static int[] dr = { -1, 0, 0 };
	static int[] dc = { 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/d0804/input1210.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t < 11; t++) {
			br.readLine();
			nr = 99;
			map = new int[100][100];

			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken(" "));
					if (map[i][j] == 2)
						nc = j;
				}
			}
			find();
			System.out.println("#" + t + " " + nc);
		}

	}

	static void find() {
		while (nr > 0) {
			nr += dr[0];
			nc += dc[0];

			if (0 <= nc - 1 && map[nr][nc - 1] == 1)
				while (0 <= nc - 1 && map[nr][nc - 1] == 1) {
					nr += dr[1];
					nc += dc[1];
					if (0 <= nc - 1 && map[nr][nc - 1] == 0)
						break;
				}

			else if (nc + 1 <= 99 && map[nr][nc + 1] == 1)
				while (nc + 1 <= 99 && map[nr][nc + 1] == 1) {
					nr += dr[2];
					nc += dc[2];
					if (nc + 1 <= 99 && map[nr][nc + 1] == 0)
						break;
				}
		}
	}

}
