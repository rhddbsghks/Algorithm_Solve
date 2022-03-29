package d220329;

import java.io.*;
import java.util.*;

public class BJ_1654 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int answer = 0;
        int[] lan = new int[k];

        for (int i = 0; i < k; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(lan);

        long left = 1;
        long right = lan[k - 1];

        while (left <= right) {
            long mid = (left + right) / 2;
            int tmp = 0;

            for (int i = 0; i < k; i++) {
                tmp += lan[i] / mid;
            }

            if (tmp < n) {
                right = mid - 1;
            } else {
                answer = (int)mid;
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
