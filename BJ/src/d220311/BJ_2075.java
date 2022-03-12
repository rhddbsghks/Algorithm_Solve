package d220311;

import java.io.*;
import java.util.*;

/*
백준2075 N번째 큰 수

풀이법 :
	 1. 제일 아래줄 숫자들을 우선순위큐에 넣어줍니다.
	 2. 우선순위큐에서 숫자를 하나씩 빼주고 해당 수 바로 위칸의 수를 다시 넣어줍니다.

 */

public class BJ_2075 {

    static int n, board[][];

    static class Number {

        int i;
        int j;
        int number;

        Number(int i, int j, int number) {
            this.i = i;
            this.j = j;
            this.number = number;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        // 내림차순 정렬
        PriorityQueue<Number> pq = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o2.number, o1.number));

        int answer = 0;
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (i == n - 1) {
                    pq.offer(new Number(i, j, board[i][j]));
                }
            }
        }

        while (!pq.isEmpty()) {
            Number num = pq.poll();
            answer = num.number;
            cnt++;

            if (num.i >0) {
                pq.offer(new Number(num.i - 1, num.j, board[num.i - 1][num.j]));
            }

            if(cnt==n)
                break;
        }
        System.out.println(answer);
    }
}
