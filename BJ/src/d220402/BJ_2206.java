package d220402;

import java.util.*;
import java.io.*;

public class BJ_2206 {

    static int n, m, answer, map[][];
    static boolean visited[][][];
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][2];
        answer = -1;
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        q.offer(new int[]{0, 0, 1, 0});
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int len = cur[2];
            int breakCnt = cur[3];

            if (r == n - 1 && c == m - 1) {
                answer = len;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr == n || nc == m) {
                    continue;
                }

                if (map[nr][nc] == 0 && !visited[nr][nc][breakCnt]) {
                    visited[nr][nc][breakCnt] = true;
                    q.offer(new int[]{nr, nc, len + 1, breakCnt});
                } else if (map[nr][nc] == 1 && breakCnt == 0 && !visited[nr][nc][1]) {
                    visited[nr][nc][1] = true;
                    q.offer(new int[]{nr, nc, len + 1, 1});
                }
            }
        }

        System.out.println(answer);
    }
}
