package d220108;

import java.util.*;
import java.io.*;

/*
백준 2212 센서

풀이법 :
	 1. 센서 좌표들을 오름차순으로 정렬합니다.
	 2. 좌표간 거리가 큰 부분부터 영역을 구분하여 k개의 영역으로 쪼개줍니다.
	 3. k개의 영역을 각 집중국이 담당하면 거리 합이 최소가 됩니다.

 */


public class BG_2212 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int answer = 0;
		int[] sensor = new int[n];
		String[] input = br.readLine().split(" ");
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

		// 센서 좌표 입력 및 정렬 
		for (int i = 0; i < n; i++)
			sensor[i] = Integer.parseInt(input[i]);
		Arrays.sort(sensor);

		// 좌표간 거리를 우선순위큐에 내림차순으로 삽입
		for (int i = 1; i < n; i++)
			pq.offer(sensor[i] - sensor[i - 1]);

		// 거리가 먼 순으로 k-1개를 제거 (k개 영역 생성)
		for (int i = 0; i < k - 1; i++)
			pq.poll();
		
		// 나머지 거리들 합
		while(!pq.isEmpty())
			answer+=pq.poll();
		
		System.out.println(answer);
	}
}