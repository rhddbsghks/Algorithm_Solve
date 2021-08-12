package d0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
백준 5639 이진 검색 트리
풀이법 : 전위 순회 결과를 통해 트리 생성 후 후위 순회 출력
시간복잡도 : O(N)
배열로 하다가 편향트리 생각을 못해서 2시간 날림... 
배열로 하려면 2^10000 사이즈의 배열을 생성해야한다.
 */


/****************** Class ******************/
// 이진트리 노드
class Tree {
	/****************** Class Field ******************/
	int num;
	Tree leftTree;
	Tree rightTree;

	
	/****************** Class Constructor ******************/
	Tree(int num) {
		this.num = num;
	}

	
	/****************** Class Method ******************/
	
	// addTree
	void addTree(int num) {
		if (num < this.num) {
			if (leftTree == null) {
				leftTree = new Tree(num);
				return;
			}
			leftTree.addTree(num);
		}

		else {
			if (rightTree == null) {
				rightTree = new Tree(num);
				return;
			}
			rightTree.addTree(num);
		}
	}

	// printPostOrder
	void printPostOrder() {
		if (leftTree != null)
			leftTree.printPostOrder();

		if (rightTree != null)
			rightTree.printPostOrder();

		System.out.println(num);
	}
}
/****************** Class End ******************/




public class BJ_5639 {

	/****************** Main ******************/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;

		// 트리 생성
		Tree root = new Tree(Integer.parseInt(br.readLine()));
		while ((input = br.readLine()) != null)
			root.addTree(Integer.parseInt(input));

		// 후위 순회 결과 출력
		root.printPostOrder();
	}
	/****************** Main End ******************/
}
