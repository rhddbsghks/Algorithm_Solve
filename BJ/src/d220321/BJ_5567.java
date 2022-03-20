package d220321;

import java.util.*;
import java.io.*;

// BFS 탐색

public class BJ_5567 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<Integer>[] friends = new List[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            friends[from].add(to);
            friends[to].add(from);
        }

        q.add(1);
        visited[1] = true;
        int cnt = 0;

        while (!q.isEmpty() && cnt < 3) {
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                answer++;

                for (int friend : friends[cur]) {
                    if (visited[friend]) {
                        continue;
                    }

                    visited[friend] = true;
                    q.offer(friend);
                }
            }
            cnt++;
        }

        System.out.println(answer-1);

    }
}
