package d0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2999 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] msg = br.readLine().toCharArray();
		int len = msg.length;

		int r = 0;

		for (int i = 1; i <= Math.sqrt(len); i++)
			if (len % i == 0)
				r = i;

		int c = len / r;
		char[][] decrpyt = new char[r][c];

		int idx = 0;
		for (int i = 0; i < c; i++)
			for (int j = 0; j < r; j++) {
				decrpyt[j][i] = msg[idx++];
				if (idx == len)
					break;
			}

		StringBuilder sb = new StringBuilder();
		
		for(char[] s:decrpyt)
			for(char cha : s)
				sb.append(cha);
		
		System.out.println(sb);
		
	}
}
