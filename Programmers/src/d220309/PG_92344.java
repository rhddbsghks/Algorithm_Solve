package d220309;

/*
프로그래머스92344 파괴되지 않은 건물

풀이법 :
        1. skill 값들을 누적합으로 기록해둡니다.
        2. 누적합 기록으로 전체 건물 변화량을 계산합니다.
        3. board와 변화량으로 파괴되지 않은 건물을 카운트합니다.

 */


public class PG_92344 {

    public static int solution(int[][] board, int[][] skills) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] sum = new int[n][m]; // skill 누적합
        int[][] skillAmount; // 각 칸별 변화량

        for (int[] skill : skills) {
            int type = skill[0];
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            int degree = type == 1 ? -skill[5] : skill[5]; // 파괴이면 - 해주기

            sum[r2][c2] += degree; // (0, 0) ~ (r2, c2) 누적합

            // 왼쪽 누적합 빼주기
            if (c1 > 0) {
                sum[r2][c1 - 1] -= degree;
            }

            //위쪽 누적합 빼주기
            if (r1 > 0) {
                sum[r1 - 1][c2] -= degree;
            }

            // 중복으로 빠진 좌상단 누적합 다시 더해주기
            if (c1 > 0 && r1 > 0) {
                sum[r1 - 1][c1 - 1] += degree;
            }
        }

        skillAmount = calcAmount(sum);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + skillAmount[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }

    static int[][] calcAmount(int[][] sum) {

        int n = sum.length;
        int m = sum[0].length;

        int[][] amount = new int[n][m];

        // 행별로 우 -> 좌 누적합 계산
        for (int i = n - 1; i >= 0; i--) {
            int tmp = 0;
            for (int j = m - 1; j >= 0; j--) {
                tmp += sum[i][j];
                amount[i][j] += tmp;
            }
        }

        // 열별로 하 -> 상 누적합 계산
        for (int j = m - 1; j >= 0; j--) {
            int tmp = 0;

            for (int i = n - 1; i >= 0; i--) {
                amount[i][j] += tmp;
                tmp = amount[i][j];
            }
        }

        return amount;
    }
}
