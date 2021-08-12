package d0804;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_1764 {

	public static void main(String[] args) throws Exception {
		HashSet<String> nolisten = new HashSet<String>();
		ArrayList<String> nolistensee = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++)
			nolisten.add(br.readLine());

		for (int i = 0; i < m; i++) {
			String str = br.readLine();
			if (nolisten.contains(str))
				nolistensee.add(str);
		}

		nolistensee.sort((o1, o2) -> o1.compareTo(o2));

		sb.append(nolistensee.size() + "\n");
		for (String name : nolistensee)
			sb.append(name + "\n");

		System.out.println(sb.toString());
	}
}
