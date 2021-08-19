package d0819;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
SWEA 1247 최적경로

풀이법 : 고객 순열을 구해 이동 거리를 계산한다. 이동 도중에 기존 최소 거리보다 커지면 재귀를 종료한다.
		 
시간복잡도: 최대 O(N!)
 */


public class SEWEA_1247 {

	static int min; // 최소 거리
	static int n;
	static boolean[] isSelected;
	static ArrayList<int[]> customerPos; // 고객 좌표 List
	static int[] homePos; // 집 좌표

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/d0819/input1247.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		
		// testcase Start
		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] companyPos = new int[2];
			homePos = new int[2];
			customerPos = new ArrayList<int[]>();

			companyPos[0] = Integer.parseInt(st.nextToken());
			companyPos[1] = Integer.parseInt(st.nextToken());
			homePos[0] = Integer.parseInt(st.nextToken());
			homePos[1] = Integer.parseInt(st.nextToken());

			min = Integer.MAX_VALUE;
			isSelected = new boolean[n];

			// 고객좌표 리스팅
			for (int i = 0; i < n; i++)
				customerPos.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });

			// 현재 거리합 = 0, 회사좌표부터 출발
			minDist(0, 0, companyPos);

			System.out.println("#" + t + " " + min);
		}
		// testcase End
	}

	static void minDist(int cnt, int nowDist, int[] startPos) {
		
		// 지금까지 거리가 최솟값보다 크면 가지치기
		if (nowDist > min)
			return;
		
		// 마지막 고객까지 방문완료
		if (cnt == n) {
			// 마지막 고객 -> 집 거리 더하기
			nowDist += Math.abs(startPos[0] - homePos[0]) + Math.abs(startPos[1] - homePos[1]);

			// 최솟값 갱신
			if (nowDist < min)
				min = nowDist;
			return;
		}

		// 고객 좌표 순열
		for (int i = 0; i < n; i++) {
			if (!isSelected[i]) {
				int[] cPos = customerPos.get(i);
				isSelected[i] = true;
				minDist(cnt + 1, nowDist + Math.abs(startPos[0] - cPos[0]) + Math.abs(startPos[1] - cPos[1]), cPos);
				isSelected[i] = false;
			}
		}
	}
}