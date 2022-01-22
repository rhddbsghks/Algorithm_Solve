package d220122;

import java.util.*;
import java.io.*;

/*
백준17136 색종이 붙이기

풀이법 :
	 1. 모든 크기 색종이를 놓아보며 완전탐색으로 해결하였습니다.
	 2. 보드를 탐색하다 커버되지 않은 점을 만나면 1~5 색종이를 놓아보고 불가능하면 다음 크기 색종이는 보지 않습니다.
	 3. 주어진 개수 내에서 커버를 완료하면 answer 최솟값을 갱신합니다.

 */


public class BJ_17136 {

    static int answer, used[];
    static boolean[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new boolean[10][10];
        answer = Integer.MAX_VALUE;
        used = new int[6];

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = st.nextToken().equals("0"); // 보드에서 0이면 커버된곳이므로 true
            }
        }

        dfs(0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer); // 한번도 갱신 못했으면 -1
    }

    static void dfs(int cnt) {
        // 끝점 도착했으면 사용한 색종이 수 갱신
        if (cnt == 100) {
            int sum = 0;
            for (int i = 1; i <= 5; i++) {
                sum += used[i];
            }
            answer = Math.min(answer, sum);
            return;
        }

        int r = cnt / 10;
        int c = cnt % 10;

        // 커버된 점이면 바로 다음 칸으로
        if (board[r][c]) {
            dfs(cnt + 1);
            return;
        }

        // 해당 칸에 1~5 색종이를 놓아본다.
        for (int paper = 1; paper <= 5; paper++) {
            // 놓은 색종이가 범위 밖이면 종료
            if (r + paper > 10 || c + paper > 10)
                return;
            
            // 해당 크기 5개 다 썼으면 다음 크기로
            if (used[paper] == 5)
                continue;
            
            // 해당 크기 놓는게 불가능하면 종료
            if (!check(cnt, paper))
                return;

            used[paper]++;
            cover(cnt, paper); // 커버영역 보드에 표시
            dfs(cnt + 1);
            used[paper]--;
            cover(cnt, paper); // 표시한거 되돌리기
        }
    }

    // 해당 위치를 왼쪽 상단으로 하여 paper 크기 놓을 수 있는지 판단
    static boolean check(int cnt, int paper) {
        int r = cnt / 10;
        int c = cnt % 10;

        for (int i = r; i < r + paper; i++) {
            for (int j = c; j < c + paper; j++) {
                if (board[i][j]) // 한곳이라도 커버된 곳 있으면 false
                    return false;
            }
        }
        return true;
    }

    // 해당 지점을 왼쪽 상단으로 true false XOR 연산
    static void cover(int cnt, int paper) {
        int r = cnt / 10;
        int c = cnt % 10;

        for (int i = r; i < r + paper; i++) {
            for (int j = c; j < c + paper; j++) {
                board[i][j] = !board[i][j];
            }
        }
    }
}
