package d0813;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;


/*
백준 11559 뿌요뿌요
풀이법 :	bfs 메소드: 해당 좌표부터 탐색
		  	bomb 메소드: 4뿌요 이상일 때 터뜨리고 해당 line 번호Set 반환
		  	movePuyo: bombLine에 저장된 번호들 정렬
시간복잡도: 뿌요 map도 작고, 따로 고려하지 않았음.
	 
캐슬에서 삽질 좀 한 결과 이런 유형은 이제 조금 알 것 같네요. 역시 알고는 양치기가 답인가봅니다.
우리 서터디 화이팅ㅎㅎ
 */


public class BJ_11559 {

	static char map[][] = new char[12][6];
	static boolean isVisited[][] = new boolean[12][6];
	static Set<Integer> bombLine = new HashSet<Integer>();

	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	
	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int chain = 0;

		for (int i = 0; i < 12; i++)
			map[i] = br.readLine().toCharArray();

		while (true) {
			bombLine.clear();
			visitedClear();
			for (int i = 0; i < 12; i++)
				for (int j = 0; j < 6; j++)
					if (map[i][j] != '.' && isVisited[i][j] == false)
						bfs(i, j); // 첫 방문인 색 BFS

			if (bombLine.isEmpty()) // 터뜨린 라인이 없다면 종료
				break;

			movePuyo(); // 뿌요들 밑으로 정렬
			chain++;
		}

		System.out.println(chain);
	}
	/****************** Main End ******************/

	
	
	
	/****************** Method ******************/
	
//	bfs: 해당 색부터 상하좌우 탐색
	static void bfs(int r, int c) {
		isVisited[r][c] = true;
		Queue<int[]> tmpPosList = new ArrayDeque<int[]>(); // 탐색한 뿌요들 좌표 임시저장
		Queue<int[]> q = new ArrayDeque<int[]>(); // 탐색 큐
		q.add(new int[] { r, c });
		int puyo = 1;
		char nowChar = map[r][c]; // 탐색하려는 색

		while (!q.isEmpty()) {
			int[] pos = q.poll(); // 큐에서 좌표하나 뽑아서 탐색시작
			tmpPosList.add(pos);  // 탐색한 좌표는 임시저장

			// 상하좌우 탐색
			for (int i = 0; i < 4; i++) {
				int x = pos[0] + dr[i];
				int y = pos[1] + dc[i];
				
				// 유효한 좌표이면 큐에 추가
				if (isValid(x, y) && !isVisited[x][y] && map[x][y] == nowChar) {
					isVisited[x][y] = true;
					q.add(new int[] { x, y });
					puyo++;
				}
			}
		}

		// 4뿌요 이상일 때
		if (puyo >= 4)
			bombLine.addAll(bomb(tmpPosList)); // 임시좌표 뿌요들 터뜨리고 터뜨린 line 번호 저장
	}

	
//	bomb: 해당 좌표 뿌요들 터뜨리고 터뜨린 line 번호 Set 반환
	static Set<Integer> bomb(Queue<int[]> posList) {
		Set<Integer> bombL = new HashSet<Integer>();
		while (!posList.isEmpty()) {
			int[] pos = posList.poll();
			map[pos[0]][pos[1]] = '.';
			bombL.add(pos[1]);
		}
		return bombL;
	}

	
//	bombLine: bombLline에 저장된 line의 뿌요들 밑으로 정렬
	static void movePuyo() {
		for (int posY : bombLine) {
			while (true) {
				boolean check = false;
				for (int i = 0; i < 11; i++)
					// 해당 좌표가 색이면서 밑이 .이라면 자리 교체
					if (map[i][posY] != '.' && map[i + 1][posY] == '.') {
						map[i + 1][posY] = map[i][posY];
						map[i][posY] = '.';
						check = true;
					}
				if (!check)
					break;
			}
		}
	}

	
//	isValid: 유효 인덱스 검사
	static boolean isValid(int i, int j) {
		if (0 <= i && i < 12 && 0 <= j && j < 6)
			return true;
		return false;
	}

	
//	visitedClear: 방문체크 초기화
	static void visitedClear() {
		for (int i = 0; i < 12; i++)
			for (int j = 0; j < 6; j++)
				isVisited[i][j] = false;
	}
	/****************** Method End ******************/
}