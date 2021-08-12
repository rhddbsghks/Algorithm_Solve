package d0804;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class BJ_2751 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(br.readLine());

		Arrays.sort(arr);

		for (int num : arr)
			sb.append(num+"\n");
		System.out.print(sb.toString());
		br.close();
	}
}
