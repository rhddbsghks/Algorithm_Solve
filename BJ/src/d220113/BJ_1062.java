package d220113;

import java.io.*;
import java.util.*;

/*
백준 1062 가르침

풀이법 :
	 1. antic 5개는 기본으로 알아야 하므로 k<5일시 0, 그 외에는 모든 단어에서 5개의 알파벳을 제거합니다.
	 2. 21Ck로 알파벳을 선택해 Set에 담고 단어들이 Set에 있는 문자로만 이루어지면 카운트해줍니다.
	 3. 이 값을 비교해 최댓값을 갱신합니다.

비트마스킹 문제였군요... 잊고 있었네요.
 */

public class BJ_1062 {

    static int n, k, max;
    static char a = 'a', z = 'z';
    static String[] words;
    static Set<Character> ch;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        max = 0;
        words = new String[n];
        ch = new HashSet<>(); // 선택한 알파벳 Set

        if (k < 5) {
            System.out.println(0);
            return;
        }

        // a, t, n, c, i는 기본이므로 단어에서 제거
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine()
                    .replaceAll("a", "")
                    .replaceAll("t", "")
                    .replaceAll("n", "")
                    .replaceAll("c", "")
                    .replaceAll("i", "");
        }

        comb(0, a);
        System.out.println(max);
    }

    static void comb(int cnt, int start) {
        if (cnt == k - 5) { // 5개 알파벳은 이미 선택했음
            int canRead = 0;

            LOOP:
            for (String word : words) {
                for (int i = 0; i < word.length(); i++) {
                    if (!ch.contains(word.charAt(i))) // 해당 단어에서 한 알파벳라도 모르면 패스
                        continue LOOP;
                }
                canRead++;
            }

            max = Math.max(max, canRead);
            return;
        }

        for (int i = start; i < z; i++) {
            if (i == 'a' || i == 'n' || i == 't' || i == 'i' || i == 'c')
                continue;
            ch.add((char) i);
            comb(cnt + 1, i + 1);
            ch.remove((char) i); // 배열에 덮어쓰는 형식이 아니기 때문에 제거도 해줌
        }
    }
}
