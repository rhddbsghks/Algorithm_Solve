package d220106;

import java.util.*;

/*
프로그래머스64065 튜플

풀이법 :
	 1. 문자열을 split하고 길이 순으로 정렬합니다.
	 2. 사용한 숫자들을 set으로 관리하며 튜플을 생성합니다.

 */


public class PG_64065 {
    public List<Integer> solution(String s) {
        List<Integer> answer = new ArrayList<>();
        Set<Integer> used = new HashSet<>(); // 튜플에 사용된 여부 판단 Set

        String[] tmp = s.substring(2, s.length()).split("\\{");
        Arrays.sort(tmp, (o1, o2) -> Integer.compare(o1.length(), o2.length()));

        for (String set : tmp) {
            String[] numbers = set.substring(0, set.length() - 2).split(",");
            
            // 숫자들 차례로 used에 넣어보며 처음 넣는거면 튜플에 추가
            for (String number : numbers) {
                int num = Integer.parseInt(number);

                if (used.add(num)) {
                    answer.add(num);
                    break;
                }
            }
        }

        return answer;
    }
}