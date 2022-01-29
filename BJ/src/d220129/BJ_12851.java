package d220129;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준12851 숨바꼭질2

풀이법 :
	 1. BFS를 사용해 k점까지 탐색해봅니다.

 */


public class BJ_12851 {

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(
            new BufferedReader(new InputStreamReader(System.in)).readLine());
        boolean[] visited = new boolean[100001];

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int time = -1;
        int route = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);

        // 큐가 비거나 루트가 생기면 종료
        while (!q.isEmpty() && route == 0) {
            int size = q.size();
            time++;

            // 같은 시간에 도착하는 경로들
            while (size-- > 0) {
                int cur = q.poll();
                visited[cur] = true;
                int[] next = new int[3];

                // 도착하면 경로 카운트
                if (cur == k) {
                    route++;
                    continue;
                }

                next[0] = cur - 1;
                next[1] = cur + 1;
                next[2] = cur * 2;

                for (int i = 0; i < 3; i++) {
                    if (next[i] < 0 || next[i] > 100000 || visited[next[i]]) {
                        continue;
                    }
                    q.add(next[i]);
                }
            }
        }

        System.out.println(time);
        System.out.println(route);
    }
}
