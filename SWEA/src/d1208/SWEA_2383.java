package d1208;

/*
SWEA 2383 점심 식사시간

풀이법 : 
	 1. 전체 인원을 두 그룹으로 나누어 2개의 계단으로 분배합니다.
	 2. 두 그룹 중 더 오래 걸린시간이 해당 경우 이동 시간이고 최솟값을 계속 갱신해줍니다.

계단이 2개로 고정이고, 인원도 적어 완탐으로 접근했습니다.
 */


import java.util.*;
import java.io.*;

public class SWEA_2383 {

	static int n, map[][], answer;
	static List<int[]> people, stairs;
	static boolean subset[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			people = new ArrayList<>(); // 사람들 좌표
			stairs = new ArrayList<>(); // 계단 좌표와 길이
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						people.add(new int[] { i, j });
					else if (map[i][j] > 0)
						stairs.add(new int[] { i, j, map[i][j] });
				}
			}
			subset = new boolean[people.size()];
			dfs(0);
			
			System.out.println("#"+t+" "+answer);
		}
	}

	// dfs: 사람 좌표들 두 그룹으로 나누고 각 계단 태워보기
	static void dfs(int cnt) {
		if (cnt == people.size()) {
			PriorityQueue<Integer> groupA = new PriorityQueue<Integer>(); // 계단1까지의 거리들 삽입  가까운순으로 정렬
			PriorityQueue<Integer> groupB = new PriorityQueue<Integer>(); // 계단2까지의 거리들 삽입
			int[] stairA = stairs.get(0);
			int[] stairB = stairs.get(1);

			for (int i = 0; i < subset.length; i++) {
				int[] pos = people.get(i);
				int dist = 0;

				// 두 그룹으로 나누고 각 계단까지의 거리들 큐에 삽입 & 도착 후 1초 기다리므로 1 더해주기
				if (subset[i]) {
					dist = Math.abs(stairA[0] - pos[0]) + Math.abs(stairA[1] - pos[1]) + 1;
					groupA.offer(dist);
				} else {
					dist = Math.abs(stairB[0] - pos[0]) + Math.abs(stairB[1] - pos[1]) + 1;
					groupB.offer(dist);
				}
			}

			// 그룹A시간, B시간 중 최댓값과 기존 답을 비교해 갱신
			answer = Math.min(answer,
					Math.max(calcTime(groupA, map[stairA[0]][stairA[1]]), calcTime(groupB, map[stairB[0]][stairB[1]])));

			return;
		}

		subset[cnt] = true;
		dfs(cnt + 1);
		subset[cnt] = false;
		dfs(cnt + 1);
	}

	// calcTime: 계단까지의 거리들 담은 큐와 계단길이로 총 소요 시간 계산 
	static int calcTime(PriorityQueue<Integer> group, int len) {
		Queue<Integer> going = new ArrayDeque<Integer>(); // 현재 계단 내려가고있는 사람 큐
		int time = 0;
		int empty = 0;
		
		// group과 going이 모두 빌 때까지
		while (!group.isEmpty() || !going.isEmpty()) {
			time++;

			// 계단에 3명이 될때까지, group에서 계단까지 소요 시간이 현재시간보다 작은 애들 다 넣어주기
			while ((going == null || going.size() < 3+empty) && !group.isEmpty() && group.peek() <= time) {
				group.poll();
				going.offer(len);
			}

			// 1분 내려 보냈을때 빈자리 나온거 체크
			empty = down(going);
		}
		return time;
	}

	// down: 계단을 내려가고 있는 큐 1분 진행, 남은 시간이 0인 사람들 갯수 리턴
	static int down(Queue<Integer> going) {
		int size = going.size();
		int empty = 0;
		
		// 현재 내려가고있는 시간들에 1을 빼주고 아직 시간이 남았으면 다시 큐에 삽입
		for (int i = 0; i < size; i++) {
			int len = going.poll();

			if (--len >= 0) {
				if (len == 0)
					empty++;
				going.offer(len);
			}
		}

		return empty;
	}
}