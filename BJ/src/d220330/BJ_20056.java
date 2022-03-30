package d220330;

import java.io.*;
import java.util.*;

/*
백준20056 마법사 상어와 파이어볼

풀이법 :
	 1. 파이어볼 하나의 정보인 FireBall과 map의 각 칸별 파이어볼들 정보를 담는 FireBallInfo를 사용합니다.
	 2. Step1 각 칸의 FireBallInfo에 있는 큐에서 FireBall들을 이동시킵니다.
	 3. Step2 각 칸별 FireBallInfo에서 파이어볼이 2개 이상이면 2단계 작업을 수행합니다.
	 4. 각 칸별 FireBallInfo에서 현재 몇개가 있는지 cnt를 갱신합니다.

 */

public class BJ_20056 {

    static int n;
    static FireBallInfo[][] map;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    // FireBall 하나
    static class FireBall {

        int mass;
        int speed;
        int dir;

        FireBall(int mass, int speed, int dir) {
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }
    }

    // map[i][j]의 FireBall들 정보 
    static class FireBallInfo {

        int cnt;
        Queue<FireBall> q;

        FireBallInfo() {
            q = new ArrayDeque<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new FireBallInfo[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new FireBallInfo();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].q.add(new FireBall(mass, s, d));
        }

        // 각 칸별 FireBallInfo에 현재 파이어볼 몇개인지 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j].cnt = map[i][j].q.size();
            }
        }

        for (int i = 0; i < k; i++) {
            move();
            mergeAndSplit();
            setCnt();
        }

        System.out.println(calc());
    }

    // move: 각 칸의 FireBall 큐에서 기존에 있던 파이어볼들만 이동시키기
    static void move() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].cnt == 0) {
                    continue;
                }

                Queue<FireBall> q = map[i][j].q;
                int cnt = map[i][j].cnt; // 원래 칸에 있던 파이어볼 개수

                for (int k = 0; k < cnt; k++) {
                    FireBall fb = q.poll();
                    int nr = (i + dr[fb.dir] * fb.speed) % n;
                    int nc = (j + dc[fb.dir] * fb.speed) % n;
                    if (nr < 0) {
                        nr += n;
                    }
                    if (nc < 0) {
                        nc += n;
                    }

                    map[nr][nc].q.add(fb); // 새로운 위치로 파이어볼 이동
                }
            }
        }
    }

    // mergeAndSplit: 2개 이상이면 합치고 쪼개기
    static void mergeAndSplit() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j].cnt = map[i][j].q.size();

                if (map[i][j].cnt < 2) {
                    continue;
                }

                Queue<FireBall> q = map[i][j].q;
                int cnt = q.size();
                int newM = 0;
                int newS = 0;
                int newD = q.peek().dir % 2; // 첫 번째 파이어볼 방향
                boolean sameD = true;

                while (!q.isEmpty()) {
                    FireBall fb = q.poll();
                    newM += fb.mass;
                    newS += fb.speed;

                    // 방향 하나라도 다르면 false
                    if (fb.dir % 2 != newD) {
                        sameD = false;
                    }
                }

                newM /= 5;
                newS /= cnt;
                newD = sameD ? 0 : 1;

                // 파이어볼 소멸
                if (newM == 0) {
                    continue;
                }

                // 4개로 쪼개진 파이어볼 추가
                for (int k = 0; k < 4; k++) {
                    map[i][j].q.add(new FireBall(newM, newS, newD));
                    newD += 2;
                }
            }
        }
    }

    // map의 각 칸의 FireBallInfo에 FireBall 몇개인지 cnt 갱신
    static void setCnt() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j].cnt = map[i][j].q.size();
            }
        }
    }

    static int calc() {
        int mass = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Queue<FireBall> q = map[i][j].q;

                while (!q.isEmpty()) {
                    FireBall fb = q.poll();
                    mass += fb.mass;
                }
            }
        }

        return mass;
    }
}
