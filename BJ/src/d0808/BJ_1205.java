package d0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1205 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int newScore = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int[] score = new int[n];
		if (n == 0) {
			System.out.print(1);
			return;
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++)
			score[i] = Integer.parseInt(st.nextToken());

		if (n == p && score[n - 1] >= newScore) {
			System.out.print(-1);
			return;
		}

		for (int i = 0; i < n; i++)
			if (score[i] <= newScore) {
				System.out.print(i + 1);
				return;
			}

		if (n == p)
			System.out.print(-1);
		else
			System.out.print(n + 1);

	}
}
