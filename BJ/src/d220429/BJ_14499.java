package d220429;

import java.util.*;

import java.io.*;

/*
백준14499 주사위 굴리기

풀이법 :
	 1. 주사위 클래스를 생성하고 roll 메소드를 생성합니다.
	 2. 주어진대로 굴리고 방향대로 roll

시간: 28m
  */

public class BJ_14499 {

	static int n, m, x, y, k, map[][];
	static int[] dr = { 0, 0, 0, -1, 1 };
	static int[] dc = { 0, 1, -1, 0, 0 };

	static class Dice {
		int top;
		int bottom;
		int left;
		int right;
		int front;
		int back;

		void roll(int d) {
			int tmp = 0;

			switch (d) {

			case 1:
				tmp = right;
				right = top;
				top = left;
				left = bottom;
				bottom = tmp;
				break;
			case 2:
				tmp = left;
				left = top;
				top = right;
				right = bottom;
				bottom = tmp;
				break;
			case 3:
				tmp = back;
				back = top;
				top = front;
				front = bottom;
				bottom = tmp;
				break;
			case 4:
				tmp = front;
				front = top;
				top = back;
				back = bottom;
				bottom = tmp;
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		Dice dice = new Dice();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int d = Integer.parseInt(st.nextToken());

			int nx = x + dr[d];
			int ny = y + dc[d];

			if (nx < 0 || ny < 0 || nx == n || ny == m) {
				continue;
			}

			dice.roll(d);
			
			if (map[nx][ny] == 0) {
				map[nx][ny] = dice.bottom;
			} else {
				dice.bottom = map[nx][ny];
				map[nx][ny] = 0;
			}

			x = nx;
			y = ny;
			sb.append(dice.top+"\n");
		}
		
		System.out.println(sb);
	}
}
