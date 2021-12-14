package d1214;

import java.util.*;

public class PG_42888 {

	public static List<String> solution(String[] record) {
		List<String> msgs = new ArrayList<>();
		Map<String, String> userID = new HashMap<String, String>();

		for (String rec : record) {
			String[] cmd = rec.split(" ");
			if (cmd.length == 3)
				userID.put(cmd[1], cmd[2]);
		}

		for (String rec : record) {
			String msg = "";
			String[] cmd = rec.split(" ");

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
