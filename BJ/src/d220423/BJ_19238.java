package d220423;

import java.util.*;
import java.io.*;

/*
백준19238 스타트 택시

풀이법 :
	 1. 벽은 -1, 사람은 번호를 매겨 map을 생성합니다.
	 2. 각 사람별로 도착지 좌표와 도착지 까지의 거리를 계산에 저장해둡니다.
	 3. 사람들 중 한 명이라도 거리가 -1(도착지까지 막힘)이면 -1을 출력합니다.
	 4. 

시간: 2h
  */

public class BJ_19238 {

	static class Person {
		int num;
		int r;
		int c;
		int distance;

		public Person(int num, int r, int c, int distance) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
	}

	static int n, m, taxiR, taxiC, fuel, map[][], personInfo[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		personInfo = new int[m + 1][]; // 도착지까지 거리 + 도착지 좌표

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) == 0 ? 0 : -1;
			}
		}

		st = new StringTokenizer(br.readLine());
		taxiR = Integer.parseInt(st.nextToken()) - 1;
		taxiC = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int destR = Integer.parseInt(st.nextToken()) - 1;
			int destC = Integer.parseInt(st.nextToken()) - 1;
			int distance = getDistance(r, c, destR, destC);

			// 도착지까지 막혀있음 
			if (distance < 0) {
				System.out.println(-1);
				return;
			}

			map[r][c] = i;
			personInfo[i] = new int[] { distance, destR, destC };
		}

		// m명의 사람을 다 태울때까지
		while (m > 0) {
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
				if (o1[0] != o2[0]) {
					return Integer.compare(o1[0], o2[0]);
				}

				if (o1[1] != o2[1]) {
					return Integer.compare(o1[1], o2[1]);
				}

				return Integer.compare(o1[2], o2[2]);
			});

			pq.offer(new int[] { 0, taxiR, taxiC });
			boolean[][] visited = new boolean[n][n];
			int person = 0; // 제일 가까운 사람 번호
			int taxiToPerson = 0; // 그 사람까지 거리
			
			// 제일 가까운 사람 찾기
			LOOP: while (!pq.isEmpty()) {
				int size = pq.size();

				while (size-- > 0) {
					int[] cur = pq.poll();
					int r = cur[1];
					int c = cur[2];
					person = map[r][c];

					if (visited[r][c]) {
						continue;
					}

					visited[r][c] = true;
					
					// 사람 찾으면 그 칸 0으로 바꾸고 종료
					if (person > 0) {
						map[r][c] = 0;
						break LOOP;
					}

					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (nr < 0 || nc < 0 || nr == n || nc == n || map[nr][nc] == -1) {
							continue;
						}

						pq.offer(new int[] { taxiToPerson + 1, nr, nc });
					}
				}

				taxiToPerson++;
			}

			// 태울 사람 못찾으면 -1 출력
			if (person <= 0) {
				System.out.println(-1);
				return;
			}

			// 그 사람 목적지로 택시 이동
			int personToDest = personInfo[person][0];
			taxiR = personInfo[person][1];
			taxiC = personInfo[person][2];

			// 연료 모자람
			if (fuel < taxiToPerson + personToDest) {
				System.out.println(-1);
				return;
			}

			fuel = fuel - taxiToPerson + personToDest;
			m--;
		}

		System.out.println(m == 0 ? fuel : -1);
	}

	// getDistance: 두 좌표 사이 최소거리
	static int getDistance(int r, int c, int destR, int destC) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { r, c });
		boolean[][] visited = new boolean[n][n];
		int dist = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int[] cur = q.poll();
				int i = cur[0];
				int j = cur[1];

				if (visited[i][j]) {
					continue;
				}

				visited[i][j] = true;

				if (i == destR && j == destC) {
					return dist;
				}

				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (nr < 0 || nc < 0 || nr == n || nc == n || map[nr][nc] == -1) {
						continue;
					}

					q.offer(new int[] { nr, nc });
				}
			}

			dist++;
		}

		return -1;
	}
}
