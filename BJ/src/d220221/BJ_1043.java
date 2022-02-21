package d220221;

import java.io.*;
import java.util.*;

/*
백준1043 거짓말

풀이법 :
	 1. 진실을 아는 사람을 Set으로 관리합니다.
	 2. 파티에 진실을 아는 사람이 한 명이라도 있으면 해당 파티에 참가한 모든 사람을 Set에 추가합니다.
	 3. Set에 새로운 사람이 추가되지 않을 때까지 반복합니다.

 */


public class BJ_1043 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m, k, answer;
        boolean flag;

        Set<Integer> knowReal = new HashSet<>(); // 진실을 아는 사람 Set
        List<List<Integer>> parties = new ArrayList<>(); // 파티 참가자

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean[] checked = new boolean[n+1];
        answer = 0;

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(st.nextToken());
            knowReal.add(num);
            checked[num] = true;
        }

        for (int i = 0; i < m; i++) {
            parties.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                parties.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        while (true) {
            flag = false;

            for (List<Integer> party : parties) {
                for (int person : party) {
                    // 해당 파티에서 한 명이라도 진실을 안다면
                    if (knowReal.contains((person))) {
                        for (int know : party) {

                            // 이미 알고있는 사람은 패스
                            if(checked[know])
                                continue;

                            knowReal.add(know);
                            checked[know] = true;
                            flag = true;
                        }
                        break;
                    }
                }
            }

            // 진실 아는사람 더이상 업데이트 안되면 종료
            if (!flag) {
                break;
            }
        }

        LOOP:
        for (List<Integer> party : parties) {
            for (int person : party) {
                if (knowReal.contains((person))) {
                    continue LOOP;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}
