package d220407;

import java.util.*;
import java.io.*;

public class BJ_14889 {

    static int n, answer, map[][];
    static boolean member[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        answer = Integer.MAX_VALUE;
        map = new int[n][n];
        member = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeTeam(0, 0);

        System.out.println(answer);
    }

    static void makeTeam(int cnt, int start) {
        if (cnt == n / 2) {
            calc();
            return;
        }

        for (int i = start; i < n; i++) {
            member[i] = true;
            makeTeam(cnt + 1, i + 1);
            member[i] = false;
        }
    }

    static void calc() {
        int score1;
        int score2;
        score1 = score2 = 0;

        for (int i = 0; i < n; i++) {
            boolean team = member[i];
            int score = 0;

            for (int j = 0; j < n; j++) {
                if (i == j || member[j] != team) {
                    continue;
                }

                score += map[i][j];
            }

            if (team) {
                score1 += score;
            } else {
                score2 += score;
            }
        }

        answer = Integer.min(Math.abs(score1 - score2), answer);
    }
}
