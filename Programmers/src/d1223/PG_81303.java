package d1223;

import java.util.*;

/*
프로그래머스81303 표 편집

풀이법 : 
	 1. boolean[n]으로 삭제 여부를 관리합니다.
	 2. 커멘드에 맞게 인덱스를 옮기거나 삭제/복구를 수행합니다.
	 3.	인덱스를 옮길 때 삭제된 인덱스들은 건너뛰어야 하므로 각 인덱스별로 위,아래 인덱스를 관리하는 int[][]를 선언합니다.
	    ex) int[3][0] = 인덱스 3 위쪽에 삭제되지 않은 인덱스 중 제일 가까운 인덱스
	 4. 이동, 삭제, 복구 시 int[][]를 참고해 인덱스를 이동합니다.
	 5. 모두 완료 후 boolean[n]을 참고해 StringBuilder로 답을 생성합니다.
	 
	 

 */

public class PG_81303 {

	String solution(int n, int k, String[] cmds) {
		
		Stack<Integer> delete = new Stack<>(); // 삭제된 인덱스들 관리
		int[][] table = new int[n][2]; // 인덱스 연결관계
		boolean[] isDelete = new boolean[n]; // 삭제 여부
		int index = k;
		table[0][0] = table[n - 1][1] = -1;  // [][0]이 -1이면 제일 위, [][1]이 -1이면 제일 아래

		// 인덱스 관계 초기화
		for (int i = 0; i < n; i++) {
			if (i > 0)
				table[i][0] = i - 1; // i 인덱스의 위는 i-1
			if (i < n - 1)
				table[i][1] = i + 1; // i 인덱스의 아래는 i+1
		}

		for (String c : cmds) {
			String[] cmd = c.split(" ");
			int move;

			switch (cmd[0]) {
			
			// U, D는 해당 값만큼 테이블을 참고해서 인덱스를 이동시킨다.
			case "U":
				move = Integer.parseInt(cmd[1]);
				for (int i = 0; i < move; i++)
					index = table[index][0]; // 위의 인덱스로 한 번 이동
				break;

			case "D":
				move = Integer.parseInt(cmd[1]);
				for (int i = 0; i < move; i++)
					index = table[index][1]; // 아래의 인덱스로 한 번 이동
				break;

			// C는 스택에 삭제할 인덱스 삽입 후 위,아래의 인덱스들을 연결시켜준다.
			case "C":
				delete.push(index);
				isDelete[index] = true;

				int upperIndex = table[index][0];
				int lowerIndex = table[index][1];

				
				// 현재 인덱스가 제일 위 또는 아래가 아니라면 위 인덱스와 아래 인덱스 연결
				if (upperIndex != -1)
					table[upperIndex][1] = table[index][1];
				
				if (lowerIndex != -1)
					table[lowerIndex][0] = table[index][0];

				// 현재 인덱스가 제일 아래이면 위쪽 아니면 아래쪽 인덱스로 변경
				index = table[index][1] != -1 ? table[index][1] : table[index][0];

				break;

			// Z는 삭제여부 변경 후, 인덱스 관계 재조정
			case "Z":
				int restore = delete.pop();
				int tmp;
				table[restore][0] = table[restore][1] = -1; // 위 또는 아래로 연결 못하면 제일 위 or 제일 아래

				// 복구할 인덱스의 위쪽에 삭제되지 않은 인덱스 있으면 연결
				tmp = restore;
				while (tmp > 0) {
					if (!isDelete[--tmp]) {
						table[tmp][1] = restore;
						table[restore][0] = tmp;
						break;
					}
				}

				// 복구할 인덱스의 아래쪽에 삭제되지 않은 인덱스 있으면 연결
				tmp = restore;
				while (tmp < n - 1) {
					if (!isDelete[++tmp]) {
						table[tmp][0] = restore;
						table[restore][1] = tmp;
						break;
					}
				}

				isDelete[restore] = false;
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (isDelete[i])
				sb.append("X");
			else
				sb.append("O");
		}

		return sb.toString();
	}
}