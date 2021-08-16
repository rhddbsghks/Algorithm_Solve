package d0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2941 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		int len = input.length;
		int cnt = 0;
		for (int i = 0; i < len; i++)
			switch (input[i]) {

			case 'c':
				if (i < len - 1 && (input[i + 1] == '=' || input[i + 1] == '-'))
					i++;
				cnt++;
				break;

			case 'd':
				if (i < len - 2 && input[i + 1] == 'z' && input[i + 2] == '=')
					i += 2;

				if (i < len - 1 && input[i + 1] == '-')
					i++;

				cnt++;
				break;

			case 'l':
			case 'n':
				if (i < len - 1 && input[i + 1] == 'j')
					i++;
				cnt++;
				break;

			case 's':
			case 'z':
				if (i < len - 1 && input[i + 1] == '=')
					i++;
				cnt++;
				break;

			default:
				cnt++;
				break;
			}

		System.out.println(cnt);
	}
}