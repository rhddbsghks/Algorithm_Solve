package d0803;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1244 {
	static int[] switches;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		switches = new int[len];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++)
			switches[i] = Integer.parseInt(st.nextToken());

		int p = Integer.parseInt(br.readLine());

		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			onoff(sex, num);
		}

		for (int i = 0; i < switches.length; i++) {
			if (i != 0 && i % 20 == 0)
				System.out.println();

			System.out.print(switches[i] + " ");
		}
	}

	static void onoff(int sex, int num) {
		if (sex == 1) {
			for (int i = num - 1; i < switches.length; i += num)
				switches[i] = switches[i] == 0 ? 1 : 0;
		}

		else {
			switches[num - 1] = switches[num - 1] == 0 ? 1 : 0;
			int lc = num - 2;
			int rc = num;
			while (lc >= 0 && rc < switches.length) {
				if (switches[lc] != switches[rc])
					break;

				switches[lc] = switches[rc] = switches[lc] == 0 ? 1 : 0;
				lc-=1;
				rc+=1;
			}
		}
	}
}