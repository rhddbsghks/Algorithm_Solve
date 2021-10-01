package d1001;

import java.util.*;
import java.io.*;

public class SWEA_4013 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./src/d1001/input4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			int k = Integer.parseInt(br.readLine());
			int[][] sawTooth = new int[5][8]; // 톱니정보
			int[] point = new int[5]; // 톱니별 빨간화살표 위치
			int result = 0;

			for (int i = 1; i < 5; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++)
					sawTooth[i][j] = Integer.parseInt(st.nextToken());
			}

			// k번 회전
			for (int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()); // 돌릴 톱니 번호
				int d = Integer.parseInt(st.nextToken()); // 돌릴 톱니 방향
				int[] rotate = new int[5]; // 각 톱니들 회전 방향

				rotate[num] = d;

				int rightD, leftD, rightN, leftN;
				rightD = leftD = d;
				rightN = leftN = num;

				// 오른쪽에 맞물릴 톱니들 탐색
				for (int s = num; s < 4; s++) {
					int curP = (point[s] + 2) % 8;
					int rightP = (point[s + 1] + 6) % 8;
					rightD *= -1;
					rightN++;
					if (sawTooth[s][curP] != sawTooth[s + 1][rightP]) {
						rotate[rightN] = rightD;
						continue;
					}
					break;
				}
				// 왼쪽에 맞물릴 톱니들 탐색
				for (int s = num; s > 1; s--) {
					int curP = (point[s] + 6) % 8;
					int leftP = (point[s - 1] + 2) % 8;
					leftD *= -1;
					leftN--;
					if (sawTooth[s][curP] != sawTooth[s - 1][leftP]) {
						rotate[leftN] = leftD;
						continue;
					}
					break;
				}

				// rotate[]의 방향 참고해서 point 바꾸기
				for (int n = 1; n < 5; n++) {
					if (rotate[n] == 0)
						continue;

					if (rotate[n] == 1)
						point[n] = (point[n] + 7) % 8;
					else
						point[n] = (point[n] + 1) % 8;
				}
			}

			for (int n = 1; n < 5; n++)
				if (sawTooth[n][point[n]] == 1)
					result += (int) Math.pow(2, n - 1);

			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
}