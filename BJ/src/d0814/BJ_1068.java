package d0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
백준 1068 트리
풀이법 :	자바 객체의 참조값들을 적극 활용하여 문제를 해결했습니다. 혹시 구조가 잘 이해가지 않으시면 그림판으로 설명해드리겠슴다
			Node 클래스: <Node> 리스트인 nodeList를 가지고 자식 노드를 저장
			tree: <Node>리스트임. 전체 트리를 구성하는 노드들을 저장하고 각 인덱스는 노드 번호를 의미한다.
			rootList: <Node>리스트임. 부모번호가 -1, 즉 루트인 노드들을 따로 저장한다.
			
		  	
시간복잡도: N은 50이하이므로 고려하지 않음.

깨달은 점: 문제에 대해 끝없이 의심하고 멋대로 넘겨짚지 말자
 		   문제에서는 트리가 1개라고도, 이진트리라고도, 노드번호가 0부터 차례로 매긴다고도 하지 않았습니다.
 		   차례로 매기는걸 가정해서 엄청난 삽질을 했네요;..
 */

public class BJ_1068 {

	/****************** Class ******************/
	static class Node {
		ArrayList<Node> nodeList = new ArrayList<>();
	}
	/****************** Class End ******************/

	
	
	static int leafNode = 0;
	static ArrayList<Node> tree = new ArrayList<>();
	
	/****************** Main ******************/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// tree에 전체 노드 갯수만큼 Node 객체를 생성하여 추가
		for (int i = 0; i < n; i++) {
			Node newNode = new Node();
			tree.add(newNode);
		}

		ArrayList<Node> rootList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		// tree에서 Node별로 부모-자식 관계 매핑해주는 작업
		for (int i = 0; i < n; i++) {
			
			int parentIdx = Integer.parseInt(st.nextToken()); // 입력값, 즉 부모 Node의 인덱스 값
			Node curNode = tree.get(i); // tree에서 현재 Node 로드

			// 부모 인덱스가 -1이면 rootList에 추가
			if (parentIdx == -1) {
				rootList.add(curNode);
				continue;
			}

			// 부모가 있으면 tree에서 부모 Node를 불러오고 부모의 nodeList에 현재 Node를 저장
			Node parentNode = tree.get(parentIdx);
			parentNode.nodeList.add(curNode);
		}

		// Node 하나 삭제
		int deleteNodeIdx = Integer.parseInt(br.readLine());
		tree.remove(tree.get(deleteNodeIdx));

		// rootList에 있는 루트Node들 dfs
		for (Node r : rootList)
			if (tree.contains(r)) // 루트가 삭제되었을 수도 있으므로 체크
				dfs(r);

		System.out.println(leafNode);
	}
	/****************** Main End ******************/

	
	
	/****************** Method ******************/

//	dfs: 자식이 없거나, 자식들이 하나라도 tree에 속해있지 않으면 leafNode++
	static void dfs(Node node) {
		boolean dfsCall = false;

		for (Node n : node.nodeList)
			if (tree.contains(n)) {
				dfsCall = true;
				dfs(n);
			}

		if (!dfsCall)
			leafNode++;
	}
	/****************** Method End ******************/
}