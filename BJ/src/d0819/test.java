package d0819;

import java.util.HashSet;
import java.util.Set;

public class test {

	public static void main(String[] args) {
		char[][] map = new char[][] { { 'a', 'b' }, { 'a', 'd' } };
		Set<Character> s = new HashSet<Character>();
		
		s.add(map[0][0]);
		s.add(map[0][1]);
		s.add(map[1][0]);
		
		System.out.println(s.size());

	}

}
