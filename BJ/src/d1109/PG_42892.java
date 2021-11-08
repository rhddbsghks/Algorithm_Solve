package d1109;

import java.util.ArrayList;
import java.util.List;

/*
프로그래머스42892 길 찾기 게임

풀이법 : 
	 1. 입력 배열을 y 내림차순, x 오름차순으로 정렬해줍니다.
	 2. 해당 배열의 마지막 노드는 root이고 root의 x를 기준으로 왼쪽자식배열, 오른쪽 자식배열로 나누어 재귀적으로 트리를 생성합니다.
	 3. 생성된 트리로 preorder와 postorder 순회를 해줍니다.

 */

public class PG_42892 {
	class Node {
		int no;
		int x;
		int y;
		Node left;
		Node right;

		Node(int no, int x, int y) {
			this.no = no;
			this.x = x;
			this.y = y;
		}
	}

	List<Integer> pre, post;

	public List<List<Integer>> solution(int[][] nodeinfo) {
		List<int[]> node = new ArrayList<>();
		pre = new ArrayList<>();
		post = new ArrayList<>();

		// 입력 info에 각 노드 번호 추가해서 새로운 List 생성
		for (int i = 0; i < nodeinfo.length; i++)
			node.add(new int[] { nodeinfo[i][0], nodeinfo[i][1], i + 1 });

		// node list를 y내림차순, x 오름차순으로 정렬
		node.sort((o1, o2) -> {
			if (o1[1] != o2[1])
				return Integer.compare(o1[1], o2[1]);
			return Integer.compare(o1[0], o2[0]);
		});

		// node list에서 마지막 값을 root로 설정하고 list에서 제거
		int[] R = node.get(node.size() - 1);
		Node root = new Node(R[2], R[0], R[1]);
		node.remove(node.size() - 1);

		// root와 나머지 node list를 가지고 트리 생성
		makeTree(root, node);

		// root부터 preorder, postorder 생성
		preorder(root);
		postorder(root);

		List<List<Integer>> answer = new ArrayList<>();
		answer.add(pre);
		answer.add(post);
		return answer;
	}

	// child list를 왼쪽, 오른쪽으로 나누고 각 배열의 끝 값이 parent의 leftNode, rightNode가 된다.
	void makeTree(Node parent, List<int[]> child) {
		List<int[]> leftChild = new ArrayList<>();
		List<int[]> rightChild = new ArrayList<>();

		// child 리스트의 노드들 중 parent의 x값보다 작으면 왼쪽, 크면 오른쪽
		for (int[] c : child) {
			if (c[0] < parent.x)
				leftChild.add(c);
			else
				rightChild.add(c);
		}

		// leftChild 배열이 있으면 끝 값을 parent의 leftNode로 연결하고 끝값 제거 후 재귀
		if (leftChild.size() > 0) {
			// leftChild 배열의 끝값으로 leftNode 생성
			int[] left = leftChild.get(leftChild.size() - 1);
			leftChild.remove(leftChild.size() - 1);
			Node leftNode = new Node(left[2], left[0], left[1]);
			
			// parent의 왼쪽에 이어주고 재귀호출
			parent.left = leftNode;
			makeTree(leftNode, leftChild);
		}

		// 위와 같이 rightChild도 재귀
		if (rightChild.size() > 0) {
			int[] right = rightChild.get(rightChild.size() - 1);
			rightChild.remove(rightChild.size() - 1);
			Node rightNode = new Node(right[2], right[0], right[1]);
			parent.right = rightNode;

			makeTree(rightNode, rightChild);
		}
	}

	void preorder(Node node) {
		pre.add(node.no);

		if (node.left != null)
			preorder(node.left);

		if (node.right != null)
			preorder(node.right);
	}

	void postorder(Node node) {
		if (node.left != null)
			postorder(node.left);

		if (node.right != null)
			postorder(node.right);

		post.add(node.no);
	}
}