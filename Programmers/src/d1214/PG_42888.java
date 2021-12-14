package d1214;

import java.util.*;

/*
프로그래머스42888 오픈채팅방

풀이법 : 
	 1. Map을 사용해 ID와 닉네임을 관리합니다. 
	 2. record에서 Enter와 Change를 먼저 처리하여 최종 닉네임을 저장해둡니다.
	 3. record를 다시 읽어 최종 닉네임으로 메시지를 생성합니다.

 */


public class PG_42888 {

	public static List<String> solution(String[] record) {
		List<String> msgs = new ArrayList<>();
		Map<String, String> userID = new HashMap<String, String>();

		// 명령이 Enter나 Change일 때 ID와 닉네임을 Map에 저장
		for (String rec : record) {
			String[] cmd = rec.split(" ");
			if (cmd.length == 3)
				userID.put(cmd[1], cmd[2]);
		}

		// record를 다시 읽어 들여 Enter와 Leave일 때 메시지 생성
		for (String rec : record) {
			String msg = "";
			String[] cmd = rec.split(" ");

			// 닉네임 변경은 건너뛰기
			if (cmd[0].equals("Change"))
				continue;

			msg += userID.get(cmd[1]) + "님이 ";

			if (cmd[0].equals("Enter"))
				msg += "들어왔습니다.";
			else if (cmd[0].equals("Leave"))
				msg += "나갔습니다.";

			msgs.add(msg);
		}

		return msgs;
	}
}
