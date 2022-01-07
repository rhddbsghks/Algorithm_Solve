package d1213;

import java.util.*;

/*
프로그래머스42893 매칭 점수

풀이법 : 
	 1. url과 점수를 Map으로 관리합니다.
	 2. 각 url 별로 기본 점수를 계산하면서 링크가 있는 url 점수도 같이 더해줍니다.

 */


public class PG42893 {

	public static int solution(String word, String[] pages) {
		Map<String, double[]> scores = new HashMap<>(); // <url,[인덱스, 기본점수, 링크점수]
		word = word.toLowerCase(); // 검색어 소문자로 변환

		for (int index = 0; index < pages.length; index++) {
			String page = pages[index];
			page = page.toLowerCase(); // html 소문자로 변환
			String url = parseUrl(page); // html에서 url 파싱

			if (!scores.containsKey(url)) // Map에 url 없으면 삽입
				scores.put(url, new double[] { -1, 0, 0 });

			double[] score = scores.get(url);
			score[0] = index; // 인덱스 세팅
			score[1] = getScore(page, word); // 기본점수 계산
			List<String> links = parseLink(page); // html에 있는 link 리스트

			for (String link : links) {
				// Map에 해당 url 아직 없으면 생성
				if (!scores.containsKey(link))
					scores.put(link, new double[] { -1, 0, 0 });

				scores.get(link)[2] += score[1] / links.size(); // 링크 url에 링크점수 더해주기
			}
		}

		// pq로 점수 정렬
		PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1[1] != o2[1])
				return Double.compare(o2[1], o1[1]);
			return Double.compare(o1[0], o2[0]);
		});

		for (String url : scores.keySet()) {
			double[] score = scores.get(url);
			if (score[0] == -1)
				continue;

			pq.offer(new double[] { score[0], score[1] + score[2] });
		}

		return (int) pq.poll()[0];
	}

	// parseUrl: html 페이지에서 url 파싱
	static String parseUrl(String page) {
		return page.split("<meta property=\"og:url\" content=\"")[1].split("\"/>")[0];
	}

	// parseLink: html 페이지에서 링크 url 리스트
	static List<String> parseLink(String page) {
		List<String> list = new ArrayList<String>();

		String[] tmp = page.split("<a");

		for (int i = 1; i < tmp.length; i++) {
			String link = tmp[i].split(" href=\"|\">")[0];
			list.add(link);
		}



		return list;
	}

	// getScore: html 페이지에서 기본점수 계산
	static int getScore(String page, String word) {
		int score = 0;
		int len = word.length();

		// word와 일치하면서 앞이나 뒤에 문자가 알파벳이 아닌 경우만 카운트
		for (int i = 0; i <= page.length() - len; i++) {
			String sub = page.substring(i, i + len);
			if ((i > 0 && isAlpha(page.charAt(i - 1))) || (i < page.length() - len && isAlpha(page.charAt(i + len))))
				continue;

			if (sub.equals(word))
				score++;
		}

		return score;
	}

	// isAlpha: 알파벳인지 체크
	static boolean isAlpha(char c) {
		return 'a' <= c && c <= 'z';
	}
}
