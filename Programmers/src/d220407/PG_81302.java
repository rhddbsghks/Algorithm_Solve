package d220407;

public class PG_81302 {

    char[][] room;
    boolean[][] visited;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        room = new char[places.length][];

        LOOP:
        for (int p = 0; p < answer.length; p++) {
            String[] place = places[p];

            for (int i = 0; i < place.length; i++) {
                room[i] = place[i].toCharArray();
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (room[i][j] != 'P') {
                        continue;
                    }

                    visited = new boolean[5][5];

                    if (!dfs(i, j, 0)) {
                        answer[p] = 0;
                        continue LOOP;
                    }
                }
            }

            answer[p] = 1;
        }

        return answer;
    }

    boolean dfs(int i, int j, int cnt) {
        if (cnt == 2) {
            return true;
        }

        visited[i][j] = true;

        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (nr < 0 || nc < 0 || nr == 5 || nc == 5 || room[nr][nc] == 'X' || visited[nr][nc]) {
                continue;
            }

            if (room[nr][nc] == 'P' || !dfs(nr, nc, cnt + 1)) {
                return false;
            }
        }

        return true;
    }
}
