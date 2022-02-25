package d220225;

import java.util.*;

/*
프로그래머스42885 구명보트

풀이법 :
	 1. 최대 2명이 탈 수 있으므로 가장 무거운 사람을 태운 후 가벼운 사람 한 명을 태워봅니다.

 */

public class PG_42885 {

    int solution(int[] people, int limit) {

        Arrays.sort(people); // 무게순으로 정렬

        int heavy = people.length - 1;
        int light = 0;
        int answer=0;

        while (true) {
            int boat = people[heavy];

            // 자리 남으면 제일 가벼운 사람도 태우기
            if(light<heavy && people[light]+boat<=limit)
                light++;

            heavy--;
            answer++;

            if(light>heavy)
                break;
        }

        return answer;
    }
}
