package d220112;

import java.util.Scanner;

/*
백준 1300 k번째 수

풀이법 :
	 1. 오름차순 정렬이므로 k번째 수는 1~k 입니다.
	 2. 이 사이의 수들을 이분탐색하여 k번째 수를 계산해줍니다.

이분탐색 너무 어렵네요...
 */


public class BJ_1300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int start = 1, end = k;
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            long order = 0; // mid 이하인 수들의 개수

            // i행 = i배수
            // i행에서 mid 보다 작은 수의 개수는 mid/i 이다.
            // 이때 n*n이므로 mid/i와 n 중 최솟값이 해당 행에서 mid보다 작은 수의 개수이다.
            for (int i = 1; i <= n; i++) {
                order += Math.min(mid / i, n);
            }

            if (order < k) {
                start = mid + 1;

            } else {
                end= mid - 1;
                result=mid;
            }
        }

        System.out.println(result);
    }
}
