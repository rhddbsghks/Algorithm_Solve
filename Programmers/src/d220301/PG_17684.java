package d220301;

import java.util.*;

/*
프로그래머스17684 압축

풀이법 :
	 1. Map으로 인덱스를 관리합니다.
	 2. 새로운 단어가 나올때까지 인덱스를 늘려가며 단어를 생성합니다.
	 3. 새로운 단어는 인덱스를 추가하고 현재 단어 인덱스를 answer에 추가합니다.

 */

public class PG_17684 {

    List<Integer> solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> dictionary = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            dictionary.put(new String("" + (char) ('A' + i)), i + 1);
        }

        for (int idx = 0; idx < msg.length(); ) {
            int len = 1;
            if (idx < msg.length()) {
                int end = idx;
                String existWord = "";

                // 끝 인덱스 늘려가며 새로운 단어 찾기
                while (++end <= msg.length()) {
                    String checkWord = msg.substring(idx, end);
                    if (!dictionary.containsKey(checkWord)) {
                        dictionary.put(checkWord, dictionary.size() + 1);
                        idx = end - 1;
                        break;
                    }

                    existWord = checkWord;

                    // 현재 단어가 끝이면 종료
                    if (end == msg.length()) {
                        idx = end;
                    }
                }

                answer.add(dictionary.get(existWord));
            }
        }
        return answer;
    }
}
