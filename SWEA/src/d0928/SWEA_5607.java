package d0928;

import java.io.*;
import java.util.*;

public class SWEA_5607 {

	static final long MOD = 1234567891;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 1; t <= testcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			long n = Long.parseLong(st.nextToken());
			long r = Long.parseLong(st.nextToken());

			long a = fact(n);
			long b = fact(r) * fact(n - r);

			System.out.println("#" + t + " " + (a * pow(b, MOD - 2)) % MOD);
		}
	}

	static long fact(long num) {
		long rem = 1;

		while (num > 0) {
			rem = (rem * num) % MOD;
			num--;
		}

		return rem;
	}

	static long pow(long n, long exp) {
		if (exp == 1)
			return n % MOD;

		long tmp = pow(n, exp / 2);

		if (exp % 2 == 1)
			return tmp % MOD * tmp % MOD * n % MOD;

		return tmp * tmp % MOD;
	}
}