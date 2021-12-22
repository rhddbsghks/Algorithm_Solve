package d1222;

import java.io.*;
import java.util.*;

/*
백준 10282 해킹

풀이법 : 
	 1. 의존성을 인접리스트 형태로 저장해둡니다.
	 2. 감염 정보를 pq에 딜레이시간 오름차순으로 저장 후 하나씩 감염시킵니다.
	 
	풀고 보니 다익스트라 문제였네요. 뭔가 신선했습니다.😎😎😎
 */

public class BJ_10282 {
	static int testcase, n, d, c, infection, time;
	static List<int[]> dependency[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testcase = Integer.parseInt(br.readLine());

		for (int t = 0; t < testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			infection = time = 0;

			// 의존성 인접리스트 초기화
			// i번 감염시 해당리스트 모두 감염
			dependency = new List[n + 1];
			for (int i = 1; i < dependency.length; i++)
				dependency[i] = new ArrayList<>();

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				dependency[b].add(new int[] { a, s });
			}

			simulate();
			System.out.println(infection + " " + time);
		}
	}

	static void simulate() {
		PriorityQueue<int[]> computer = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));  // 감염 대기열 
		boolean[] infected = new boolean[n + 1];
		computer.offer(new int[] { c, 0 });

		while (!computer.isEmpty()) {
			int[] curCom = computer.poll();
			int num = curCom[0];
			int delay = curCom[1];

			if (infected[num])
				continue;

			infection++;
			infected[num] = true;
			time = delay; // 지금까지 경과시간 갱신

			for (int[] dep : dependency[num])
				computer.offer(new int[] { dep[0], time + dep[1] });  // [컴퓨터 번호, 현재시간 + 딜레이시간]
		}
	}
}