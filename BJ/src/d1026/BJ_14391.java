package d1026;

import java.util.*;
import java.io.*;

/*
백준 14391 종이 조각

풀이법 : 
	 1. 최대 4x4이기 때문에 완전탐색으로 해결하였습니다. 
	 2. dfs로 각 칸을 0 또는 1로 마킹하고 0끼리 세로로 묶고 1끼리 가로로 묶어 계산합니다.

입력값이 작으면 항상 완전탐색부터 생각해봐야겠습니다. 재밌는 문제였어요.
 */


public class BJ_14391 {
	static int n, m,result, input[][], board[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		input = new int[n][m];
		board = new int[n][m];
		result = 0;
		
		for (int i = 0; i < n; i++) {
			String[] in = br.readLine().split("");
			for (int j = 0; j < m; j++)
				input[i][j] = Integer.parseInt(in[j]);
		}

		dfs(0);
		System.out.println(result);
	}

	// NxM 보드에 0 또는 1로 세팅
	static void dfs(int cnt) {
		if(cnt==n*m) {
			// 완성되면 가로+세로 해서 최댓값 갱신 
			result = Integer.max(result,garo()+ sero());
			return;
		}
		
		int r = cnt / m;
		int c = cnt % m;
		
		board[r][c]=0;
		dfs(cnt+1);
		board[r][c]=1;
		dfs(cnt+1);
	}

	// 세로합
	private static int sero() {
		int sum = 0;
		String num="0";
		
		for(int j=0;j<m;j++) {
			num="0";
			for(int i=0;i<n;i++) {
				if(board[i][j]==1) {
					sum+=Integer.parseInt(num);
					num = "0";
				}
				else
					num+=input[i][j];
			}
			sum+=Integer.parseInt(num);
		}
		return sum;
	}

	// 가로합
	private static int garo() {
		int sum = 0;
		String num="0";
		
		for(int i=0;i<n;i++) {
			num="0";
			for(int j=0;j<m;j++) {
				if(board[i][j]==0) {
					sum+=Integer.parseInt(num);
					num = "0";
				}
				else
					num+=input[i][j];
			}
			sum+=Integer.parseInt(num);
		}
		return sum;
	}
}