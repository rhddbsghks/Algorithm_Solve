package d0806;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861 {
	static int[][] map;
	static int root;
	static int num;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.setIn(new FileInputStream("src/d0806/input1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int testcase = Integer.parseInt(br.readLine());
		int n;
		int roomnum;
		int maxroot;

		for (int t = 1; t <= testcase; t++) {

			roomnum = 0;
			maxroot = 0;
			sb.append("#" + t + " ");

			n = Integer.parseInt(br.readLine());
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					root = 1;
					num = map[i][j];
					search(i, j);
					if (maxroot < root) {
						maxroot = root;
						roomnum = num;
					}
					if (maxroot == root) {

						if (roomnum > num)
							roomnum = num;
					}
				}
			sb.append(roomnum + " " + maxroot);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static void search(int i, int j) {

		for (int d = 0; d < 4; d++) {
			int n = map[i][j];
			i += dr[d];
			j += dc[d];

			if (0 <= i && i < map.length && 0 <= j && j < map.length && map[i][j] == n + 1) {
				root++;
				search(i, j);
				break;
			}
			i -= dr[d];
			j -= dc[d];
		}
		return;

	}
}