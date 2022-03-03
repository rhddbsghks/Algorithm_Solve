package d220303;

import java.io.*;
import java.util.*;

/*
백준1107 리모컨

풀이법 :
	 1. 타겟 번호 자리수와 +-1 자리수인 수를 dfs로 생성합니다.
	 2. 생성한 후 +- 버튼으로 타겟 번호까지 이동해봅니다.

그냥 0~999999 번호를 다 해보는게 훨씬 효율적이었네요.
 */


public class BJ_1107 {

    static String target;
    static String trial;
    static int answer;
    static Set<Integer> breakdown;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        breakdown = new HashSet<>();
        answer = Math.abs(100 - Integer.parseInt(target));
        trial = ""; // 시도해볼 번호

        int n = Integer.parseInt(br.readLine());

        if (n != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                breakdown.add(Integer.parseInt(st.nextToken()));
            }
        }

        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int cnt) {

        // 타겟 번호와 일치하는 자리수 or +-1 자리수인 숫자 도착
        if (cnt < 0 && (cnt == target.length() || cnt == target.length() - 1
            || cnt == target.length() + 1)) {
            answer = Integer.min(answer,
                Math.abs(Integer.parseInt(trial) - Integer.parseInt(target)) + trial.length());

            if (cnt == target.length() + 1) {
                return;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (breakdown.contains(i)) {
                continue;
            }

            trial += i;
            dfs(cnt + 1);
            trial = trial.substring(0, trial.length() - 1);
        }
    }
}
