package d0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_3499 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb;
		for (int t = 1; t <= testcase; t++) {
			int n = Integer.parseInt(br.readLine());
			int idx;
			st = new StringTokenizer(br.readLine(), " ");
			sb = new StringBuilder();
			sb.append("#" + t + " ");
			Queue<String> card1 = new ArrayDeque<String>();
			Queue<String> card2 = new ArrayDeque<String>();

			if (n % 2 == 1)
				idx = n / 2 + 1;
			else
				idx = n / 2;

			for (int i = 0; i < idx; i++)
				card1.offer(st.nextToken());
			for (int i = idx; i < n; i++)
				card2.offer(st.nextToken());

			while (!card2.isEmpty()) {
				sb.append(card1.poll() + " ");
				sb.append(card2.poll() + " ");
			}
			if (n % 2 == 1)
				sb.append(card1.poll() + " ");

			System.out.println(sb.toString());
		}
	}
}
