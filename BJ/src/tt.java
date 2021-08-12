import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class tt {

	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		Deque<Integer> q = new ArrayDeque<Integer>();

		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		
		q.offerFirst(8);
		q.offerFirst(7);
		q.offerFirst(6);
		q.offerFirst(5);

		System.out.println(s);
		System.out.println(q);

		s.addAll(q);
		System.out.println(s);
	}

}
