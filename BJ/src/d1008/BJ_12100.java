package d1008;

import java.util.*;
import java.io.*;

/*
백준 12100 2048(Easy)

풀이법 : 
	 1. 5번 움직여 볼 방향을 중복 수열로 생성합니다.
	 2. 4가지 방향별로 이중for문 인덱스 접근만 달리하고 Deque를 활용해 숫자를 합칩니다.
	 3. 이때 Deque에는 int[]로 값을 담아 {숫자, 합침가능여부(0 or 1)}로 저장합니다.
	 4. Deque에서 값을 뽑으며 copy를 갱신하면서 result 최댓값도 함께 갱신합니다.

시간복잡도: 최대 5번이동, 4방향이므로 4^5 = 1024

메모리도 넉넉하고 4^5이면 해볼만한 것 같아서 그냥 깡으로 다 돌렸습니다. 12094 플레버전은 어림도 없네요.

(코드 약혐 주의)
 */


public class BJ_12100 {

	static int n, result, direction[], map[][], copy[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		result = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				 map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 방향 저장할 중복수열
		direction = new int[5];
		perm(0);
		System.out.println(result);
	}

	// 중복수열 생성 및 최댓값 갱신
	static void perm(int cnt) {

		if (cnt == 5) {
			// 임시 map 생성
			copy = new int[n][n];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					copy[i][j] = map[i][j];

			// 중복수열로 생성된 5가지 방향으로 밀어보기
			for (int i = 0; i < 5; i++)
				move(direction[i]);

			return;
		}

		for (int i = 0; i < 4; i++) {
			direction[cnt] = i;
			perm(cnt + 1);
		}
	}

	// 해당 방향으로 copy 한번 밀기
	static void move(int d) {
		// n개의 Deque
		Deque<int[]>[] dqArray = new Deque[n];
		for (int i = 0; i < n; i++)
			dqArray[i] = new ArrayDeque<>();

		
		// 방향별로 접근 인덱스만 달리하여 copy[i][j] 값이 Deque의 마지막 값과 같고 아직 합치지 않은 숫자면 2배하여 삽입
		
		// 왼쪽으로 밀기
		if (d == 0) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++) {
					if (copy[i][j] == 0)
						continue;

					// Deque의 마지막 값이 현재 넣으려는 값과 같고 아직 합치지 않았으면
					if (!dqArray[i].isEmpty() && dqArray[i].peekLast()[0] == copy[i][j] && dqArray[i].peekLast()[1] == 1) {
						 // 이전 값 삭제 후 2배하여 삽입
						dqArray[i].pollLast();
						dqArray[i].offerLast(new int[] { copy[i][j] * 2, 0 });
					} else
						dqArray[i].offerLast(new int[] { copy[i][j], 1 });
				}
			copyClear();
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (!dqArray[i].isEmpty())
						result = Integer.max(result, copy[i][j] = dqArray[i].pollFirst()[0]);
		}

		// 아래로 밀기
		else if (d == 1) {
			for (int j = 0; j < n; j++)
				for (int i = n - 1; i >= 0; i--) {
					if (copy[i][j] == 0)
						continue;

					if (!dqArray[j].isEmpty() && dqArray[j].peekLast()[0] == copy[i][j] && dqArray[j].peekLast()[1] == 1) {
						dqArray[j].pollLast();
						dqArray[j].offerLast(new int[] { copy[i][j] * 2, 0 });
					} else
						dqArray[j].offerLast(new int[] { copy[i][j], 1 });
				}
			
			copyClear();
			for (int j = 0; j < n; j++)
				for (int i = n - 1; i >= 0; i--)
					if (!dqArray[j].isEmpty())
						result = Integer.max(result, copy[i][j] = dqArray[j].pollFirst()[0]);
		}

		// 오른쪽으로 밀기
		else if (d == 2) {
			for (int i = 0; i < n; i++)
				for (int j = n - 1; j >= 0; j--) {
					if (copy[i][j] == 0)
						continue;

					if (!dqArray[i].isEmpty() && dqArray[i].peekLast()[0] == copy[i][j] && dqArray[i].peekLast()[1] == 1) {
						dqArray[i].pollLast();
						dqArray[i].offerLast(new int[] { copy[i][j] * 2, 0 });
					} else
						dqArray[i].offerLast(new int[] { copy[i][j], 1 });
				}
			
			copyClear();
			for (int i = 0; i < n; i++)
				for (int j = n - 1; j >= 0; j--)
					if (!dqArray[i].isEmpty())
						result = Integer.max(result, copy[i][j] = dqArray[i].pollFirst()[0]);

		}

		// 위로 밀기
		else if (d == 3) {
			for (int j = 0; j < n; j++)
				for (int i = 0; i < n; i++) {
					if (copy[i][j] == 0)
						continue;

					if (!dqArray[j].isEmpty() && dqArray[j].peekLast()[0] == copy[i][j] && dqArray[j].peekLast()[1] == 1) {
						dqArray[j].pollLast();
						dqArray[j].offerLast(new int[] { copy[i][j] * 2, 0 });
					} else
						dqArray[j].offerLast(new int[] { copy[i][j], 1 });
				}

			copyClear();
			for (int j = 0; j < n; j++)
				for (int i = 0; i < n; i++)
					if (!dqArray[j].isEmpty())
						result = Integer.max(result, copy[i][j] = dqArray[j].pollFirst()[0]);
		}
	}

	static void copyClear() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				copy[i][j] = 0;
	}
}