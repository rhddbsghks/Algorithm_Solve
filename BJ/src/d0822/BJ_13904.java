package d0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
백준 13904 과제

풀이법 : 1. 과제들을 deadline 별로 내림차순으로 저장한다.
		2. deadline 순서대로 점수를 탐색하고 해당 deadline 까지의 과제 중 가장 높은 점수의 과제를 선택한다.
		3. 만약 해당 deadline 까지의 과제가 2개 이상이었다면, 다음으로 점수가 높은 과제를 앞서 선택한 과제들 중 최소 점수와 비교하여 더 높다면 대체해준다.
		4. 3 작업을 앞선 과제들을 대체할 수 없을 때까지 반복한다.
		5. 완성된 socre 리스트들을 더해준다.
		 
시간복잡도: 잘 모르겠음..
 */

public class BJ_13904 {
	static PriorityQueue<Integer>[] work; // 각 deadline별 과제 점수 큐 배열 ex) work[1]에는 deadline이 1인 과제들의 점수가 내림차순으로 정렬되어 있다.
	static PriorityQueue<Integer> score; // n일차까지 획득한 점수 큐
	static int maxDay; // 전체 과제 중 최대 deadline

	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] input = new int[n][2];

		score = new PriorityQueue<>();
		maxDay = 0;

		// input 데이터를 임시 저장하고, 최장 deadline을 찾는다.
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());

			maxDay = Math.max(input[i][0], maxDay);
		}

		work = new PriorityQueue[maxDay + 1]; // deadline 배열; 인덱스 0은 사용하지 않는다.
		for (int day = 1; day <= maxDay; day++)
			work[day] = new PriorityQueue<>(Collections.reverseOrder()); // dealine 별로 과제 점수를 내림차순으로 저장하는 큐를 생성한다.

		for (int i = 0; i < n; i++) {
			int deadLine = input[i][0];
			int score = input[i][1];

			work[deadLine].offer(score); // 임시저장한 input을 참고하여 deadline별로 과제 점수를 큐에 저장한다.
		}

		makeScore(); // score 생성하기
		System.out.println(getScore());
	}
	/****************** Main End ******************/

	
	
	/****************** Method ******************/

	// getScoreList: 과제를을 deadline 순으로 탐색하여 최고의 score 리스트를 생성한다.
	static void makeScore() {
		for (int day = 1; day <= maxDay; day++) { // day 순으로 탐색

			// 해당 deadline 까지의 과제가 없다면 0점을 얻는다.
			if (work[day].size() == 0) {
				score.offer(0);
				continue;
			}

			// 과제가 있다면
			score.add(work[day].poll()); // 해당 dedline의 과제 중 최고 점수를 score에 추가

			// 해볼 수 있는 과제가 더 남아 있다면 남은 점수 중 최고 점수를 score리스트의 최소점수와 비교해본다.
			while (!work[day].isEmpty()) {
				int remainMaxScore = work[day].poll();

				// 남은 최고 점수가 score의 최소 점수보다 높으면 대체해준다.
				if (remainMaxScore > score.peek()) {
					score.poll();  // 최소 점수 제거
					score.offer(remainMaxScore);
					continue;
				}
				break; // 대체할 점수가 없으면 종료
			}
		}
	}

	// getScore: score 합
	static int getScore() {
		int sum = 0;
		for (int s : score)
			sum += s;
		return sum;
	}
	/****************** Method End ******************/
}