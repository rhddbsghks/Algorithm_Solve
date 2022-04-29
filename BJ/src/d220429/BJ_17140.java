package d220429;

import java.io.*;
import java.util.*;

/*
백준17140 이차원 배열과 연산

풀이법 :
	 1. 이차원 List로 배열 관리
	 2. 각 행, 열별로 정렬
	 3. 배열이 작아져 r,c 조회 못하는 경우 Index 에러 주의

시간: 1h
  */

public class BJ_17140 {

	static int r, c, k, time;
	static List<List<Integer>> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		time = 0;
		map = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			List<Integer> list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			map.add(list);
		}

		while (time <= 100) {
			if (r < map.size() && c < map.get(0).size() && map.get(r).get(c) == k) {
				System.out.println(time);
				return;
			}

			time++;
			
			if (map.size() >= map.get(0).size()) {
				sortRow();
			} else {
				sortCol();
			}
		}

		System.out.println(-1);
	}

	// 열 별로 정렬
	private static void sortCol() {
		int max = map.size();
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1[1] != o2[1]) {
				return Integer.compare(o1[1], o2[1]);
			}
			return Integer.compare(o1[0], o2[0]);
		});
		List<List<Integer>> newList = new ArrayList<>();

		// 기존 열 길이만큼 ArrayList 생성
		for (int i = 0; i < max; i++) {
			newList.add(new ArrayList<>());
		}

		// 열 별로 정렬
		for (int j = 0; j < map.get(0).size(); j++) {
			Map<Integer, Integer> count = new HashMap<>();
			for (int i = 0; i < map.size(); i++) {
				int num = map.get(i).get(j);

				if (map.get(i).get(j) == 0) {
					continue;
				}

				if (count.get(num) == null) {
					count.put(num, 0);
				}

				count.put(num, count.get(num) + 1);
			}

			for (int num : count.keySet()) {
				pq.offer(new int[] { num, count.get(num) });
			}

			List<Integer> tmp = new ArrayList<>();

			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				tmp.add(cur[0]);
				tmp.add(cur[1]);
			}

			// 생성된 list보다 열이 더 많으면 더 추가
			if (tmp.size() > max) {

				for (int x = 0; x < tmp.size() - max; x++) {
					List<Integer> list = new ArrayList<>();

					for (int y = 0; y < j; y++) {
						list.add(0);
					}

					newList.add(list);
				}

				max = tmp.size();
			}

			for (int x = 0; x < max; x++) {
				if (x > tmp.size() - 1) {
					newList.get(x).add(0);
				} else {
					newList.get(x).add(tmp.get(x));
				}
			}
		}

		if (newList.size() > 100) {
			newList = newList.subList(0, 100);
		}

		map = newList;
	}

	// 행 별로 정렬
	private static void sortRow() {
		int max = 0;

		for (int i = 0; i < map.size(); i++) {

			List<Integer> row = map.get(i);
			Map<Integer, Integer> count = new HashMap<>();
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
				if (o1[1] != o2[1]) {
					return Integer.compare(o1[1], o2[1]);
				}
				return Integer.compare(o1[0], o2[0]);
			});

			for (int j = 0; j < row.size(); j++) {
				if (row.get(j) == 0) {
					continue;
				}

				if (count.get(row.get(j)) == null) {
					count.put(row.get(j), 0);
				}

				count.put(row.get(j), count.get(row.get(j)) + 1);
			}

			// 숫자 카운트한거 pq로 정렬
			for (int num : count.keySet()) {
				pq.offer(new int[] { num, count.get(num) });
			}

			List<Integer> tmp = new ArrayList<>();

			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				tmp.add(cur[0]);
				tmp.add(cur[1]);
			}

			// 정렬한 list로 대체 및 행 최대길이 갱신
			map.set(i, tmp);
			max = Integer.max(max, tmp.size());
		}

		// 모자란 부분 0 채우기 혹은 100넘어가면 자르기
		for (int i = 0; i < map.size(); i++) {
			List<Integer> row = map.get(i);
			int cnt = max - row.size();

			for (int j = 0; j < cnt; j++) {
				row.add(0);
			}

			if (row.size() > 100) {
				row = row.subList(0, 100);
			}
		}
	}
}
