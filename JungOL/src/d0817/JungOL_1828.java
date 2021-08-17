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

		// 화학물질 최저 온도순으로 정렬
		Arrays.sort(chemicals);

		// 냉장고 생성: 가능한 냉장고 범위를 공통되는 부분으로 줄이다가
		// 냉장고 리스트들의 범위 밖이면 냉장고 하나 추가
		makeRefrigerators();

		System.out.println(refrigerators.size());
	}

	static void makeRefrigerators() {
		for (int i = 0; i < n; i++) {
			// 첫 물질이면 해당 온도 범위만큼 냉장고 추가
			if (refrigerators.size() == 0) {
				refrigerators.add(new int[] { chemicals[i].x, chemicals[i].y });
				continue;
			}

			boolean flag = false;

			for (int[] ref : refrigerators) {
				// 화학물질의 최저온도가 냉장고범위 안이면 그만큼 냉장고 범위 줄이기
				if (ref[0] <= chemicals[i].x && chemicals[i].x <= ref[1]) {
					ref[0] = chemicals[i].x;
					flag = true;
				}

				// 화학물질의 최고온도가 냉장고범위 안이면 그만큼 냉장고 범위 줄이기
				if (ref[0] <= chemicals[i].y && chemicals[i].y <= ref[1]) {
					ref[1] = chemicals[i].y;
					flag = true;
				}

				// 한번이라도 맞는 냉장고 있으면 break
				if (flag)
					break;
			}

			// 맞는 냉장고 없으면 하나 생성
			if (!flag)
				refrigerators.add(new int[] { chemicals[i].x, chemicals[i].y });
		}
	}
}