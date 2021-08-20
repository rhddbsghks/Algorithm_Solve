package d0820;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
백준 15683 감시

풀이법 : CCTV List에 cctv들을 저장하고 하나씩 뽑아가며 감시 영역을 체크한다. 각 cctv 별 경우의 수만큼 재귀 호출

 */

public class BJ_15683 {

	static class CCTV {
		int x;
		int y;
		char num; // cctv 번호

		public CCTV(int x, int y, char num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	static int n, m, cctvNum;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dc = { 0, 1, 0, -1 };
	static int min;
	static ArrayList<CCTV> cctvList;

	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		min = Integer.MAX_VALUE;
		cctvList = new ArrayList<CCTV>();

		// map 초기화 및 cctvList 생성
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				map[i][j] = input[j].charAt(0);
				if (isCCTV(input[j].charAt(0)))
					cctvList.add(new CCTV(i, j, map[i][j]));
			}
		}

		cctvNum = cctvList.size();
		watch(0, map); // cctv 0번부터 감시 시작

		System.out.println(min);
	}

	/****************** Main End ******************/

	/****************** Method ******************/

	// watch: cctv 리스트에서 하나씩 뽑아가며 감시지대 세팅
	static void watch(int cnt, char[][] nowMap) {

		// 모든 cctv 다 세팅했으면 사각지대 갱신
		if (cnt == cctvNum) {
			checkBlind(nowMap);
			return;
		}

		// cnt 번째 cctv
		CCTV cctv = cctvList.get(cnt);

		// cctv 번호에 맞게 세팅
		switch (cctv.num) {

		case '1': // 4방 중 하나로 세팅
			for (int d = 0; d < 4; d++) {
				char[][] newMap = cloneArray(nowMap);
				setCCTV(cctv.x + dr[d], cctv.y + dc[d], d, newMap);
				watch(cnt + 1, newMap); // 다음 cctv 세팅
			}
			break;

		case '2': // 위아래 or 좌우 세팅
			for (int d = 0; d < 2; d++) {
				char[][] newMap = cloneArray(nowMap);
				setCCTV(cctv.x + dr[d], cctv.y + dc[d], d, newMap);
				setCCTV(cctv.x + dr[d + 2], cctv.y + dc[d + 2], d + 2, newMap);
				watch(cnt + 1, newMap);
			}
			break;

		case '3': // 4방 중 하나+오른쪽
			for (int d = 0; d < 4; d++) {
				char[][] newMap = cloneArray(nowMap);
				setCCTV(cctv.x + dr[d], cctv.y + dc[d], d, newMap);
				setCCTV(cctv.x + dr[(d + 1) % 4], cctv.y + dc[(d + 1) % 4], (d + 1) % 4, newMap);
				watch(cnt + 1, newMap);
			}
			break;

		case '4': // 4방 중 하나+오른쪽+오른쪽
			for (int d = 0; d < 4; d++) {
				char[][] newMap = cloneArray(nowMap);
				setCCTV(cctv.x + dr[d], cctv.y + dc[d], d, newMap);
				setCCTV(cctv.x + dr[(d + 1) % 4], cctv.y + dc[(d + 1) % 4], (d + 1) % 4, newMap);
				setCCTV(cctv.x + dr[(d + 2) % 4], cctv.y + dc[(d + 2) % 4], (d + 2) % 4, newMap);
				watch(cnt + 1, newMap);
			}
			break;

		case '5': // 4방으로 세팅
			char[][] newMap = cloneArray(nowMap);
			for (int d = 0; d < 4; d++)
				setCCTV(cctv.x + dr[d], cctv.y + dc[d], d, newMap);
			watch(cnt + 1, newMap);
			break;
		}
	}

	// setCCTV: 해당점부터 d 방향으로 cctv 감시지대 세팅
	static void setCCTV(int i, int j, int d, char[][] m) {
		while (true) {
			if (isValid(i, j) && m[i][j] != '6') {
				if (!isCCTV(m[i][j]))
					m[i][j] = '#';
				i += dr[d];
				j += dc[d];
				continue;
			}
			break;
		}
	}

	// checkBlind: 현재 map에서 사각지대 카운트 및 최솟값 갱신
	static void checkBlind(char[][] arr) {
		int blind = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (arr[i][j] == '0')
					blind++;

		min = Math.min(min, blind);
	}

	// cloneArray: 2차원 배열 복사
	static char[][] cloneArray(char[][] arr) {
		char[][] clone = new char[n][m];

		for (int i = 0; i < n; i++)
			clone[i] = arr[i].clone();

		return clone;
	}

	// isCCTV: 해당 문자가 cctv인지 체크
	static boolean isCCTV(char c) {
		if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5')
			return true;
		return false;
	}

	// isValid: cctv 범위 유효한지 확인
	static boolean isValid(int i, int j) {
		if (0 <= i && i < n && 0 <= j && j < m && map[i][j] != 6)
			return true;
		return false;
	}
	/****************** Method End ******************/
}