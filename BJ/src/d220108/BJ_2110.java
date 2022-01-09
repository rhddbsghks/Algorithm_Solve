package d220108;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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

        while (start < end) {
            int prev = house[0];
            int router = 1;

            for (int i = 1; i < n; i++) {
                if (house[i] - prev >= mid) {
                    prev = house[i];
                    router++;
                }
            }

            if (router >= c) {
                start = mid;
                mid = (start + end) / 2;
            } else {
                end = mid;
                mid = (start + end) / 2;
            }

            if(start==mid) break;
        }

        System.out.println(mid);
    }
}