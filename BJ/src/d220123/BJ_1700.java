package d220123;

import java.io.*;
import java.util.*;

/*
백준1700 멀티탭 스케줄링

풀이법 :
	 1. 콘센트는 Set으로 관리하고 각 기기별 큐를 두어 몇번째에 꽂히는지 관리합니다.
	 2. 콘센트가 아직 남았거나 이미 꽂혀있는 기기라면 Set에 기기번호를 추가하고 큐에서 하나 빼줍니다.
	 3. 콘센트가 다 찼고 새로운 기기라면 꽂힌 기기중 가장 나중에 꽂힐 예정인 기기를 빼줍니다.

 */


public class BJ_1700 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] order = new int[k]; // 기기 들어오는 순서
        int answer = 0;
        Set<Integer> outlet = new HashSet<>(); // 현재 콘센트에 꽂힌 기기
        HashMap<Integer, ArrayDeque<Integer>> electronic = new HashMap<>(); // 각 기기별 예정된 인덱스

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(st.nextToken());
            order[i] = num;

            if (!electronic.containsKey(num)) {
                electronic.put(num, new ArrayDeque<>());
            }
            electronic.get(num).offer(i);
        }

        for (int i = 0; i < k; i++) {
            int num = order[i];

            // 콘센트에 꽉차있고 새로운 기기일 때 스케줄링
            if (outlet.size() == n && !outlet.contains(num)) {
                int unplug = 0;
                int farIdx = 0;

                // 콘센트에 꽂힌 기기들 확인하기
                for (int key : outlet) {
                    ArrayDeque<Integer> elec = electronic.get(key);
                    int next = elec.isEmpty() ? 101 : elec.peek(); // 큐에 더이상 없다면 101 or 제일 최근에 예정된 인덱스

                    // 제일 멀리 남은 인덱스와 기기번호 갱신
                    if (next > farIdx) {
                        farIdx = next;
                        unplug = key;
                    }
                }
                outlet.remove(unplug); // 제일 나중에 나올 예정인 기기 언플러그
                answer++;
            }

            // 콘센트 set에 기기번호 추가하고 해당 기기 큐에서 인덱스 하나 빼주기
            outlet.add(num);
            electronic.get(num).poll();
        }

        System.out.println(answer);
    }
}
