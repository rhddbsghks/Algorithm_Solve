package d220108;

import java.io.*;
import java.util.*;

/*
백준 17281 야구

풀이법 :
	 1. n=9 이므로 순열을 생성하여 완전탐색으로 해결하였습니다.
	 2. 순열 생성 시 4번째 자리는 항상 0(1번타자)로 고정하고 계산합니다.

 */

public class BJ_17281 {

    static int innings, answer, order[], players[][];
    static boolean used[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        innings = Integer.parseInt(br.readLine());
        order = new int[9];
        used = new boolean[9];
        players = new int[innings][9]; // 이닝별 선수결과 배열
        answer = 0;

        for (int i = 0; i < innings; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int p = 0; p < 9; p++)
                players[i][p] = Integer.parseInt(st.nextToken());
        }

        // 4번타자 0번선수로 고정 후 순열 생성
        order[3] = 0;
        used[0] = true;
        perm(0);
        
        System.out.println(answer);
    }

    static void play() {
        int hitter, num = 0, score = 0;

        for (int inning = 0; inning < innings; inning++) {
            boolean[] base = new boolean[4]; // 현재타자, 1루, 2루, 3루
            int out = 0;

            while (out < 3) {
                hitter = order[num];

                int hit = players[inning][hitter];
                base[0] = true;

                // 아웃
                if (hit == 0) out++;

                // 안타처리
                else {
                    // 3루부터 베이스+안타 합이 4 이상이면 득점 처리
                    for (int b = 3; b >= 0; b--) {
                        if (!base[b]) continue;

                        base[b] = false;

                        if (b + hit > 3) score++;
                        else base[b + hit] = true;
                    }
                }

                num = (num+1)%9; 
            }
        }
        answer = Math.max(answer, score);
    }

    static void perm(int cnt) {
        // 4번 타자는 고정이므로 패스
        if(cnt==3) {
            perm(cnt+1);
            return;
        }

        if (cnt == 9) {
            play();
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (used[i]) continue;

            used[i] = true;
            order[cnt] = i;
            perm(cnt + 1);
            used[i] = false;
        }
    }
}