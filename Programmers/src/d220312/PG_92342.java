package d220312;

/*
프로그래머스92342 양궁대회

풀이법 :
        1. dfs로 해당 점수 과녁을 맞추기 or 안맞추기로 분기해나갑니다.
        2. 화살은 항상 어피치+1 만큼 맞추고 화살이 부족하면 그냥 넘어갑니다.
        3. 0점까지 갔을 때 남은 화살은 0점에 몰아줍니다.

 */


public class PG_92342 {

    int[] shoot = new int[11];
    int n, max;
    int[] apeachShoot, answer;
    boolean flag;

    public int[] solution(int N, int[] info) {
        answer = new int[11];
        n = N;
        apeachShoot = info;
        max = 1;
        flag = false;

        dfs(0, 0);

        return flag ? answer : new int[]{-1};
    }

    public void dfs(int cnt, int arrow) {
        // 모든 점수 과녁 체크 완료
        if (cnt == 11) {
            shoot[10] = n - arrow; // 남은 화살은 0점에

            int lionScore = 0;
            int apeachScore = 0;

            // 점수 계산
            for (int i = 0; i < 11; i++) {
                if (shoot[i] == 0 && apeachShoot[i] == 0) {
                    continue;
                }
                lionScore += shoot[i] > apeachShoot[i] ? 10 - i : 0;
                apeachScore += shoot[i] <= apeachShoot[i] ? 10 - i : 0;
            }

            if (lionScore - apeachScore >= max) {
                flag = true;

                // max 값이 동률일 때 우선순위 계산
                if (lionScore - apeachScore == max) {
                    for (int i = 10; i >= 0; i--) {
                        if (answer[i] == shoot[i]) {
                            continue;
                        }

                        answer = answer[i] > shoot[i] ? answer : shoot.clone();
                        break;
                    }
                }

                // 그냥 max 값 새로 갱신
                else {
                    max = lionScore - apeachScore;
                    answer = shoot.clone();
                }
            }

            return;
        }

        // 어피치보다 1개 더 맞춰야함
        int mustShoot = apeachShoot[cnt] + 1;

        // 화살 갯수 남았을 때만 과녁 선택하기
        if (arrow + mustShoot <= n) {
            shoot[cnt] = mustShoot;
            dfs(cnt + 1, arrow + mustShoot);
            shoot[cnt] = 0;
        }

        // 해당 점수는 안맞추고 넘어가기
        dfs(cnt + 1, arrow);
    }
}
