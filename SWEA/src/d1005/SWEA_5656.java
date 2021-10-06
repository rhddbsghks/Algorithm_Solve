package d1005;

import java.util.*;
import java.io.*;

public class SWEA_5656 {

	static int n, w, h, min, perm[], map[][], clone[][];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <=testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			perm = new int[n];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			simulate(0);

			System.out.println("#"+t+" "+min);
		}
	}

	static void simulate(int cnt) {

		if (cnt == n) {

			clone = new int[h][w];
			for (int i = 0; i < h; i++)
				for (int j = 0; j < w; j++)
					clone[i][j] = map[i][j];

			for (int i = 0; i < n; i++) {
				shoot(perm[i]);
				down();
			}
			
			min = Integer.min(min, count());

			return;
		}

		for (int j = 0; j < w; j++) {
			perm[cnt] = j;
			simulate(cnt + 1);
		}
	}

	static void shoot(int j) {
		for (int i = 0; i < h; i++)
			if (clone[i][j] > 0) {
				bomb(i, j);
				return;
			}
	}

	static void bomb(int i, int j) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[h][w];
		q.offer(new int[] { i, j });
		visited[i][j] = true;
		

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int cnt = clone[r][c] - 1;

			
			clone[r][c] = 0;
			
			for (int d = 0; d < 4; d++) {
				int nr = r, nc = c;
				for (int k = 0; k < cnt; k++) {
					nr += dr[d];
					nc += dc[d];
					if (nr < 0 || nc < 0 || nr >= h || nc >= w || clone[nr][nc] == 0 || visited[nr][nc])
						continue;

					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}

		}
	}

	static void down() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		for (int j = 0; j < w; j++) {
			for (int i = h - 1; i >= 0; i--) {
				if (clone[i][j] != 0)
					q.offer(clone[i][j]);
			}

			for (int i = h - 1; i >= 0; i--) {
				if (q.isEmpty())
					clone[i][j] = 0;
				else
					clone[i][j] = q.poll();
			}
		}

	}

	static int count() {
		int cnt = 0;
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				if (clone[i][j] > 0)
					cnt++;
		return cnt;
	}
	
	static void print() {
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++)
				System.out.print(clone[i][j]+" ");
			System.out.println();
		}
	}
}
