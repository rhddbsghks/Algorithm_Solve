package d0805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 3190 뱀
풀이법 : 뱀 몸통들의 좌표값(int[])들을 Deque에 저장
사과는 1, 뱀은 2로 map에 마킹하여 게임 종료 체크

더 공부할 것 : 문제 조건을 잘 보자... ; 사과의 행열 정보를 -1을 안해줘서 애를 먹음.
 */

public class BJ_3190 {
	static int[][] map;
	static Deque<int[]> snake = new ArrayDeque<int[]>(); // 뱀이 머물고 있는 좌표들 저장
	static int snakeDir = 0; // 뱀이 바라보는 방향 ; 0:우 1:하 2:좌 3:상
	static int[] dr = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dc = { 1, 0, -1, 0 };
	static int x = 0; // 초

	static Queue<String> cmdList = new ArrayDeque<String>(); // 커멘드 리스트
	static int nowCmdSec;
	static char nowCmdDir;

	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// map 초기화 및 사과는 1로 초기화
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}

		// snake 2로 초기화
		map[0][0] = 2;
		snake.add(new int[] { 0, 0 });

		// 커멘드 저장
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++)
			cmdList.offer(br.readLine());

		// 게임 시작!
		start();
		System.out.println(x);
	}

	/****************** Main End ******************/

	/****************** Method ******************/

//	move: 게임 진행
	static void start() {
		// 첫 커멘드 파싱
		parseCmd();

		while (true) {
			// 머리 한칸 이동
			int r = snake.peekFirst()[0] + dr[snakeDir];
			int c = snake.peekFirst()[1] + dc[snakeDir];
			x++; // 1초 증가
			if (checkBoundary(r, c) && map[r][c] != 2) {

				snake.offerFirst(new int[] { r, c });

				// 사과가 아니면 꼬리제거
				if (map[r][c] != 1) {
					map[snake.peekLast()[0]][snake.peekLast()[1]] = 0;
					snake.pollLast();
				}

				map[r][c] = 2;

				if (nowCmdSec == x) {
					switch (nowCmdDir) {
					case 'L':
						snakeDir -= 1;
						if (snakeDir < 0)
							snakeDir = 3;
						break;

					case 'D':
						snakeDir = (snakeDir + 1) % 4;
						break;
					}

					if (!cmdList.isEmpty())
						parseCmd();
				}
				continue;
			}
			break;
		}
	}

//	checkBoundary: 경곗값 체크
	static boolean checkBoundary(int r, int c) {
		if (0 <= r && r < map.length && 0 <= c && c < map.length)
			return true;
		return false;
	}

// 	parseCmd(): 커멘드 큐에서 하나 뽑아서 초, 방향 정보 파싱
	static void parseCmd() {
		StringTokenizer st = new StringTokenizer(cmdList.poll());
		nowCmdSec = Integer.parseInt(st.nextToken());
		nowCmdDir = st.nextToken().charAt(0);
	}
	/****************** Method End ******************/
}