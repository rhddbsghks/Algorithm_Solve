package d220313;

import java.io.*;
import java.util.*;

/*
백준15684 사다리 조작

풀이법 :
	 1. 사다리는 2차원 boolean 배열로 관리합니다.
	 2. 우선순위큐에서 숫자를 하나씩 빼주고 해당 수 바로 위칸의 수를 다시 넣어줍니다.
	 3. 사다리를 0개, 1개, 2개... 순으로 dfs로 뽑아봅니다.
	 4. 한번이라도 완성되면 종료합니다.

 */


public class BJ_15684 {

    static int n, m, h, answer;
    static boolean[][] ladder;
    static int[] dr = {-1, 0, 0};
    static int[] dc = {0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt((st.nextToken()));
        h = Integer.parseInt(st.nextToken());
        ladder = new boolean[h][n];
        answer = 4;

        for (int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            ladder[i][j] = true;
        }

        for (int i = 0; i < 4; i++) {
            // 사다리 i개 놓아보고 체크
            // 가능하면 바로 종료
            if (dfs(0, 0, i)) {
                break;
            }
        }

        System.out.println(answer == 4 ? -1 : answer);
    }

    // 사다리를 target개 뽑고 체크
    public static boolean dfs(int cnt, int start, int target) {

        if (cnt == target) {
            if (checkLadder()) {
                answer = Integer.min(answer, cnt);
                return true;
            }

            return false;
        }

        for (int k = start; k < n * h; k++) {
            int i = k / n;
            int j = k % n;

            if (j == n - 1 || ladder[i][j] || (j > 0 && ladder[i][j - 1]) || ladder[i][j
                + 1]) {
                continue;
            }

            ladder[i][j] = true;

            // 한 번이라도 성공하면 종료
            if (dfs(cnt + 1, k + 1, target)) {
                return true;
            }

            ladder[i][j] = false;
        }

        return false;
    }

    public static boolean checkLadder() {

        int i, j;

        for (int l = 0; l < n; l++) {
            i = 0;
            j = l;

            while (i != h) {

                if (j > 0 && ladder[i][j - 1]) {
                    j = j - 1;
                } else if (ladder[i][j]) {
                    j = j + 1;
                }

                i++;
            }

            if (j != l) {
                return false;
            }
        }

        return true;
    }
}