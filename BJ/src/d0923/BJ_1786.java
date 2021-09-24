package d0923;

import java.util.*;
import java.io.*;

public class BJ_1786 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		StringBuilder sb = new StringBuilder();
		int tLength = text.length, pLength = pattern.length;

		int[] pi = new int[pLength];
		for (int i = 1, j = 0; i < pLength; i++) {// i:접미사 포인터(i=1부터 시작: 우리는 실패함수를 만드는게 목적이므로 첫글자 틀리면 0위치로 가야하므로.),
													// j:접두사 포인터
			while (j > 0 && pattern[i] != pattern[j]) {
				j = pi[j - 1];
			}
			if (pattern[i] == pattern[j])
				pi[i] = ++j;
		}

		int cnt = 0;
		// i : 텍스트 포인터 , j: 패턴 포인터
		for (int i = 0, j = 0; i < tLength; ++i) {

			while (j > 0 && text[i] != pattern[j])
				j = pi[j - 1];

			if (text[i] == pattern[j]) { // 두 글자 일치
				if (j == pLength - 1) { // j가 패턴의 마지막 인덱스라면
					cnt++; // 카운트 증가
					sb.append((i + 2) - pLength + " ");
					j = pi[j];
				} else {
					j++;
				}
			}
		}

		System.out.println(cnt);
		System.out.println(sb);
	}
}
