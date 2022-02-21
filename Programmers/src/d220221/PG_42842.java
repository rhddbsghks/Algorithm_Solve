package d220221;

public class PG_42842 {

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int n = brown + yellow;

        for (int i = n - 1; i > 1; i--) {
            if (n % i != 0) {
                continue;
            }

            int w = i;
            int h = n / i;

            if ((w - 2) * (h - 2) == yellow) {
                answer[0] = w;
                answer[1] = h;
                break;
            }
        }

        return answer;
    }
}
