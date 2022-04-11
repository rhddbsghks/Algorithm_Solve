package d220410;

import java.util.*;
import java.io.*;

/*
백준16235 나무 제태크

풀이법 :
	 시뮬레이션

 */

public class BG_16235 {

    static int nutrient[][], n, m, k;
    static Land[][] map;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1,};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Land {

        int nutrient;
        PriorityQueue<Integer> trees;
        Queue<Integer> dead;

        Land(int nutrient) {
            this.nutrient = nutrient;
            trees = new PriorityQueue<>();
            dead = new ArrayDeque<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nutrient = new int[n][n];
        map = new Land[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new Land(5);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            map[r][c].trees.offer(age);
        }

        for (int i = 0; i < k; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(answer());
    }

    static void spring() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].trees.size() == 0) {
                    continue;
                }

                Land land = map[i][j];
                int size = land.trees.size();
                Queue<Integer> grow = new ArrayDeque<>();

                while (size-- > 0) {
                    int tree = land.trees.poll();

                    if (tree > land.nutrient) {
                        land.dead.offer(tree);
                        continue;
                    }

                    land.nutrient -= tree;
                    grow.offer(tree + 1);
                }

                while (!grow.isEmpty()) {
                    land.trees.offer(grow.poll());
                }
            }
        }
    }

    static void summer() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].dead.size() == 0) {
                    continue;
                }

                Queue<Integer> dead = map[i][j].dead;

                while (!dead.isEmpty()) {
                    int deadTree = dead.poll();
                    map[i][j].nutrient += deadTree / 2;
                }
            }
        }
    }

    static void fall() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].trees.size() == 0) {
                    continue;
                }

                Land land = map[i][j];

                for (int tree : land.trees) {
                    if (tree % 5 != 0) {
                        continue;
                    }

                    for (int d = 0; d < 8; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (nr < 0 || nc < 0 || nr == n || nc == n) {
                            continue;
                        }

                        map[nr][nc].trees.offer(1);
                    }
                }
            }
        }
    }

    static void winter() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j].nutrient += nutrient[i][j];
            }
        }
    }

    static int answer() {
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt += map[i][j].trees.size();
            }
        }

        return cnt;
    }
}
