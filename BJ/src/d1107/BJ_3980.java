package d1107;

import java.io.*;
import java.util.*;

/*
백준 3980 선발 명단

풀이법 : 
	 1. 비트마스킹으로 해당 포지션을 선택했는지 관리합니다. 
	 2. dfs를 사용해 11명의 선수를 선택하면 최댓값을 갱신해줍니다.

 */

public class BJ_3980 {
	static int result, player[][];
	static final int PLAYER_NUM = 11;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 0; t < testcase; t++) {
			player = new int[PLAYER_NUM][PLAYER_NUM];
			result = 0;

			for (int i = 0; i < PLAYER_NUM; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < PLAYER_NUM; j++)
					player[i][j] = Integer.parseInt(st.nextToken());
			}
			dfs(0,0,0);
			System.out.println(result);
		}
	}

	static void dfs(int cnt, int sum, int bit) {
		if (cnt == PLAYER_NUM) {
			result = Integer.max(result, sum);
			return;
		}

		for (int i = 0; i < PLAYER_NUM; i++) {
			// 해당 선수가 i포지션 능력을 가지고 있고 아직 비트마스킹에서 선택 안한 포지션이면 dfs
			if (player[cnt][i] != 0 && (bit & 1 << i) == 0) {
				dfs(cnt + 1, sum + player[cnt][i], (bit | 1 << i));
			}
		}
	}
}