package d220325;

import java.util.*;
import java.io.*;

public class BJ_1890 {

    static int n, map[][];
    static long answer;
    static long dp[][];
    static boolean visited[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new long[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(dp[0][0]);
    }

    public static long dfs(int i, int j) {

        if (i == n - 1 && j == n - 1) {
            return 1;
        }

        if (map[i][j] == 0) {
            return 0;
        }

        if (i + map[i][j] < n && visited[i + map[i][j]][j]) {
            dp[i][j] += dp[i + map[i][j]][j];
        } else if (i + map[i][j] < n) {
            dp[i][j] += dfs(i + map[i][j], j);
        }

        if (j + map[i][j] < n && visited[i][j + map[i][j]]) {
            dp[i][j] += dp[i][j + map[i][j]];
        } else if (j + map[i][j] < n) {
            dp[i][j] += dfs(i, j + map[i][j]);
        }

        visited[i][j] = true;
        return dp[i][j];
    }
}
