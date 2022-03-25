package d220325;

import java.util.*;
import java.io.*;

public class BJ_21610 {

    static int n, m, map[][], tmpMap[][];
    static List<int[]> cloudPos;
    static boolean[][] prevCloud;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        tmpMap = new int[n][n];
        prevCloud = new boolean[n][n];
        cloudPos = new ArrayList<>();

        cloudPos.add(new int[]{n - 1, 0});
        cloudPos.add(new int[]{n - 1, 1});
        cloudPos.add(new int[]{n - 2, 0});
        cloudPos.add(new int[]{n - 2, 1});

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveAndRain(d, s);
            waterBug();
            makeCloud();
        }

        System.out.println(calcSum());
    }

    static void moveAndRain(int d, int s) {
        for (int[] pos : cloudPos) {
            for (int i = 0; i < s; i++) {
                pos[0] = (n + pos[0] + dr[d]) % n;
                pos[1] = (n + pos[1] + dc[d]) % n;
            }

            map[pos[0]][pos[1]]++;
            prevCloud[pos[0]][pos[1]] = true;
        }
    }

    static void waterBug() {
        for (int[] pos : cloudPos) {
            for (int d = 2; d <= 8; d += 2) {

                int nr = pos[0] + dr[d];
                int nc = pos[1] + dc[d];

                if (nr < 0 || nc < 0 || nr == n || nc == n) {
                    continue;
                }

                if (map[nr][nc] > 0) {
                    tmpMap[pos[0]][pos[1]]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += tmpMap[i][j];
                tmpMap[i][j] = 0;
            }
        }
    }

    public static void makeCloud() {
        cloudPos.clear();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] >= 2 && !prevCloud[i][j]) {
                    map[i][j] -= 2;
                    cloudPos.add(new int[]{i, j});
                }

                if (prevCloud[i][j]) {
                    prevCloud[i][j] = false;
                }
            }
        }
    }

    public static int calcSum() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += map[i][j];
            }
        }

        return answer;
    }
}
