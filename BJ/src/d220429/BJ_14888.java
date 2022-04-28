package d220429;

import java.io.*;
import java.util.*;

/*
백준14888 연산자 끼워넣기

풀이법 :
	 1. 완전탐색

시간: 17m
  */

public class BJ_14888 {

	static int n, min, max, num[], op[];
	static boolean used[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = new int[n];
		op = new int[4];
		used = new boolean[4];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, num[0]);
		System.out.println(max);
		System.out.println(min);

	}

	static void dfs(int cnt, int score) {
		if (cnt == n-1) {
			min = Integer.min(score, min);
			max = Integer.max(score, max);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (op[i] == 0) {
				continue;
			}
			
			op[i]--;
			dfs(cnt + 1, operate(score, num[cnt + 1], i));
			op[i]++;
		}
	}

	static int operate(int a, int b, int type) {

		switch (type) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		case 3:
			return a / b;
		}

		return -1;

	}
}
