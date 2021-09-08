package d0908;

/*
프로그래머스 60059 자물쇠와 열쇠

풀이법 : 
		 1. n,m 크기가 20 이하이므로 58*58번 4번 돌려가며 진행해도 충분한 시간이므로 완전탐색으로 풀었습니다.
		 2. Lock 크기에 (Key크기-1)*2 만큼 패딩하여 lock 배열을 생성합니다.
		 3. 같은 크기로 key 배열도 생성하고, 0,0 ~ n+m-1,n+m-1을 왼쪽 상단으로 하여 원본 키를 4방향 모두 놓아봅니다.
		 4. 키를 놓았을 때 가운데 Lock 영역을 끝까지 탐색하였을 때 key와 lock이 모두 다른 값이라면 true를 리턴합니다.
		 
 */

public class PG_60059 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } },
				new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } }));
	}

	static int[][] lock; // 패딩한 lock
	static int n, m;

	// solution
	static boolean solution(int[][] k, int[][] l) {
		n = l.length;
		m = k.length;

		lock = new int[n + 2 * (m - 1)][n + 2 * (m - 1)];

		// 입력 lock을 토대로 패딩한 영역 가운데에 배치
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				lock[i + (m - 1)][j + (m - 1)] = l[i][j];

		// 좌측 상단을 0,0 ~ n+m,n+m에 맞춰 key를 놓고 4방향 체크해보기
		for (int i = 0; i < n + m - 1; i++)
			for (int j = 0; j < n + m - 1; j++)
				if (check(i, j, k)) // 맞으면 바로 true 리턴
					return true;

		return false;
	}

	// solution
	static boolean check(int r, int c, int[][] keyTry) {

		// key를 4번 돌려가며 배치해본다.
		for (int t = 0; t < 4; t++) {
			int[][] key = new int[n + 2 * (m - 1)][n + 2 * (m - 1)];

			// 패딩한 영역에 r,c를 좌측상단으로 하여 key 배치
			for (int i = r; i < r + m; i++)
				for (int j = c; j < c + m; j++)
					key[i][j] = keyTry[i - r][j - c];

			// 패딩한 lock의 시작점부터 비교해보기
			LOOP: for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// lock과 key가 한군데라도 다르면 LOOP를 종료하고 새로 시도한다.
					if (lock[i + (m - 1)][j + (m - 1)] != key[i + (m - 1)][j + (m - 1)]) {
						// 마지막까지 모두 다르면 true 리턴
						if (i == n - 1 && j == n - 1)
							return true;
						continue;
					}
					break LOOP;
				}
			}
			// 키 시계방향으로 돌리기
			lotate(keyTry);
		}
		return false;
	}
	
	// solution: 2차원 배열 시계방향으로 한번 돌리기
	static void lotate(int[][] arr) {
		int level = 0;

		// key 길이 / 2 깊이만큼 회전한다.
		while (level < m / 2) {
			// 깊이에 따라 회전 횟수가 다르다.
			for (int rot = 0; rot < m - 1 - 2 * level; rot++) {
				int tmp = arr[level][level];

				// 좌측
				for (int i = level; i < m - 1 - level; i++)
					arr[i][level] = arr[i + 1][level];
				// 하단
				for (int i = level; i < m - 1 - level; i++)
					arr[m - 1 - level][i] = arr[m - 1 - level][i + 1];
				// 우측
				for (int i = m - 1 - level; i > level; i--)
					arr[i][m - 1 - level] = arr[i - 1][m - 1 - level];
				// 상단
				for (int i = m - 1 - level; i > level; i--)
					arr[level][i] = arr[level][i - 1];

				arr[level][level + 1] = tmp;
			}
			level++;
		}
	}
}