package d0817;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JungOL_1828 {
	static Chemical[] chemicals;
	static List<int[]> refrigerators;
	static int n;

	static class Chemical implements Comparable<Chemical> {
		int x;
		int y;

		public Chemical(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Chemical o) {
			return this.x - o.x;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		chemicals = new Chemical[n];
		refrigerators = new ArrayList<int[]>();

		for (int i = 0; i < n; i++)
			chemicals[i] = new Chemical(sc.nextInt(), sc.nextInt());

		Arrays.sort(chemicals);

		makeRefrigerators();

		System.out.println(refrigerators.size());
	}

	static void makeRefrigerators() {
		for (int i = 0; i < n; i++) {
			if (refrigerators.size() == 0)
				refrigerators.add(new int[] { chemicals[i].x, chemicals[i].y });

			boolean flag = false;

			for (int[] ref : refrigerators) {

				if (ref[0] <= chemicals[i].x && chemicals[i].x <= ref[1]) {
					ref[0] = chemicals[i].x;
					flag = true;
				}

				if (ref[0] <= chemicals[i].y && chemicals[i].y <= ref[1]) {
					ref[1] = chemicals[i].y;
					flag = true;
				}

				if (flag)
					break;
			}

			if (!flag)
				refrigerators.add(new int[] { chemicals[i].x, chemicals[i].y });
		}
	}
}