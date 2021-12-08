package d1206;

import java.util.*;
import java.io.*;

/*
SWEA5658 보물상자 비밀번호

풀이법 : 
	 1. input을 한번더 이어 붙인 후 가능한 모든 비밀번호를 정수로 변환하여 리스트에 저장합니다.
	 2. 이미 있는 숫자이면 넣지 않고 모두 완료 후 리스트를 내림차순으로 정렬하여 답을 구합니다.

 */

public class SWEA_5658 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int t = 1; t <= testcase; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int l = n / 4; // 한변에 오는 숫자 갯수
			String input = br.readLine();
			input += input; // input값 이어붙이기

			List<Integer> num = new ArrayList<>();

			// 가능한 모든 비밀번호들 리스트에 추가
			for (int i = 0; i < l; i++) {
				for (int j = 0; j < 4; j++) {
					int pw = Integer.parseInt(input.substring(i + j * l, i + j * l + l), 16); // 문자열을 16진수형으로 정수로 변환
					if (!num.contains(pw))
						num.add(pw); // 없는 숫자만 리스트에 삽입
				}
			}
			Collections.sort(num,Collections.reverseOrder());
			System.out.println("#" + t + " " + num.get(k-1));
		}
	}
}
