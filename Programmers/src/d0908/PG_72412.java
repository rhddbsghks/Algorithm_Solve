package d0908;

import java.util.*;

/*
프로그래머스 72412 순위 검색

풀이법 : 
	info를 파싱하여 각 Set에 인덱서 값들을 저장해둡니다.
	이후 쿼리를 하나 가져오고 info의 모든 인덱스 Set을 탐색하여 마지막으로 스코어까지 비교해줍니다.
		 
이게 3번째 방법인데 뭔 짓을 해도 효율성은 못 뚫네요...
결국 O(N^2)이라 큰 차이가 없었던 것 같네요.
 */

public class PG_72412 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(
				new String[] { "java backend junior pizza 150", "python frontend senior chicken 210",
						"python frontend senior chicken 150", "cpp backend senior pizza 260",
						"java backend junior chicken 80", "python backend senior chicken 50" },
				new String[] { "java and backend and junior and pizza 100",
						"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
						"- and backend and senior and - 150", "- and - and - and chicken 100",
						"- and - and - and - 150" })));

	}

	static String[][] info;
	static String[][] query;
	static int result;

	// 매개변수를 전역으로 쓰려고 이름을 수정했습니다.
	static int[] solution(String[] inf, String[] q) {
		// 입력 값을 전역 변수로 사용
		info = new String[inf.length][];
		query = new String[q.length][5];
		int[] answer = new int[q.length];

		Map<String, Set<Integer>> cache = new HashMap<>();

		Set<Integer> cpp = new HashSet<>();
		Set<Integer> java = new HashSet<>();
		Set<Integer> python = new HashSet<>();

		Set<Integer> backend = new HashSet<>();
		Set<Integer> frontend = new HashSet<>();

		Set<Integer> junior = new HashSet<>();
		Set<Integer> senior = new HashSet<>();

		Set<Integer> pizza = new HashSet<>();
		Set<Integer> chicken = new HashSet<>();

		cache.put("cpp", cpp);
		cache.put("java", java);
		cache.put("python", python);
		cache.put("backend", backend);
		cache.put("frontend", frontend);
		cache.put("junior", junior);
		cache.put("senior", senior);
		cache.put("pizza", pizza);
		cache.put("chicken", chicken);

		StringTokenizer st;

		// info 파싱
		for (int i = 0; i < inf.length; i++) {
			info[i] = inf[i].split(" ");

			if (info[i][0].equals("cpp"))
				cpp.add(i);
			else if (info[i][0].equals("java"))
				java.add(i);
			else
				python.add(i);

			if (info[i][1].equals("backend"))
				backend.add(i);
			else
				frontend.add(i);

			if (info[i][2].equals("junior"))
				junior.add(i);
			else
				senior.add(i);

			if (info[i][3].equals("pizza"))
				pizza.add(i);
			else
				chicken.add(i);
		}

		// 쿼리 한줄 파싱
		for (int i = 0; i < q.length; i++) {
			st = new StringTokenizer(q[i], " ");

			for (int j = 0; j < 4; j++) {
				if (j != 3) {
					query[i][j] = st.nextToken();
					st.nextToken(); // and 버리기
					continue;
				}

				// 마지막 음식과 점수
				query[i][3] = st.nextToken();
				query[i][4] = st.nextToken();
			}

			result = 0;

			for (int idx = 0; idx < info.length; idx++) {
				String language = query[i][0];
				String job = query[i][1];
				String career = query[i][2];
				String food = query[i][3];
				int score = Integer.parseInt(query[i][4]);
				boolean langFlag = false;
				boolean jobFlag = false;
				boolean careerFlag = false;
				boolean foodFlag = false;

				if (query[i][0].equals("-") || cache.get(language).contains(idx))
					langFlag = true;

				if (query[i][1].equals("-") || cache.get(job).contains(idx))
					jobFlag = true;

				if (query[i][2].equals("-") || cache.get(career).contains(idx))
					careerFlag = true;

				if (query[i][3].equals("-") || cache.get(food).contains(idx))
					foodFlag = true;

				// 모든 flag를 통과하면 스코어 비교
				if (langFlag && jobFlag && careerFlag && foodFlag && Integer.parseInt(info[idx][4]) >= score)
					result++;
			}
			answer[i] = result;
		}
		return answer;
	}
}