package d220225;

import java.util.*;

/*
프로그래머스72411 메뉴 리뉴얼

풀이법 :
	 1. Map으로 <조합, 주문횟수>를 관리합니다.
	 2. 각 손님별로 course[]의 길이들만큼 메뉴조합을 Map에 카운트합니다.
	 3. Map에서 course 길이별로 최다 주문된 코스를 리스트에 저장합니다.

 */

public class PG_72411 {

    Map<String, Integer> menus = new HashMap<>(); // 전체 가능한 조합들 카운트
    String menu;
    boolean visited[];

    List<String> solution(String[] orders, int[] course) {

        // 각 손님별 단품메뉴 조합
        for (String order : orders) {
            // 단품 메뉴 갯수별 조합 생성
            for (int len : course) {
                menu = "";
                visited = new boolean[order.length()];
                
                // 단품메뉴 조합을 알파벳순 정렬
                char[] toChar = order.toCharArray();
                Arrays.sort(toChar);
                order = new String(toChar);

                // 단품메뉴 조합에서 len개 조합해서 Map에 카운트
                comb(order, 0, 0, len);
            }
        }

        return makeAnswer(course);
    }

    void comb(String order, int cnt, int start, int len) {
        // len개 뽑았으면 Map에 카운트
        if (cnt == len) {
            if (!menus.containsKey(menu)) {
                menus.put(menu, 0);
            }
            menus.put(menu, menus.get(menu) + 1);
            return;
        }

        for (int i = start; i < order.length(); i++) {
            if (visited[i]) {
                continue;
            }

            menu += order.charAt(i);
            comb(order, cnt + 1, i + 1, len);
            menu = menu.substring(0, menu.length() - 1);
        }
    }

    // Map에서 갯수별로 최다 주문건 리스트에 추가
    List<String> makeAnswer(int[] course) {
        Map<Integer, Integer> max = new HashMap<>(); // len 별 최다 주문횟수
        Map<Integer, List<String>> maxCourse = new HashMap<>(); // 최다 주문횟수의 메뉴 리스트(여러개일수 있음)
        List<String> answer = new ArrayList<>();

        for (int i : course) {
            max.put(i, 0);
        }

        for (String combination : menus.keySet()) {
            int len = combination.length();
            int cnt = menus.get(combination);
            int maxCnt = max.get(len);

            // 원하던 길이의 조합이 아니거나 1명만 주문했으면 건너뛰기
            if (!max.containsKey(len) || cnt < 2) {
                continue;
            }

            // 최다 주문횟수 및 메뉴리스트 갱신
            if (cnt >= maxCnt) {
                if (cnt > maxCnt) {
                    maxCourse.put(len, new ArrayList<>());
                }

                maxCourse.get(len).add(combination);
                max.put(len, cnt);
            }
        }

        for (int key : maxCourse.keySet()) {
            List<String> list = maxCourse.get(key);
            for (String combination : list) {
                answer.add(combination);
            }
        }

        Collections.sort(answer);

        return answer;
    }
}
