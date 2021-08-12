package d0809;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1940 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("src/d0809/input1940.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			int speed = 0;
			int dist = 0;
			int accel = 0;

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int mode = Integer.parseInt(st.nextToken());

				if (mode != 0)
					accel = Integer.parseInt(st.nextToken());

				switch (mode) {
				case 0:
					break;
				case 1:
					speed += accel;
					break;
				case 2:
					speed -= accel;
					if (speed < 0)
						speed = 0;
					break;

				}
				dist += speed;
			}
			sb.append("#" + t + " " + dist + "\n");
		}
		System.out.println(sb.toString());
	}
}
