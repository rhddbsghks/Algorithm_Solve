package d220227;

import java.util.*;
import java.io.*;

/*
백준20055 컨베이어 벨트 위의 로봇

풀이법 :
	 1. 벨트를 2차원 배열로 내구도, 로봇유무를 관리하고 시작, 끝점을 투포인터로 관리합니다.
	 2. step 별로 실행해줍니다.

 */


public class BJ_20055 {

    static int n, k, start, end, belt[][];  // [[내구도, 로봇], []...]

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        start = 0;
        end = n - 1;
        belt = new int[2 * n][2];
        int level = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            belt[i][0] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            level++;
            step1(); // 벨트 회전
            step2(); // 로봇 이동
            step3(); // 시작에 로봇 올리기

            // 종료 체크
            if (step4()) break;
        }

        System.out.println(level);
    }

    static void step1() {

        start = (start - 1 + 2 * n) % (2 * n);
        end = (end - 1 + 2 * n) % (2 * n);
        checkArrive();
    }

    static void step2() {

        for (int i = 1; i < n; i++) {
            int current = (end - i + 2 * n) % (2 * n);
            int next = (current + 1) % (2 * n);

            if (belt[current][1] == 1 && belt[next][1] == 0 && belt[next][0] > 0) {
                belt[current][1] = 0;
                belt[next][1] = 1;
                belt[next][0]--;
                checkArrive();
            }
        }
    }

    static void step3() {

        if (belt[start][0] > 0) {
            belt[start][1] = 1;
            belt[start][0]--;
        }
    }

    static boolean step4() {

        int cnt = 0;

        for (int i = 0; i < 2 * n; i++) {
            if (belt[i][0] == 0) {
                cnt++;
            }
        }

        return cnt >= k;
    }

    // 끝점 로봇 체크
    static void checkArrive() {

        if (belt[end][1] == 1) {
            belt[end][1] = 0;
        }
    }
}
