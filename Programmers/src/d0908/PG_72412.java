package d0908;

import java.util.*;

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

	static Map<String, ArrayList<Integer>> infoMap;

	static int[] solution(String[] info, String[] query) {

		infoMap = new HashMap<>();
		int[] answer = new int[query.length];

		for (int i = 0; i < info.length; i++)
			dfs(0, info[i].split(" "), "");

		for (String key : infoMap.keySet())
			Collections.sort(infoMap.get(key));

		for (int i = 0; i < query.length; i++) {
			String[] input = query[i].replace(" and", "").split(" ");
			String keyword = input[0] + input[1] + input[2] + input[3];
			int score = Integer.parseInt(input[4]);

			if (!infoMap.containsKey(keyword)) {
				answer[i] = 0;
				continue;
			}

			ArrayList<Integer> scoreList = infoMap.get(keyword);
			int start = 0;
			int end = scoreList.size() - 1;

			while (start <= end) {
				int mid = (start + end) / 2;

				if (scoreList.get(mid) < score)
					start = mid + 1;
				else
					end = mid - 1;
			}
			answer[i] = scoreList.size() - start;
		}
		return answer;
	}

	static void dfs(int index, String[] input, String output) {
		if (index == 4) {
			int score = Integer.parseInt(input[4]);

			if (!infoMap.containsKey(output))
				infoMap.put(output, new ArrayList<Integer>());

			infoMap.get(output).add(score);
			return;
		}
		dfs(index + 1, input, output + input[index]);
		dfs(index + 1, input, output + "-");
	}
}