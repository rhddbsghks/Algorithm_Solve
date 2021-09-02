package d0902;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 14503 로봇 청소기

풀이법 : 
	로봇 이동 메소드를 크게 청소, 이동, 후진 3가지로 나누고 boolean 값을 return하여 simulate() 메소드 내에서 관리하였습니다.
		 
TMI: 
	저 맞은 사람 1등 찍었습니다...! 여기에라도 자랑을..ㅎㅎ
 */

public class BJ_14503 {
	static int n, m, r, c, d, result;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++)
			map[i] = br.readLine().replace(" ", "").toCharArray();

		// 시뮬레이션 시작
		simulate();
		System.out.println(result);
	}
	/****************** Main ******************/

	
	/****************** Method ******************/
	
	// simulate: 움직이지 못하고, 후진도 못하면 return
	static void simulate() {
		while (true) {
			clean(r, c);
			if (!move() && !back())
				return;
		}
	}

	// clean: 현재 자리가 '0'이면 'c'로 바꾸고 result 카운트
	static void clean(int r, int c) {
		if (map[r][c] == '0') {
			map[r][c] = 'c';
			result++;
		}
	}

	// move: 4방을 탐색해, 갈 수 있는 곳으로 r,c,d 변경후 true 반환. 모두 못가면 false 반환
	static boolean move() {
		for (int i = 0; i < 4; i++) {
			int nextDir = (d + 3) % 4;
			int nr = r + dr[nextDir];
			int nc = c + dc[nextDir];

			if (!isValid(nr, nc)) {
				d = nextDir;
				continue;
			}

			r = nr;
			c = nc;
			d = nextDir;
			return true;
		}
		return false;
	}

	// back: 뒤쪽이 벽이면 false 반환
	static boolean back() {
		int nextDir = (d + 2) % 4;
		int nr = r + dr[nextDir];
		int nc = c + dc[nextDir];

		if (map[nr][nc] == '1')
			return false;

		r = nr;
		c = nc;
		return true;
	}

	// isValid: 청소유무 체크
	static boolean isValid(int r, int c) {
		if (map[r][c] != '0')
			return false;
		return true;
	}
	/****************** Method ******************/
}