package d1110;

import java.util.*;

/*
프로그래머스60061 기둥과 보

풀이법 : 
	 1. 삭제 또는 설치를 한 후 전체 map 유효성 체크를 하여 그대로 진행하거나 되돌려줍니다.
	 2. 완성된 map을 탐색하여 기둥, 보 정보를 리스트에 담고 조건에 맞게 정렬해줍니다.

 */


class Solution {

	boolean[][][] map;
	int n;

	public List<int[]> solution(int no, int[][] build_frame) {
		n = no;
		map = new boolean[2][n + 1][n + 1];

		for (int[] bf : build_frame) {
			int x = bf[0];
			int y = bf[1];
			int type = bf[2];
			int act = bf[3];

			// 삭제
			if (act == 0) {
				map[type][y][x] = false;
				// 삭제 후 유효하지 않으면 삭제 취소
				if (!isValid())
					map[type][y][x] = true;
			}

			// 설치 
			else {
				map[type][y][x] = true;
				// 설치 후 유효하지 않으면 설치 취소
				if (!isValid())
					map[type][y][x] = false;
			}
		}

		List<int[]> answer = new ArrayList<>();

		for (int type = 0; type < 2; type++) {
			for (int y = 0; y <= n; y++)
				for (int x = 0; x <= n; x++) {
					if (map[type][y][x])
						answer.add(new int[] { x, y, type });
				}
		}

		// 조건에 맞게 정렬
		answer.sort((o1, o2) -> {
			if (o1[0] != o2[0])
				return Integer.compare(o1[0], o2[0]);
			if (o1[1] != o2[1])
				return Integer.compare(o1[1], o2[1]);
			return Integer.compare(o1[2], o2[2]);
		});

		return answer;
	}

	boolean isValid() {
		return checkBo() && checkPillar();
	}

	// map에서 기둥들 유효성 검사
	boolean checkPillar() {
		for (int i = 1; i < n; i++)
			for (int j = 0; j < n + 1; j++) {
				if (map[0][i][j]) {
					if (!map[0][i - 1][j] && !map[1][i][j] && (j == 0 || !map[1][i][j - 1]))
						return false;
				}
			}
		return true;
	}

	// map에서 보들 유효성 검사
	boolean checkBo() {
		for (int i = 1; i < n + 1; i++)
			for (int j = 0; j < n + 1; j++) {
				if (map[1][i][j]) {
					if (!map[0][i - 1][j] && !map[0][i - 1][j + 1]
							&& (j == 0 || !map[1][i][j - 1] || !map[1][i][j + 1])) {
						return false;
					}
				}
			}
		return true;
	}
}