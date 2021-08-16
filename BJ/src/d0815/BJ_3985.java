package d0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_3985 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int l = Integer.parseInt(br.readLine());
		int[] rollCake = new int[l];

		int people = Integer.parseInt(br.readLine());
		int predict = 0;
		int predLen=0;
		int real = 0;
		int realLen=0;
		
		for (int i = 1; i <= people; i++) {
			String[] input = br.readLine().split(" ");
			int nowPredLen = Integer.parseInt(input[1]) - Integer.parseInt(input[0]);
			int nowRealLen = 0;
			if (nowPredLen > predLen) {
				predLen=nowPredLen;
				predict = i;
			}

			for (int j = Integer.parseInt(input[0])-1; j < Integer.parseInt(input[1]); j++)
				if (rollCake[j] == 0) {
					rollCake[j] = i;
					nowRealLen++;
				}
			if (nowRealLen > realLen) {
				realLen=nowRealLen;
				real = i;
			}
		}
		System.out.println(predict);
		System.out.println(real);
		
	}
}