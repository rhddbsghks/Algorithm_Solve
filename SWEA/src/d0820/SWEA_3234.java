package d0820;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
SWEA 3234 준환이의 양팔저울

풀이법 : NextPermutation으로 무게추를 올릴 순열을 생성한다. 
		 이후 재귀적으로 무게추를 왼쪽, 오른쪽에 올려보며 오른쪽 합이 크다면 재귀를 호출하지 않는다.
		
시간복잡도: rough하게 본다면 2N * N!, 따라서 백트래킹을 하며 오른쪽이 무거울때마다 pruning을 해야함
 */


public class SWEA_3234 {

	static int cnt;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/d0820/input3234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		// testcase Start
		for (int t = 1; t <= testcase; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] weight = new int[n];
			cnt = 0;

			String[] input = br.readLine().split(" ");
			for (int i = 0; i < n; i++)
				weight[i] = Integer.parseInt(input[i]);

			Arrays.sort(weight); // 무게 추 순서 배열

			do {
				checkWeight(weight, 0, 0, 0);
			} while (np(weight));

			System.out.println("#" + t + " " + cnt);
		}
		// testcase End
	}

	static void checkWeight(int[] arr, int idx, int leftSum, int rightSum) {
		if (idx == arr.length) {
			cnt++;
			return;
		}

		rightSum += arr[idx];
		// 오른쪽 합이 작을 때만 오른쪽에 놓아보기
		if (!(rightSum > leftSum))
			checkWeight(arr, idx + 1, leftSum, rightSum);
		rightSum -= arr[idx]; // 놓아본 것 빼기

		// 왼쪽에 놓아보기
		leftSum += arr[idx];
		checkWeight(arr, idx + 1, leftSum, rightSum);
		leftSum -= arr[idx]; // 놓아본 것 빼기
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

		swap(i - 1, j, arr);

		int k = n - 1;
		while (i < k)
			swap(i++, k--, arr);
		return true;
	}

	static void swap(int i, int j, int[] arr) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}