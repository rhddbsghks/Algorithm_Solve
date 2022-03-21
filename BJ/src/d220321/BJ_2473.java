package d220321;

import java.io.*;
import java.util.*;

/*
백준2473 세 용액

풀이법 :
	 1. 브루트포스 + 투포인터
	 2. 왼쪽 부터 하나 선택합니다.
	 3. 왼쪽+1 ~ 끝 중에서 투포인터로 최소 값을 가지는 용액 2개를 고릅니다.
	 4. 왼쪽을 하나 증가시키고 똑같은 작업을 반복합니다.

 */

public class BJ_2473 {

    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] liquid = new long[n];
        long[] answer = new long[3];
        long min = Long.MAX_VALUE;

        for(int i=0;i<n;i++){
            liquid[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(liquid);
        int first, third, second;

        for(int i=0;i<n-2;i++){
            first = i;
            third = n-1;
            second = i+1;

            while(second<third){
                long sum = liquid[first] + liquid[second] + liquid[third];

                if(Math.abs(sum)<Math.abs(min)){
                    min = sum;
                    answer[0] = liquid[first];
                    answer[1] = liquid[second];
                    answer[2] = liquid[third];
                }

                if (sum > 0) {
                    third--;
                } else if(sum<0) {
                    second++;
                }else{
                    break;
                }
            }
        }

        for(long a: answer){
            System.out.print(a+" ");
        }
    }
}
