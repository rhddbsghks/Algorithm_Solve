package d1101;

import java.util.*;

/*
프로그래머스 64064 불량 사용자

풀이법 : 
	1. dfs로 불량 사용자를 체크하고 사용자 목록을 비트마스킹으로 관리합니다. 
	2. 가능한 경우의 수 bit를 set으로 관리하여 result를 갱신합니다. 

 */


public class PG_64064 {

	int result;
	String[] user, ban;
	boolean[] checked;
	Set<Integer> checkBit;

	public int solution(String[] user_id, String[] banned_id) {
		result = 0;
		user = user_id;
		ban = banned_id;
		checkBit = new HashSet<>();

		dfs(0, 0);
		return result;
	}

	public void dfs(int cnt, int bit) {
		if (cnt == ban.length) {
			// 해당 비트를 이미 체크했다면 종료
			if (checkBit.contains(bit))
				return;

			checkBit.add(bit);
			result++;
			return;
		}

		// ban[cnt] 아이디를 모든 유저 체크해보기
		for (int i = 0; i < user.length; i++) {
			if ((bit & 1 << i) == 0 && isBan(ban[cnt], user[i])) {
				dfs(cnt + 1, bit | 1 << i);
			}
		}
	}

	// banId와 userId를 비교해 ban 여부 리턴
	public boolean isBan(String banId, String userId) {
		if (banId.length() != userId.length())
			return false;

		for (int i = 0; i < banId.length(); i++) {
			if (banId.charAt(i) != '*' && banId.charAt(i) != userId.charAt(i))
				return false;
		}
		return true;
	}
}