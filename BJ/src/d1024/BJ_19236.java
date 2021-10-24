package d1024;

import java.util.*;
import java.io.*;

/*
백준 19236 청소년 상어

풀이법 : 
	 1. 상어 번호와 방향은 3차원배열 int[4][4][2]로 관리하고 각 상어들의 좌표는 Map을 사용해 관리합니다. 
	 2. 상어가 갈 수 있는 모든 물고기들로 보내보고 이때 map과 상어좌표Map을 복사해서 넘겨줍니다.
	 3. 상어가 한번도 가지 못했다면 result를 갱신해줍니다.

시뮬은 디버깅이 너무 힘들어요...
 */


public class BJ_19236 {
	static int[] dr = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static final int NUM = 0;
	static final int DIR = 1;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][][] map = new int[4][4][2];
		Map<Integer, int[]> fish = new HashMap<>();

		result = 0;

		// 입력값 처리
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j][NUM] = Integer.parseInt(st.nextToken());
				map[i][j][DIR] = Integer.parseInt(st.nextToken());
				fish.put(map[i][j][NUM], new int[] { i, j });
			}
		}

		dfs(0, 0, 0, map, fish);
		System.out.println(result);
	}

	static void dfs(int r, int c, int sum, int[][][] map, Map<Integer, int[]> fish) {
		boolean flag = false; // 상어 갈 수 있는지 여부

		// (r, c) 물고기를 먹고 해당 물고기 좌표를 null로 바꿔준다.
		sum += map[r][c][NUM];
		fish.put(map[r][c][NUM], null);
		map[r][c][NUM] = -1;
		int d = map[r][c][DIR];
		
		moveFish(map, fish);

		// 상어는 현재 방향으로 최대 3배 갈 수 있다.
		for (int i = 1; i <= 3; i++) {
			int nr = r + dr[d] * i;
			int nc = c + dc[d] * i;

			if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4)
				break;

			if (map[nr][nc][NUM] > 0) {
				flag = true;
				int[][][] copyMap = copyMap(map);
				copyMap[r][c][NUM] = 0; // copy한 map에서 현재 위치 0으로 바꿔주기
				dfs(nr, nc, sum, copyMap, copyFish(fish));
			}
		}

		// 한번도 물고기를 못먹었으면 result 갱신
		if (!flag) 
			result = Integer.max(result, sum);
	}


	static void moveFish(int[][][] map, Map<Integer, int[]> fish) {

		for (int i = 1; i <= 16; i++) {
			if (fish.get(i) == null)
				continue;

			int r = fish.get(i)[0];
			int c = fish.get(i)[1];
			int d = map[r][c][DIR];

			while (true) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				// 좌45도 방향전환
				if (nr < 0 || nc < 0 || nr == 4 || nc == 4 || map[nr][nc][NUM] == -1) {
					d += 1;
					if (d == 9)
						d = 1;
					continue;
				}

				map[r][c][DIR] = d;
				
				// 가려는 곳이 물고기면 좌표 swap
				if(map[nr][nc][NUM]>0)
					swapFishPos(map[r][c][NUM], map[nr][nc][NUM],fish);
				
				// 빈곳이면 해당물고기 좌표 바꿔주기
				else 
					fish.put(map[r][c][NUM],new int[] {nr,nc});
				
				// map에서 swap
				swap(map[r][c], map[nr][nc]);
				break;
			}
		}
	}
	
	// 물고기좌표 Map에서 두 물고기 좌표 swap
	static void swapFishPos(int f1, int f2, Map<Integer, int[]> fish) {
		int[] fish1 = fish.get(f1);
		int[] fish2 = fish.get(f2);
		fish.put(f2, fish1);
		fish.put(f1, fish2);
	}

	// 두 좌표의 번호,방향 swap
	static void swap(int[] a, int[] b) {
		int[] tmp = new int[2];
		for (int i = 0; i < 2; i++) {
			tmp[i] = a[i];
			a[i] = b[i];
			b[i] = tmp[i];
		}
	}

	// 3차원 map 복사
	static int[][][] copyMap(int[][][] arr) {
		int tmp[][][] = new int[4][4][2];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				for (int k = 0; k < 2; k++)
					tmp[i][j][k] = arr[i][j][k];
		return tmp;
	}

	// 상어좌표 Map 복사
	static Map<Integer, int[]> copyFish(Map<Integer, int[]> fish) {
		Map<Integer, int[]> tmp = new HashMap<>();

		for (int key = 1; key <= 16; key++) {
			if (fish.get(key) != null)
				tmp.put(key, fish.get(key).clone());
			else
				tmp.put(key, null);
		}
		return tmp;
	}
}