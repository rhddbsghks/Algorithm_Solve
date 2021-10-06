package d1006;

import java.util.*;
import java.io.*;

public class SEWEA_4014 {

	static int n, x, result, map[][];
	static List<List<int[]>> garo, sero;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./src/d1006/input4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			garo = new ArrayList<List<int[]>>();
			sero = new ArrayList<List<int[]>>();
			result = 0;

			for (int i = 0; i < n; i++) {
				garo.add(new ArrayList<int[]>());
				sero.add(new ArrayList<int[]>());
			}

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < n; i++) {
				int garoCnt = 1, seroCnt = 1;
				for (int j = 0; j < n; j++) {
					if (j < n - 1) {
						if (map[i][j] != map[i][j + 1]) {
							garo.get(i).add(new int[] { map[i][j], garoCnt });
							garoCnt = 1;
						} else
							garoCnt++;

						if (map[j][i] != map[j + 1][i]) {
							sero.get(i).add(new int[] { map[j][i], seroCnt });
							seroCnt = 1;
						} else
							seroCnt++;

					} else {
						garo.get(i).add(new int[] { map[i][j], garoCnt });
						sero.get(i).add(new int[] { map[j][i], seroCnt });
					}
				}
			}

			garo.addAll(sero);

			LOOP: for (List<int[]> land : garo) {
				if (land.size() == 1) {
					result++;
					continue;
				}

				for (int i = 0; i < land.size() - 1; i++) {
					int nowH = land.get(i)[0];
					int nowL = land.get(i)[1];
					int nextH = land.get(i + 1)[0];
					int nextL = land.get(i + 1)[1];

					if (Math.abs(nowH - nextH) > 1)
						continue LOOP;

					if (nowH > nextH && nextL - x >= 0) {
						land.set(i + 1, new int[] { nextH, nextL - x });
						continue;
					}

					else if (nowH < nextH && nowL - x >= 0) {
						land.set(i, new int[] { nowH, nowL - x });
						continue;
					}

					continue LOOP;
				}
				result++;
			}
			System.out.println("#" + t + " " + result);
		}
	}
}