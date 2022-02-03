package d220203;

/*
프로그래머스17677 뉴스 클러스터링

풀이법 :
	 1. Map으로 원소 개수들을 관리해주고 조건에 맞게 합/교집합 원소 수를 계산해줍니다.

쉬운 문제에 삽질을 너무 했네요... 항상 문제는 꼼꼼히ㅠ
 */

import java.util.*;

public class PG_17677 {

    int solution(String str1, String str2) {
        str1 = str1.toLowerCase(Locale.ROOT);
        str2 = str2.toLowerCase(Locale.ROOT);

        Map<String, Integer> first = makeJaccard(str1);
        Map<String, Integer> second = makeJaccard(str2);

        double union = 0;
        double intersection = 0;

        for (String key : first.keySet()) {
            union += first.get(key);
        }

        for (String key : second.keySet()) {
            if (first.containsKey(key)) {
                int firstVal = first.get(key);
                int secondVal = second.get(key);
                intersection += Math.min(firstVal, secondVal);

                if (secondVal >= firstVal) {
                    union += secondVal - firstVal;
                }

            } else {
                union += second.get(key);
            }
        }

        if (intersection == 0 && union == 0) {
            return 1;
        }

        return (int) (intersection / union * 65536);
    }

    Map<String, Integer> makeJaccard(String str) {

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length() - 1; i++) {
            if (!isAlpha(str.charAt(i)) || !isAlpha(str.charAt(i + 1))) {
                continue;
            }

            String element = str.substring(i, i + 2);

            if (!map.containsKey(element)) {
                map.put(element, 0);
            }

            map.put(element, map.get(element) + 1);
        }

        return map;
    }

    boolean isAlpha(char c) {
        return 'a' <= c && c <= 'z';
    }
}
