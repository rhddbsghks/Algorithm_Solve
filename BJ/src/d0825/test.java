package d0825;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class test {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.offer(4);
		pq.offer(40);
		pq.offer(78);
		pq.offer(24);
		pq.offer(411);
		pq.offer(49);
		pq.offer(1);

		Queue<Integer> q = new ArrayDeque<>();

		while (!pq.isEmpty()) {
			int n = pq.poll();
			q.offer(n);
		}
		System.out.println(pq.isEmpty());
		
		pq.addAll(q);
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}

	}

}
