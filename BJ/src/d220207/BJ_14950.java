package d220207;

import java.io.*;
import java.util.*;

/*
백준14950 정복자

풀이법 :
	 1. MST를 구성하면서 증가할 비용을 미리 더해놓습니다.
	 2. 일반적인 MST를 계산합니다.

 */


public class BJ_14950 {

    static int n, m, t;
    static List<int[]>[] city;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        city = new List[n+1];

        for (int i = 1; i < n + 1; i++) {
            city[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            city[from].add(new int[]{to, w});
            city[to].add(new int[]{from, w});
        }

        int mst = ((t + t * (n - 2)) * (n - 2)) / 2; // 도시를 정복하며 추가될 비용들 미리 더해놓기
        boolean visited[] = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.offer(new int[]{1, 0});

        // 크루스칼 알고리즘
        while (!pq.isEmpty() && n > 0) {
            int[] cur = pq.poll();
            int edge = cur[0];
            int w = cur[1];

            if(visited[edge])
                continue;

            visited[edge]=true;
            mst+=w;
            n--;

            for(int[] adj : city[edge]){
                if (!visited[adj[0]]) {
                    pq.offer(adj);
                }
            }
        }

        System.out.println(mst);
    }
}