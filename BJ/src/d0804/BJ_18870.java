package d0804;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 18870 좌표 압축
풀이법 : 배열 및 이진탐색
시간복잡도 : Arrays.sort (NlogN), Binary Search (logN)
더 공부할 것 : 시간복잡도 고려하기 (리스트 인덱스 기반으로 탐색하다 터짐...)
 */

public class BJ_18870 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// Testcase
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		// 배열 정렬 -> sorted
		int[] sorted = new int[n];
		sorted = arr.clone();
		Arrays.sort(sorted);

		// sorted 중복제거 -> sorted2
		int[] sorted2 = new int[n];
		sorted2[0] = sorted[0];
		int idx = 1;
		for (int i = 1; i < sorted.length; i++)
			if (sorted[i] != sorted[i - 1])
				sorted2[idx++] = sorted[i];

		// sorted2 빈칸 maxvalue로 채우고 이진탐색
		Arrays.fill(sorted2, idx, n, Integer.MAX_VALUE);
		for (int i = 0; i < n; i++)
			sb.append(Arrays.binarySearch(sorted2, arr[i]) + " ");

		sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
		// Testcase End
	}
}