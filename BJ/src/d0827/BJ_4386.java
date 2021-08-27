package d0827;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
백준 4386 별자리 만들기

풀이법: 
	완전그래프이기 때문에 Prim 알고리즘을 사용해보았습니다. (n이 최대 100이라 Dijkstra와 별반 차이 없을 것 같긴 합니다)
	n*n 인접행렬을 만들고 좌표를 입력받아 List에 저장 
	이후 입력받은 좌표와 List에 저장된 모든 점들과 거리를 계산해 인접행렬을 완성
	Prim 알고리즘을 사용해 최단거리 계산
		 
 */


public class BJ_4386 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());
		double[][] stars = new double[n][n];
		ArrayList<double[]> starPos = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 입력된 좌표 List에 저장
			double[] pos = { Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()) };
			starPos.add(pos);

			// List에 있는 좌표들과의 거리를 계산해 인접행렬 갱신
			for (int j = 0; j < starPos.size(); j++) {
				double d = dist(pos, starPos.get(j));
				stars[i][j] = d;
				stars[j][i] = d;
			}
		}

		//Prim Algorithm
		boolean[] visited = new boolean[n];
		double[] minEdge = new double[n];
		Arrays.fill(minEdge,Double.MAX_VALUE);
		minEdge[0] = 0;
		double mst=0;
		
		for (int i = 0; i < n; i++) {
			double min = Double.MAX_VALUE;
			int minJ = -1;

			for (int j = 0; j < n; j++)
				if (!visited[j]&&min>minEdge[j]) {
					min = minEdge[j];
					minJ = j;
				}

			visited[minJ]=true;
			mst+=min;
			
			for(int j=0;j<n;j++) 
				if(!visited[j]&&minEdge[j]>stars[minJ][j])
					minEdge[j]=stars[minJ][j];
		}
		//Prim Algorithm
		
		System.out.println((double)Math.round(mst*100)/100);
	}

	// dist: 두 실수 좌표간의 거리
	static double dist(double[] pos1, double[] pos2) {
		return Math.sqrt(Math.pow(pos1[0] - pos2[0], 2) + Math.pow(pos1[1] - pos2[1], 2));
	}
}