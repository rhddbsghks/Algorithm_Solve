package d0813;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15686 {

	static int[][] map;
	static int[][] chickList;
	static int[][] chickTest;
	static int n;
	static int m;
	static int minDist = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int chicknum = 0;
		int idx = 0;

		map = new int[n + 1][n + 1];

		
		// map 초기화 및 치킨집 카운트
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					chicknum++;
			}
		}

		
		// 치킨집 좌표 저장
		chickList = new int[chicknum][];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (map[i][j] == 2)
					chickList[idx++] = new int[] { i, j };

		
		// 치킨집 조합
		int[] comb = new int[chicknum];
		Arrays.fill(comb, chicknum - m, chicknum, 1);

		do {
			idx = 0;
			chickTest = new int[m][];

			for (int i = 0; i < chicknum; i++)
				if (comb[i] == 1)
					chickTest[idx++] = chickList[i];
			
			// 조합별 치킨집 좌표 선택 후 계산 반복
			calMin();
		} while (np(comb));

		System.out.println(minDist);
	}

	
	// 치킨집 조합별 최소거리
	static void calMin() {
		int distance = 0;

		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (map[i][j] == 1) {
					int md = Integer.MAX_VALUE;
					int[] pos = new int[] { i, j };

					for (int k = 0; k < m; k++) {
						int d = dist(pos, chickTest[k]);
						if (d < md)
							md = d;
					}

					distance += md;
				}
		if (distance < minDist)
			minDist = distance;
	}

	
	// Manhattan Distance
	static int dist(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}

	
	// Next Permutaion
	static boolean np(int[] arr) {
		int n = arr.length;

		int i = n - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;
		if (i == 0)
			return false;
		int j = n - 1;
		while (arr[i - 1] >= arr[j])
			j--;

		int tmp = arr[j];
		arr[j] = arr[i - 1];
		arr[i - 1] = tmp;

		Arrays.sort(arr, i, n);
		return true;
	}
}
