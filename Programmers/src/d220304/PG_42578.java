package d220304;

import java.util.*;

/*
프로그래머스42578 위장

풀이법 :
        해쉬를 써서 경우의 수를 계산하는 간단한 문제였습니다.

 */


public class PG_42578 {

    public int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, Set<String>> total = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            String cloth = clothes[i][0];
            String type = clothes[i][1];

            if (!total.containsKey(type)) {
                total.put(type, new HashSet<>());
            }

            total.get(type).add(cloth);
        }

        for (String type : total.keySet()) {
            answer *= (total.get(type).size() + 1);
        }

        return answer - 1;
    }
}
