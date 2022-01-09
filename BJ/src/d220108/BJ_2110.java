package d220108;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 2110 공유기 설치

풀이법 :
	 1. 집 좌표들을 오름차순 정렬 후, 1~ 끝집-첫집 범위로 공유기를 설치해봅니다.
	 2. n = 1,000,000,000 이고 O(N^2)이므로 이분탐색으로 해결합니다.

구글링으로 아이디어 참고했습니다. 저는 이분탐색도 연습이 필요할듯 하네요..
 */


public class BJ_2110 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int house[] = new int[n];

        for (int i = 0; i < n; i++)
            house[i] = Integer.parseInt(br.readLine());

        Arrays.sort(house);

        int start = 1, end = house[n - 1] - house[0], mid = (start + end) / 2;

        while (start <= end) {
            // 첫 집에 공유기 설치
            int prev = house[0];
            int router = 1;

            // mid 간격으로 공유기 설치해보기
            for (int i = 1; i < n; i++) {
                if (house[i] - prev >= mid) {
                    prev = house[i];
                    router++;
                }
            }

            // 필요 이상으로 공유기를 설치했다면 간격을 더 넓혀본다.
            if (router >= c) {
                start = mid + 1;
                mid = (start + end) / 2;
            }

            else {
                end = mid - 1;
                mid = (start + end) / 2;
            }
        }

        System.out.println(mid);
    }
}