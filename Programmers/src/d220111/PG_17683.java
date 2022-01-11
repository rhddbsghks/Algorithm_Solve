package d220111;

import java.util.*;

/*
프로그래머스17683 방금그곡

풀이법 :
	 1. #음은 소문자로 치환해줍니다.
	 2. N<=1439이기 때문에 이중루프로 패턴을 검색했습니다.

 */


public class PG_17683 {

    String solution(String m, String[] musicinfos) {
        List<String> thatSong = new ArrayList<>();
        int max = 0;
        
        // 패턴 변환
        m = m
                .replaceAll("C#", "c")
                .replaceAll("D#", "d")
                .replaceAll("F#", "f")
                .replaceAll("G#", "g")
                .replaceAll("A#", "a");

        for (String musicInfo : musicinfos) {
            String[] info = musicInfo.split(",");

            int playtime = 60 * (Integer.parseInt(info[1].substring(0, 2)) - Integer.parseInt(info[0].substring(0, 2)))
                    + (Integer.parseInt(info[1].substring(3, 5)) - Integer.parseInt(info[0].substring(3, 5)));
            String title = info[2];
            String melody = info[3];
            // 곡 멜로디 변환
            melody = melody
                    .replaceAll("C#", "c")
                    .replaceAll("D#", "d")
                    .replaceAll("F#", "f")
                    .replaceAll("G#", "g")
                    .replaceAll("A#", "a");

            // 총 플레이시간에 맞게 멜로디 이어붙이기
            String totalMelody = "";
            int len = melody.length();
            for (int i = 0; i < playtime; i++)
                totalMelody += melody.charAt(i % len);

            // 패턴보다 전체멜로디가 작다면 넘어가기
            if (totalMelody.length() < m.length()) continue;

            LOOP:
            for (int i = 0; i <= totalMelody.length() - m.length(); i++) {
                for (int j = 0; j < m.length(); j++) {

                    if (totalMelody.charAt(i + j) == m.charAt(j)) {
                        // 마지막까지 일치할 때
                        if (j == m.length() - 1) {
                            if (playtime >= max) {
                                // 최고 플레이시간이면 List 초기화
                                if (playtime > max) {
                                    max = playtime;
                                    thatSong = new ArrayList<>();
                                }
                                thatSong.add(title);
                            }
                            break LOOP;
                        }
                        continue;
                    }
                    
                    // 패턴 일치 안하면 바로 다음인덱스로
                    break;
                }
            }
        }

        return thatSong.size() == 0 ? "(None)" : thatSong.get(0);
    }
}
