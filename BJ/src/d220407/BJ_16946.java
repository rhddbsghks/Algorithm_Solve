package d220407;

import java.util.*;
import java.io.*;

public class BJ_16946 {

    static int n, m, map[][];
    static boolean visited[][];
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        int num = 2;
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 || visited[i][j]) {
                    continue;
                }

                int cnt = dfs(i, j, num);
                count.put(num++, cnt);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 1) {
                    sb.append(0);
                    continue;
                }

                int tmp = 0;
                Set<Integer> set = new HashSet<>();

                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (nr < 0 || nc < 0 || nr == n || nc == m || map[nr][nc] == 1 || set.contains(
                        map[nr][nc])) {
                        continue;
                    }

                    set.add(map[nr][nc]);
                    tmp += count.get(map[nr][nc]);
                }

                sb.append((tmp + 1) % 10);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int dfs(int i, int j, int num) {

        int cnt = 1;
        visited[i][j] = true;
        map[i][j] = num;

        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (nr < 0 || nc < 0 || nr == n || nc == m || visited[nr][nc] || map[nr][nc] == 1) {
                continue;
            }

            cnt += dfs(nr, nc, num);
        }

        return cnt;
    }
}
