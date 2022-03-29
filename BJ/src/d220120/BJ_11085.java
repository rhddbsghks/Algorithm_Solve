package d220120;

import java.io.*;
import java.util.*;

/*
백준11085 군사 이동

풀이법 :
	 1. c에서 BFS로 탐색을 시작하되 큐 대신 우선순위 큐를 이용해 weight가 큰 값부터 탐색합니다.
	 2. 최솟값을 갱신해가다가 v에 도착하면 종료합니다.

 */

public class BJ_11082 {

    static int p, w, c, v, answer;
    static boolean[] visited;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        graph = new ArrayList[p];
        visited = new boolean[p];
        answer = Integer.MAX_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o2[1], o1[1]))); // weight 내림차순으로 정렬

        for (int i = 0; i < p; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, weight});
            graph[to].add(new int[]{from, weight});
        }

        pq.offer(new int[]{c,50001}); // 시작점 pq에 삽입

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int from = cur[0];
            int weight = cur[1];
            visited[from] = true;
            answer = Math.min(answer, weight); // 너비 좁은 길 갱신

            // v에 도착하면 종료
            if (from == v)
                break;

            for (int[] next : graph[from]) {
                if (visited[next[0]]) continue;
                pq.offer(next);
            }
        }
        System.out.println(answer);
    }
}
