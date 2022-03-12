package d220312;

import java.util.*;

/*
프로그래머스42626 더 맵게

풀이법 :
        PriorityQueue 사용

 */

public class PG_42626 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int s : scoville) {
            pq.offer(s);
        }

        while (true) {
            int first = pq.poll();

            if(first>= K){
                return answer;
            }

            if(pq.isEmpty())
                return -1;

            int second = pq.poll();

            if (first == 0 && second == 0) {
                return -1;
            }

            pq.offer(first + second*2);
            answer++;
        }
    }
}
