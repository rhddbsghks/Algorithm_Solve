package d0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
백준 1339 단어 수학

풀이법: 최대 10!이기 때문에 여유롭게 브루트포스로 해결했습니다. 
시간복잡도: 최대 10! = 3,628,800이므로 무지성으로 다 계산함

https://www.acmicpc.net/source/31994938 
이분 코드를 보고 무지성으로 계산해 보기 전에 한번 더 생각해 보는 것도 좋을 것 같다고 느꼈습니다.
동석님도 이런식으로 짜신 것 같네요.(따봉)
 */


public class BJ_1339 {

	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] nums = new String[n];  // 입력값 배열
		Set<Character> alphaSet = new HashSet<Character>(); // 사용된 알파벳 Set
		int maxSum = 0;

		for (int i = 0; i < n; i++) {
			nums[i] = br.readLine();
			for (char c : nums[i].toCharArray())
				alphaSet.add(c);
		}

		// Permutaion 배열 초기화
		char p[] = new char[alphaSet.size()];
		char x = '9';
		for (int i = p.length - 1; i >= 0; i--)
			p[i] = x--;

		// 인덱스 처리를 위한 알파벳 List
		ArrayList<Character> alphaList = new ArrayList<Character>(alphaSet);

		// 무지성 계산
		do {
			int newSum = calSum(nums, alphaList, p);
			if (newSum > maxSum)
				maxSum = newSum;
		} while (np(p));

		System.out.println(maxSum);
	}
	/****************** Main End ******************/

	
	
	/****************** Method ******************/
	// calSum: 입력값, 알파벳리스트, 순열을 입력받아 총합 계산
	static int calSum(String[] nums, ArrayList<Character> alphaList, char[] p) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			char[] num = nums[i].toCharArray(); // ex. {'A', 'B', 'C', 'A'}

			for (int j = 0; j < num.length; j++)
				num[j] = p[alphaList.indexOf(num[j])]; // 해당 알파벳에 부여된 숫자로 치환

			sum += Integer.parseInt(new String(num));
		}
		return sum;
	}

	// Next Permutaion
	static boolean np(char[] arr) {
		int n = arr.length;

		int i = n - 1;
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;
		if (i == 0)
			return false;

		int j = n - 1;
		while (arr[i - 1] >= arr[j])
			j--;

		char tmp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = tmp;

		Arrays.sort(arr, i, n);

		return true;
	}
	/****************** Method End ******************/
}