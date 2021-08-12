package d0805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1224 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/d0805/input1224.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Queue<Integer> queue;
		StringBuilder sb = new StringBuilder();

		while (true) {
			queue = new ArrayDeque<Integer>();

			try {
				int t = Integer.parseInt(br.readLine());

				st = new StringTokenizer(br.readLine(), " ");

				for (int i = 0; i < 8; i++)
					queue.offer(Integer.parseInt(st.nextToken()));

				encrypt(queue);

				sb.append("#" + t + " ");
				for (int q : queue)
					sb.append(q + " ");
				sb.append("\n");
			} catch (NumberFormatException e) {
				// 입력 끝나면 출력 후 return
				System.out.println(sb.toString());
				return;
			}
		}
	}

	static void encrypt(Queue<Integer> queue) {
		while (true)
			for (int i = 1; i <= 5; i++) {
				int poll = queue.poll();
				poll -= i;

				if (poll <= 0) {
					queue.offer(0);
					return;
				}
				queue.offer(poll);
			}
	}
}
