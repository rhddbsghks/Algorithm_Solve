package d0812;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_3040 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] dwarf = new int[9];
		int[] comb = new int[9];

		for (int i = 0; i < 9; i++)
			dwarf[i] = sc.nextInt();

		comb[7] = 1;
		comb[8] = 1;

		do {
			int sum = 0;

			for (int i = 0; i < 9; i++) {
				if (comb[i] == 0)
					sum += dwarf[i];
			}

			if (sum == 100) {
				for (int i = 0; i < 9; i++) {
					if (comb[i] == 0)
						System.out.println(dwarf[i]);
				}
				return;
			}

		} while (np(comb));

	}

	public static boolean np(int[] arr) {
		int n = arr.length;

		int i = n - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;

		if (i == 0)
			return false;

		int j = n - 1;
		while (arr[i - 1] >= arr[j])
			j--;

		swap(arr, i - 1, j);
		Arrays.sort(arr, i, n);

		return true;
	}

	public static void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
}