package d0811;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;


/*
백준 5697 숨바꼭질
풀이법 : 아주 기본적인 bfs 문제인 것 같다. 좌표값을 큐에 넣어주고 거리를 1씩 더해주며 탐색
시간복잡도 : O(N)..? 딱히 고려해주지 않아도 됐었던 문제
전혀 감이 오지 않아 여러 풀이법을 봤던 문제
그나저나 line 30~33 부분을 처리해주지 않으면 왜 반례가 있을까요...?
 */


public class BJ_1697 {

	static int[] tree = new int[100001];
	static boolean[] isVisited = new boolean[100001];
	static int target;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		target = sc.nextInt();

		if (target < n) {
			System.out.println(n - target);
			return;
		}

		bfs(n);
		System.out.println(tree[target]);
	}

	static void bfs(int start) {

		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(start);

		while (!q.isEmpty()) {
			int t = q.poll();

			if (t == target)
				return;

			if (t - 1 > 0 && !isVisited[t - 1]) {
				isVisited[t - 1] = true;
				tree[t - 1] = tree[t] + 1;
				q.add(t - 1);
			}

			if (t + 1 < 100001 && !isVisited[t + 1]) {
				isVisited[t + 1] = true;
				tree[t + 1] = tree[t] + 1;
				q.add(t + 1);
			}

			if (t * 2 < 100001 && !isVisited[t * 2]) {
				isVisited[t * 2] = true;
				tree[t * 2] = tree[t] + 1;
				q.add(t * 2);
			}
		}
	}
}
