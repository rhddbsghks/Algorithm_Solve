package d220118;

import java.util.*;

/*
프로그래머스17686 파일명 정렬

풀이법 :
	 1. String을 파싱하여 File 클래스에 저장합니다.
	 2. 이떄 head 값은 대문자들의 인덱스를 upper 리스트에 저장하고 lowercase로 변환해줍니다.
	 3. File 클래스를 담은 리스트를 조건에 맞게 정렬해줍니다.
	 4. 정렬된 File에서 파일명을 복원하고 upper를 참고하여 대문자로 변환시켜줍니다.
 */


public class PG_17686 {

    public class File {
        String head;
        String number;
        String tail;
        ArrayList<Integer> upper;

        public File(String head, String number, String tail, ArrayList<Integer> upper) {
            this.head = head;
            this.number = number;
            this.tail = tail;
            this.upper = upper;
        }
    }

    public List<String> solution(String[] files) {
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<File> list = new ArrayList<>();

        for (String file : files) {
            String head = null;
            String number = null;
            String tail = null;
            ArrayList<Integer> upper = new ArrayList<>();
            int i = 0;
            int j = 0;

            // 첫 숫자가 나오는 인덱스 탐색 및 대문자 위치 파악
            for (; i < file.length(); i++) {
                char c = file.charAt(i);
                if ('A' <= c && c <= 'Z')
                    upper.add(i);
                if (0 <= c - '0' && c - '0' <= 9) {
                    j = i;
                    break;
                }
            }

            // NUMBER가 끝나는 인덱스 탐색
            for (; j < file.length(); j++) {
                char c = file.charAt(j);
                if (c - '0' < 0 || 9 < c - '0') {
                    break;
                }
            }

            // 탐색한 인덱스를 참고하여 File 객체 리스트 생성
            head = file.substring(0, i).toLowerCase(); // 소문자로 변환
            number = file.substring(i, j);
            tail = file.substring(j, file.length());
            list.add(new File(head, number, tail, upper));
        }

        // 조건에 맞게 파일명 정렬
        list.sort(((o1, o2) -> {
            if (!o1.head.equals(o2.head))
                return o1.head.compareTo(o2.head);

            int o1n = Integer.parseInt(o1.number);
            int o2n = Integer.parseInt(o2.number);
            if (o1n != o2n)
                return Integer.compare(o1n, o2n);

            return 0;
        }));

        // 정렬된 리스트에서 파일명들 복구
        for (File file : list) {
            char[] headChar = file.head.toCharArray();
            String number = file.number;
            String tail = file.tail;
            ArrayList<Integer> upper = file.upper;

            for (int idx : upper) {
                headChar[idx] += 'A' - 'a';
            }
            answer.add(new String(headChar) + number + tail);
        }
        return answer;
    }
}
