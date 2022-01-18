package d220116;

import java.io.*;
import java.util.*;

/*
백준 2533 사회망 서비스(SNS)

풀이법 :
	 1. 본인이 얼리X -> 자식은 무조건 얼리 / 얼리O -> 자식은 얼리 일수도아닐수도
	 2. 본인이 얼리일때는 자식의 두가지 경우중 얼리 수가 적은 놈을 택합니다.
	 3. bottom-up 방식으로 dp 테이블을 채워갑니다.

 */


public class BJ_2533 {

    static boolean visited[];
    static int n, dp[][];
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        dp = new int[n + 1][2]; // dp 테이블 0: 얼리X, 1: 얼리
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int num) {
        visited[num] = true;
        dp[num][0] = 0; // 내가 얼리가 아닐 때
        dp[num][1] = 1; // 내가 얼리이므로 +1

        for (int child : graph[num]) {
            if (!visited[child]) {
                dfs(child);

                dp[num][0] += dp[child][1]; // 내가 얼리아니면 자식들은 무조건 얼리
                dp[num][1] += Math.min(dp[child][0], dp[child][1]); // 내가 얼리면 둘 중 최솟값
            }
        }
    }
}


