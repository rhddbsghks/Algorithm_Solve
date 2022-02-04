package d220204;

import java.io.*;
import java.util.*;

/*
백준1197 최소 스패닝 트리

풀이법 :
	 기본적인 MST 문제였습니다. 크루스칼 알고리즘을 활용해 해결하였습니다.

 */


public class BJ_1197 {

    static int v, e, answer;
    static List<int[]>[] graph;
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        answer = 0;
        visited = new boolean[v + 1];
        graph = new List[v + 1];

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[from].add(new int[]{to, w});
            graph[to].add(new int[]{from, w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty() && v > 0) {
            int[] cur = pq.poll();
            int to = cur[0];
            int w = cur[1];

            if (visited[to]) {
                continue;
            }

            answer+=w;
            v--;
            visited[to] = true;

            for(int[] next : graph[to]){
                    pq.add(next);
            }
        }

        System.out.println(answer);
    }
}
