package d220305;

import java.util.*;

/*
프로그래머스92343 양과 늑대

풀이법 :
        1. dfs로 탐색을 하며 다음 방문할 수 있는 노드들을 자식들에게 복사해서 넘겨줍니다.
        2. 다음 방문할 노드들 중 자기자신으로 가는 노드는 빼줍니다.
        3. 현재 노드의 양 또는 늑대를 추가해주고 양 최대값을 갱신해줍니다.
        4. 양과 늑대 수가 같은 경우에는 더 진행하지 않고 종료합니다.

https://velog.io/@hengzizng/프로그래머스-양과-늑대
참고했습니다. 이해하는데도 오래걸렸네요..
 */


public class PG_92343 {

    static List<Integer>[] tree;
    static int n, answer;
    final static int SHEEP = 0;

    public static int solution(int[] info, int[][] edges) {
        n = info.length;
        tree = new List[n];
        answer = 0;

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            tree[from].add(to);
        }

        dfs(0, 0, 0, info, new HashSet<Integer>());

        return answer;
    }

    static void dfs(int cur, int sheep, int wolf, int[] info, Set<Integer> next) {

        // 현재 노드 참고해 양 or 늑대 추가
        if (info[cur] == SHEEP) {
            sheep++;
        } else {
            wolf++;
        }

        // 양 최대값 갱신
        answer = Math.max(answer, sheep);

        // 지금까지 끌고온 양, 늑대 수가 같다면 종료
        if (sheep == wolf) {
            return;
        }

        // 다음 방문할 노드 목록에 direct로 연결된 자식들 추가
        for (int child : tree[cur]) {
            next.add(child);
        }

        // 자신으로 가는 노드는 제거
        next.remove(cur);

        // 자신의 자식 + 부모가 갈 수 있었던 자식들 탐색
        for (int nextNode : next) {
            // 방문해볼 노드들 자식에게도 copy해서 넘겨주기
            Set<Integer> copyNext = new HashSet<>();
            copyNext.addAll(next);

            dfs(nextNode, sheep, wolf, info, copyNext);
        }
    }
}
