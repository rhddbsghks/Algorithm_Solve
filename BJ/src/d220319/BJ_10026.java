package d220319;

import java.io.*;

/*
백준10026

풀이법 :
	 1. 적록색약의 경우 'G'를 'R'로 치환해 배열을 하나더 선언합니다.
	 2. dfs로 영역 카운트

 */

public class BJ_10026 {

    static int n, area;
    static char[][] map, colorBlind;
    static boolean visited[][];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        colorBlind = new char[n][n];

        for (int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                map[i][j] = colorBlind[i][j] = input[j];

                if (colorBlind[i][j] == 'G') {
                    colorBlind[i][j] = 'R';
                }
            }
        }

        area = 0;
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, map);
                    area++;
                }
            }
        }

        System.out.print(area+" ");

        area = 0;
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, colorBlind);
                    area++;
                }
            }
        }

        System.out.println(area);
    }

    static void dfs(int i, int j, char[][] target) {

        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (nr < 0 || nc < 0 || nr == n || nc == n || visited[nr][nc]
                || target[i][j] != target[nr][nc]) {
                continue;
            }

            visited[nr][nc] = true;
            dfs(nr, nc, target);
        }
    }
}
