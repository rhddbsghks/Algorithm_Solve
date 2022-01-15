package d220115;

import java.io.*;
import java.util.*;

/*
백준 16234 인구 이동

풀이법 :
	 1. dfs로 조건에 맞는 지역들을 그룹지어 좌표와 인구수를 계산합니다.
	 2. 새로운 map에 이동시킨 인구들을 저장시킵니다.
	 3. 한 번이라도 이동을 못시킬때까지 반복합니다.

 */


public class BJ_16234 {

    static int n, l, r, total, answer, map[][];
    static boolean flag, visited[][];
    static int dr[] = new int[]{-1, 1, 0, 0};
    static int dc[] = new int[]{0, 0, -1, 1};

    static Queue<int[]> pos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        answer = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }


        while (true) {
            visited = new boolean[n][n];
            int[][] next = new int[n][n];
            flag = false; // 인구 이동시킬때 true

            // map 전체 dfs로 연합 국가들 탐색
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (visited[i][j])
                        continue;
                    
                    total = 0; // 연합 인구 수
                    pos = new ArrayDeque<>(); // 연합 국가 좌표
                    dfs(i, j); // 연합 국가 탐색
                    move(next); // pos에 있는 좌표들 next에 이동
                }

            if (!flag) break; // 한번도 이동 못하면 종료

            answer++;
            map = next; // 완성된 next를 map에 저장
        }
        System.out.println(answer);
    }

    static void dfs(int i, int j) {
        visited[i][j] = true;
        total += map[i][j];
        pos.offer(new int[]{i, j});

        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (oob(nr, nc))
                continue;

            // 차이가 조건에 안맞으면 건너뛰기
            int dif = Math.abs(map[i][j] - map[nr][nc]);
            if (dif < l || dif > r)
                continue;

            // 하나라도 연결된 국가 있으면 flag true
            if (!visited[nr][nc]) {
                flag = true;
                dfs(nr, nc);
            }
        }
    }

    // pos 좌표들에 인구수 배분
    static void move(int[][] next) {
        int people = total / pos.size();

        while (!pos.isEmpty()) {
            int[] p = pos.poll();
            next[p[0]][p[1]] = people;
        }
    }

    static boolean oob(int i, int j) {
        return i < 0 || j < 0 || i == n || j == n;
    }
}
