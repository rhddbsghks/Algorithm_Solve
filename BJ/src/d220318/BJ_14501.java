package d220318;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14501 {

    static int[][] schedule;
    static int answer, n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        schedule = new int[n + 1][2];
        answer = 0;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(1, 0, 0);

        System.out.println(answer);
    }

    static void dfs(int start, int cost, int profit) {

        if (start > n + 1) {
            answer = Math.max(answer, profit);
            return;
        }

        if(start==n+1){
            answer = Math.max(answer, profit+cost);
            return;
        }

        for (int i = start; i <= n; i++) {
            dfs(i + schedule[i][0], schedule[i][1], profit+cost);
        }
    }
}
