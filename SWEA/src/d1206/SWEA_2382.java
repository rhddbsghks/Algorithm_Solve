package d1206;

import java.util.*;
import java.io.*;

/*
SWEA2382 미생물 격리

풀이법 : 
	 1. 좌표, 미생물수, 방향을 저장하는 큐로 미생물 군집들을 관리합니다.
	 2. 임시맵에서 이동시켜보고 군집이 작은 미생물부터 이동시켜서 방향은 계속 덮어씌우는 식으로 진행해줍니다.
	 3. 시간이 다 지나면 미생물수가 0보다 큰 것들만 카운트해줍니다.

 */

public class SWEA_2382 {

	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };
	static int n, m, k;
	static int tmp[][][]; // 미생물 이동시켜볼 임시 맵
	static PriorityQueue<int[]> pos; //{좌표, 미생물수, 방향} 미생물 수로 오름차순 정렬

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int t = 1; t <= testcase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			pos = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[2], o2[2]));

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				pos.offer(new int[] { r, c, num, d }); // 미생물 정보 큐에 삽입
			}

			simulate(m);
			System.out.println("#" + t + " " + count());
		}
	}

	static void simulate(int time) {
		while (time-- > 0) {
			tmp = new int[n][n][2]; // 현재 미생물들 임시로 움직여 볼 map

			// pos에 있는 미생물들 하나씩 tmp에 임시 이동시키기
			while (!pos.isEmpty()) {
				int[] p = pos.poll();
				int r = p[0];
				int c = p[1];
				int num = p[2];
				int d = p[3];

				int nr = r + dr[d];
				int nc = c + dc[d];

				// 경계선 처리
				if (isEdge(nr, nc)) {
					if (d % 2 == 1)
						d += 1;
					else
						d -= 1;

					num /= 2;
					if (num == 0)
						continue;
				}

				tmp[nr][nc][0] += num;
				tmp[nr][nc][1] = d; // 군집수로 오름차순 정렬했으므로 방향은 덮어쓰면됨
			}
			addPos(); // 군집수 0 아닌애들은 다시 pos에 추가
		}
	}

	// 남아있는 미생물 정보에서 군집 카운트
	static int count() {
		int cnt = 0;

		while (!pos.isEmpty())
			cnt += pos.poll()[2];

		return cnt;
	}

	// 임시 맵 tmp에서 군집이 0 이상인 애들만 pos에 추가
	static void addPos() {
		for (int r = 0; r < n; r++)
			for (int c = 0; c < n; c++)
				if (tmp[r][c][0] != 0)
					pos.offer(new int[] { r, c, tmp[r][c][0], tmp[r][c][1] });
	}

	static boolean isEdge(int r, int c) {
		return r == 0 || c == 0 || r == n - 1 || c == n - 1;
	}
}
