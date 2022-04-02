package d220331;

/*
프로그래머스12920 선입 선출 스케줄링

풀이법 :
	 1. 작업 처리 시간을 이분탐색으로 하여 n개 수행 여부를 체크합니다.
	 2. 해당 작업 시간까지 프로세스를 n개 이상 했다면 해당 시간에 추가했던 작업들을 뒤에서부터 빼주며 n개가 되는 지점을 찾습니다.

우선순위큐를 쓰면 터지는 악질 문제...
 */


public class PG_12920 {

    public int solution(int n, int[] cores) {

        int answer = 0;

        if (n <= cores.length) {
            return n;
        }

        int left = 0;
        int right = cores[0] * n;

        while (left <= right) {
            int mid = (left + right) / 2;
            int totalProcess = 0;

            // mid 시간까지 core에 올라간 작업 수
            // mid 시간이 작업 시간으로 나누어 떨어질 때 추가할 수 있다.
            for (int core : cores) {
                totalProcess += mid / core + 1;
            }

            if (totalProcess < n) {
                left = mid + 1;
            }

            // 총 작업량이 n개 이상이면 total이 n개가 될 때까지 추가했던 작업을 뒤에서부터 빼본다.
            else {
                for (int i = cores.length - 1; i >= 0; i--) {
                    int core = cores[i];

                    if (mid % core == 0) {
                        if (totalProcess == n) {
                            return i + 1;
                        }

                        totalProcess--;
                    }
                }

                right = mid - 1;
            }
        }

        return answer;

//        int answer = 0;
//        int time = 0;
//
//        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
//            if (o1[0] != o2[0]) {
//                return Integer.compare(o1[0], o2[0]);
//            }
//            return Integer.compare(o1[1], o2[1]);
//        });
//
//        for (int i = 0; i < cores.length; i++) {
//            pq.offer(new int[]{cores[i], i + 1});
//            n--;
//        }
//
//        while (n-- > 0) {
//            int[] cur = pq.poll();
//            time = cur[0];
//            int emptyCore = cur[1];
//            int processTime = cores[emptyCore - 1];
//
//            answer = emptyCore;
//
//            pq.offer(new int[]{time + processTime, emptyCore});
//        }
//        return answer;
    }
}
