package d220319;

import java.util.*;
import java.io.*;

public class BJ_1012 {

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int m, n;
    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testcase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            map = new int[m][n];
            visited = new boolean[m][n];
            int answer = 0;

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(
                    st.nextToken())] = 1;
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j]==1 && !visited[i][j]) {
                        answer++;
                        dfs(i, j);
                    }
                }
            }

            System.out.println(answer);
        }
    }

    static void dfs(int i, int j) {

        visited[i][j] = true;

        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (nr <0 || nc < 0 || nr == m || nc == n || visited[nr][nc] || map[nr][nc]!=1) {
                continue;
            }

            dfs(nr, nc);
        }
    }
}
