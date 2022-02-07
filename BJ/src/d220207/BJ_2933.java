package d220207;

import java.io.*;
import java.util.*;

/*
백준2933 미네랄

풀이법 :
	 1. 막대기 위치의 미네랄을 제거합니다.
	 2. 제거한 위치의 사방을 탐색하여 각각 dfs를 태워보고 바닥과 닿지 않은 덩어리이면 떨어뜨려야합니다.
	 3. 해당 덩어리 좌표들을 왼쪽, 아래 순으로 정렬하고 각 바닥 좌표들이 떨어지는 거리의 최솟값을 구합니다.
	 4. 클러스터를 최솟값만큼 내려줍니다.

 */


public class BJ_2933 {

    static int r, c, n;
    static char[][] map;
    static List<int[]> pos;
    static int dr[] = {1, -1, 0, 0,};
    static int dc[] = {0, 0, -1, 1};
    static boolean visited[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int targetR = r - Integer.parseInt(st.nextToken());
            int targetC = -1;
            
            // 왼쪽에서 막대기
            if (i % 2 == 0) {
                for (int j = 0; j < c; j++) {
                    if (map[targetR][j] == 'x') {
                        targetC = j;
                        break;
                    }
                }
            }

            // 오른쪽에서 막대기
            else {
                for (int j = c - 1; j >= 0; j--) {
                    if (map[targetR][j] == 'x') {
                        targetC = j;
                        break;
                    }
                }
            }
                
            // 맞은 미네랄 없으면 넘어가기
            if (targetC == -1) 
                continue;

            // 해당 좌표 미네랄 부수고 클러스터 체크
            map[targetR][targetC] = '.';
            checkCluster(targetR, targetC);
        }

        printMap();
    }

    // checkCluster: 해당 좌표에서 사방으로 각각 클러스터 체크
    static void checkCluster(int targetR, int targetC) {

        for (int d = 0; d < 4; d++) {
            int nr = targetR + dr[d];
            int nc = targetC + dc[d];

            if (oob(nr, nc) || map[nr][nc] == '.') {
                continue;
            }

            pos = new ArrayList<>(); // 클러스터 이루는 좌표
            visited = new boolean[r][c];

            // 해당 방향 클러스터가 바닥에 닿지 않으면 떨어질 depth 계산후 떨어뜨리기
            if (clustering(nr, nc)) {
                int depth = checkFallDepth();
                fallMineral(depth);
                break;
            }
        }
    }

    // clustering: 좌표가 하나라도 바닥에 닿으면 false를 반환하는 DFS
    static boolean clustering(int i, int j) {
        
        if (i == r - 1) {
            return false;
        }

        visited[i][j] = true;
        pos.add(new int[]{i, j});

        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (oob(nr, nc)) {
                continue;
            }

            if (!visited[nr][nc] && map[nr][nc] == 'x' && !clustering(nr, nc)) {
                return false;
            }
        }
        return true;
    }

    // checkFallDepth: 클러스터를 이루는 pos에서 바닥 좌표들 중 제일 짧게 떨어지는 값 반환
    static int checkFallDepth() {

        int depth = Integer.MAX_VALUE;
        Set<Integer> checkedCol = new HashSet<>();

        // 클러스터 좌표들을 왼쪽아래 순으로 정렬
        pos.sort((o1,o2)->{
            if (o1[1] != o2[1]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o2[0],o1[0]);
        });

        for (int[] bottomPos : pos) {
            int i = bottomPos[0];
            int j = bottomPos[1];
            int tmp = 0;

            // 한 column당 바닥은 하나임
            if(checkedCol.contains(j))
                continue;

            checkedCol.add(j);

            while (i + 1 < r) {
                if (map[++i][j] == 'x') {
                    break;
                }
                tmp++;
            }

            // 바닥까지의 거리들 중 제일 짧은 값
            depth = Math.min(depth, tmp);
        }

        return depth;
    }

    // fallMineral: 클러스터 좌표들 .으로 바꾸고 depth만큼 다시 내리기
    static void fallMineral(int depth) {
        clearMineral();

        for (int[] cur : pos) {
            int i = cur[0] + depth;
            int j = cur[1];

            map[i][j] = 'x';
        }
    }

    static void clearMineral() {

        for (int[] cur : pos) {
            int i = cur[0];
            int j = cur[1];

            map[i][j] = '.';
        }
    }

    static boolean oob(int i, int j) {
        return i < 0 || j < 0 || i == r || j == c;
    }

    static void printMap() {

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
