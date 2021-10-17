package d1017;

import java.io.*;
import java.util.*;


/*
백준 19237 어른 상어

풀이법 : 
	 전체 상어를 Map으로 관리하였고, 2차원배열 int[][]로 상어 영역표시와 3차원배열 int[][][]로 냄새를 관리하였습니다.

 */


public class BJ_19237 {

	static class Shark {
		int r;
		int c;
		int dir;
		int[][] direction;

		Shark(int r, int c) {
			this.r = r;
			this.c = c;
			direction = new int[5][4];
		}
	}

	static int n, m, k, cnt, time, smell[][][];
	static int[][] map;
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };
	static Map<Integer, Shark> sharkMap;

	
	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		smell = new int[n][n][];
		sharkMap = new HashMap<>();
		cnt = 0;

		// 입력 및 상어 카운트
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					sharkMap.put(map[i][j], new Shark(i, j)); // 상어 정보 저장
					cnt++;
				}
			}
		}

		// 상어 초기 위치 설정
		st = new StringTokenizer(br.readLine());
		for (int num = 1; num <= m; num++)
			sharkMap.get(num).dir = Integer.parseInt(st.nextToken());

		// 상어 방향 설정
		for (int num = 1; num <= m; num++)
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++)
					sharkMap.get(num).direction[i][j] = Integer.parseInt(st.nextToken());
			}

		// 남은 상어가 1마리일때까지 이동
		while (cnt > 1) {
			// 1000초되면 종료
			if (time == 1000) {
				System.out.println(-1);
				return;
			}

			spread();
			move();
			disappear();
			time++;
		}
		System.out.println(time);
	}
	/****************** Main End******************/
	
	
	/****************** Method ******************/
	
	// spread: 상어Map에 있는 상어들을 뽑아가며 각 위치에 새로운 smell 생성
	static void spread() {
		for (int num : sharkMap.keySet()) {
			Shark shark = sharkMap.get(num);
			smell[shark.r][shark.c] = new int[] { num, k };
		}
	}

	// move: 각각 방향 순서대로 이동
	private static void move() {
		ArrayList<Integer> removeList = new ArrayList<>(); // 중복되는 상어들 중 추방자 인덱스

		for (int num : sharkMap.keySet()) {
			Shark shark = sharkMap.get(num);
			int r = shark.r;
			int c = shark.c;
			int dir = shark.dir;
			int[] direction = shark.direction[dir];
			boolean canGo = false;
			map[r][c] = 0; // 현재위치에서 제거

			// 우선순위 방향대로 이동
			for (int d : direction) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nc < 0 || nr == n || nc == n || smell[nr][nc] != null)
					continue;

				canGo = true;

				// 이미 다른 상어가 와있고 나보다 작은 숫자면 
				if (map[nr][nc] != 0 && map[nr][nc] < num) {
					removeList.add(num); // 내 번호를 추방자리스트에 저장
					break;
				}

				// 아니면 내가 자리 차지
				removeList.add(map[nr][nc]);
				map[nr][nc] = num;
				shark.r = nr;
				shark.c = nc;
				shark.dir = d;
				break;
			}

			// 4군데 모두 냄새면 자기 냄새 있는 곳으로
			if (!canGo) {
				for (int d : direction) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nc < 0 || nr == n || nc == n)
						continue;

					if (smell[nr][nc][0] == num) {
						map[r][c] = 0;
						map[nr][nc] = num;
						shark.r = nr;
						shark.c = nc;
						shark.dir = d;
						break;
					}
				}
			}
		}

		// 추방자인덱스들 Map에서 제거
		for (int num : removeList) {
			if (sharkMap.remove(num) != null)
				cnt--;
		}
	}

	// disappear: smell 배열을 탐색하여 각 냄새들 시간 1씩 감소, 0되면 null로 바꿔주기
	private static void disappear() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (smell[i][j] != null && --smell[i][j][1] == 0)
					smell[i][j] = null;
	}
	/****************** Method End******************/
}