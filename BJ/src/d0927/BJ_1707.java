package d0927;

import java.util.*;
import java.io.*;

/*
백준 1707

풀이법 : 
	1. 인접리스트로 그래프를 생성합니다.
	2. vertex 구분번호를 관리할 vertex[] 배열을 선언해줍니다. 탐색하기 전에는 0이고, dfs를 돌며 1, -1을 번갈아 매겨줍니다.
	3. 다음 인접노드 번호가 0이 아니고(이미 탐색한 노드) 현재 노드와 번호가 같다면 이분그래프를 생성할 수 없습니다.
	4. 그래프가 여러개일수도 있으므로 모든 노드를 체크해줍니다.
 */

public class BJ_1707 {

	static class Node {
		int to;
		Node link;

		public Node(int to, Node link) {

			this.to = to;
			this.link = link;
		}
	}

	static int[] vertex;
	static Node[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());

		LOOP: for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			vertex = new int[v + 1]; // 노드들 1, -1 번호 관리
			graph = new Node[v + 1]; // 인접 리스트

			// 인접 리스트 생성
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				graph[from] = new Node(to, graph[from]);
				graph[to] = new Node(from, graph[to]);
			}

			// 노드들 중 0인 놈을 찾아서 dfs
			for (int i = 1; i < v + 1; i++) {
				// vertex 값이 1 or -1이면 이미 체크한 그래프
				if (vertex[i] != 0)
					continue;

				vertex[i] = 1; // 시작 번호를 1로 세팅

				// dfs가 한번이라도 false를 반환하면 다음 테케 시작
				if (!dfs(i, 1)) {
					System.out.println("NO");
					continue LOOP;
				}
			}
			System.out.println("YES");
		}
	}

	static boolean dfs(int i, int num) {

		// i 노드의 인접한 노드들 모두 dfs 태우기
		for (Node cur = graph[i]; cur != null; cur = cur.link) {
			int to = cur.to;

			// 만약 확인하려는 노드의 값이 0이 아니면 이미 다른곳에서 먼저와서 번호를 매긴상태이다.
			// 이때 내 번호와 같다면 이분그래프가 될 수 없다.
			if (vertex[to] != 0 && vertex[to] == num)
				return false;

			// 인접 노드 값이 0이면 내가 처음 번호를 매겨주는 상태이다.
			if (vertex[to] == 0) {
				vertex[to] = -num; // 1 or -1

				if (!dfs(to, -num)) // 한번이라도 false가 나오면 dfs 끝내기
					return false;
			}
		}

		return true;
	}
}