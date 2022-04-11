package d220411;

import java.util.*;
import java.io.*;

/*
백준4991 로봇 청소기

풀이법 :
	 1. 시작점과 더러운 칸 사이의 모든 최소 거리들을 계산해둡니다.
	 2. 완전탐색으로 더러운 칸을 방문하는 모든 순서쌍을 구하고 최솟값을 갱신합니다.
	 3. 한번이라도 다음칸으로 못가는 순서쌍이 나오면 -1을 출력합니다.

 */

public class BJ_4991 {

    static int w, h, dirty, answer, order[], distance[][];
    static char[][] map;
    static boolean[] used;
    static List<int[]> dirtyPos;
    static int[] startPos;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            dirtyPos = new ArrayList<>();
            dirty = 0;
            answer = Integer.MAX_VALUE;
            map = new char[h][w];

            startPos = new int[2];

            if (w == 0 && h == 0) {
                break;
            }

            for (int i = 0; i < h; i++) {
                char[] input = br.readLine().toCharArray();

                for (int j = 0; j < w; j++) {
                    map[i][j] = input[j];

                    if (map[i][j] == 'o') {
                        startPos[0] = i;
                        startPos[1] = j;
                    }

                    if (map[i][j] == '*') {
                        dirty++;
                        dirtyPos.add(new int[]{i, j});
                    }
                }
            }

            distance = new int[dirty + 1][dirty + 1];
            dirtyPos.add(0, startPos);

            for (int i = 0; i <= dirty; i++) {
                int[] start = dirtyPos.get(i);

                for (int j = i + 1; j <= dirty; j++) {
                    int[] end = dirtyPos.get(j);

                    distance[i][j] = distance[j][i] = getDistance(start, end);
                }
            }

            order = new int[dirty + 1];
            used = new boolean[dirty + 1];

            perm(1);
            sb.append(answer + "\n");
        }

        System.out.println(sb);
    }

    static void perm(int cnt) {
        if (cnt == dirty + 1) {
            // do
            int dist = 0;

            for (int i = 0; i < order.length - 1; i++) {
                int start = order[i];
                int end = order[i + 1];
                int d = distance[start][end];

                if (d == -1) {
                    answer = -1;
                    return;
                }

                dist += d;
            }

            answer = Integer.min(answer, dist);

            return;
        }

        for (int i = 1; i <= dirty; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            order[cnt] = i;
            perm(cnt + 1);
            used[i] = false;
        }
    }

    static int getDistance(int[] start, int[] end) {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h][w];
        q.offer(start);
        visited[start[0]][start[1]] = true;
        int dist = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] pos = q.poll();
                int r = pos[0];
                int c = pos[1];

                if (r == end[0] && c == end[1]) {
                    return dist;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nc < 0 || nr == h || nc == w || visited[nr][nc]
                        || map[nr][nc] == 'x') {
                        continue;
                    }

                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }

            dist++;
        }

        return -1;
    }
}
