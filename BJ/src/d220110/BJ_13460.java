package d220110;

import java.io.*;
import java.util.*;

/*
백준 13460 구슬탈출2

풀이법 :
	 1. 최소거리 문제이기때문에 BFS로 접근했습니다.
	 2. 맵 상태와 색별 좌표를 저장하는 클래스를 사용하고 조건대로 굴려봅니다..

 */


public class BJ_13460 {

    static int n, m, hole[];
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static boolean visited[][][][];

    static class Board {
        char[][] map;
        int[] blue = new int[2];
        int[] red = new int[2];

        Board(char[][] map) {
            this.map = map;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        hole = new int[2];
        Board board = new Board(new char[n][m]);
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board.map[i][j] = tmp[j];

                if (tmp[j] == 'R') {
                    board.red[0] = i;
                    board.red[1] = j;
                } else if (tmp[j] == 'B') {
                    board.blue[0] = i;
                    board.blue[1] = j;
                } else if (tmp[j] == 'O') {
                    hole[0] = i;
                    hole[1] = j;
                }
            }
        }

        System.out.println(bfs(board));
    }

    static int bfs(Board board) {
        int answer = 0;
        Queue<Board> q = new ArrayDeque<>();
        q.offer(board);

        while (answer<10&&!q.isEmpty()) {
            int size = q.size();
            answer++;

            while (size-- > 0) {
                Board cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    Board nextBoard = tilt(cur,d);
                    int redR = nextBoard.red[0];
                    int redC = nextBoard.red[1];
                    int blueR = nextBoard.blue[0];
                    int blueC = nextBoard.blue[1];

                    // 빨간색만 도착했으면 지금까지 거리 리턴
                    if(nextBoard==null)
                        return answer;

                    // 파란색이 도착하지 않았을때만 다음 Board 큐에 추가
                    if (blueR != -1 && blueC != -1&&!visited[redR][redC][blueR][blueC]) {
                        visited[redR][redC][blueR][blueC]=true;
                        q.offer(nextBoard);
                    }
                }
            }
        }

        return -1;
    }

    // d 방향으로 Board 굴리기
    static Board tilt(Board board, int d) {
        Board nextBoard = new Board(copy(board.map));
        char[][] map = nextBoard.map;
        nextBoard.blue = board.blue.clone();
        nextBoard.red = board.red.clone();
        boolean arrive = false;

        // 빨간색 먼저 굴리기
        if (isRedFirst(nextBoard, d)) {
            tiltOneMarble(nextBoard, d, 'R');
            tiltOneMarble(nextBoard, d, 'B');
        } 
        
        // 파란색 먼저 굴리기
        else {
            tiltOneMarble(nextBoard, d, 'B');
            tiltOneMarble(nextBoard, d, 'R');
        }

        if (nextBoard.red[0] == -1 && nextBoard.red[1] == -1) arrive = true;
        if (nextBoard.blue[0] == -1 && nextBoard.blue[1] == -1) arrive = false;

        // 빨간색만 도착하면 null 리턴
        if (arrive) return null;
        
        // 아니면 다음 맵 리턴
        else return nextBoard;
    }

    // 해당 방향으로 color 구슬 굴리기
    static void tiltOneMarble(Board board, int d, char color) {
        int[] pos;
        if (color == 'R') pos = board.red;
        else pos = board.blue;

        board.map[pos[0]][pos[1]] = '.';
        while (true) {
            if (board.map[pos[0]][pos[1]] == 'O') {
                pos[0] = -1;
                pos[1] = -1;
                break;
            }

            if (board.map[pos[0] + dr[d]][pos[1] + dc[d]] == '#') {
                board.map[pos[0]][pos[1]] = '#';
                break;
            }

            pos[0] += dr[d];
            pos[1] += dc[d];
        }
    }


    static char[][] copy(char[][] map) {
        char[][] tmp = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    static Boolean isRedFirst(Board board, int d) {
        switch (d) {
            case 0:
                return board.red[0] < board.blue[0];
            case 1:
                return board.red[0] > board.blue[0];
            case 2:
                return board.red[1] < board.blue[1];
            case 3:
                return board.red[1] > board.blue[1];
        }
        return false;
    }
}
