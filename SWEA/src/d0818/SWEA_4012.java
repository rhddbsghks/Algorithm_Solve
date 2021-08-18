package d0818;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_4012 {

	static int n;
	static int[][] ingredients;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/d0818/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		int answer;

		for (int t = 1; t <= testcase; t++) {

			answer = Integer.MAX_VALUE;

			n = Integer.parseInt(br.readLine());
			int[] comb = new int[n]; // n개 중 절반을 뽑기위한 comb[]
			Arrays.fill(comb, n / 2, n, 1);

			// 재료맵 초기화 및 A, B 재료 배열
			ingredients = new int[n][n];
			int[] ingredA = new int[n / 2];
			int[] ingredB = new int[n / 2];

			for (int i = 0; i < n; i++) {
				String input[] = br.readLine().split(" ");
				for (int j = 0; j < n; j++)
					ingredients[i][j] = Integer.parseInt(input[j]);
			}

			// 절반은 서로 반대조합이므로 조합 절반만 탐색하면 됨
			do {
				int idxA = 0;
				int idxB = 0;

				// 재료 A, B 배열 초기화
				for (int i = 0; i < n; i++) {
					if (comb[i] == 1)
						ingredA[idxA++] = i;
					else
						ingredB[idxB++] = i;
				}

				// 맛 차이
				int flavorDif = Math.abs(calFlavor(ingredA) - calFlavor(ingredB));

				if (flavorDif < answer)
					answer = flavorDif;

			} while (np(comb) && comb[0] == 0); // nC(n/2) 중 절반만 보면됨

			System.out.println("#" + t + " " + answer);
		}
	}

	// 재료배열 입력받아 맛 점수 계산
	static int calFlavor(int[] ingred) {
		int flavor = 0;

		for (int i = 0; i < n / 2; i++)
			for (int j = i + 1; j < n / 2; j++) {
				flavor += ingredients[ingred[i]][ingred[j]];
				flavor += ingredients[ingred[j]][ingred[i]];
			}
		return flavor;
	}

	// next permutaion
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

		int tmp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = tmp;

		Arrays.sort(arr, i, n);
		return true;
	}
}