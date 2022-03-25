package d220325;

import java.util.*;
import java.io.*;

public class BJ_11003 {

    static class Num {

        int num;
        int idx;

        Num(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Num> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num));

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            pq.offer(new Num(Integer.parseInt(st.nextToken()), i));

            while (pq.peek().idx < i - l + 1) {
                pq.poll();
            }

            sb.append(pq.peek().num+" ");
        }

        System.out.println(sb);
    }
}
