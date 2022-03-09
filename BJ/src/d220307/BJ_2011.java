package d220307;

import java.io.*;

public class BJ_2011 {

    static final int MOD = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cipher = br.readLine();

        int[] dp = new int[cipher.length()+1];
        dp[0] = 1;

        for (int i = 1; i <= cipher.length(); i++) {

            int length1 = cipher.charAt(i-1)-'0';

            if(length1!=0){
                dp[i] = dp[i-1];
            }

            if(i==1){
                continue;
            }

            int length2 = (cipher.charAt(i-2)-'0') * 10 + length1;

            if(10<=length2 && length2<=26){
                dp[i]+=dp[i-2];
                dp[i]%=MOD;
            }
        }

        System.out.println(dp[cipher.length()]);
    }
}
