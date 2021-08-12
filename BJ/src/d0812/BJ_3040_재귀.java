package d0812;

import java.util.Scanner;

public class BJ_3040_재귀 {

	static int[] dwarf;
	static int[] comb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		dwarf = new int[9];
		comb = new int[7];
		for (int i = 0; i < 9; i++)
			dwarf[i] = sc.nextInt();

		makeComb(0, 0);
	}

	static void makeComb(int start, int cnt) {

		if (cnt == 7) {
			int sum=0;
			for(int i=0;i<7;i++)
				sum+=comb[i];
			
			if(sum==100)
				for(int i=0;i<7;i++)
					System.out.println(comb[i]);
			return;
		}

		for (int i = start; i < 9; i++) {
			comb[cnt] = dwarf[i];
			makeComb(i + 1, cnt + 1);
		}
	}
}