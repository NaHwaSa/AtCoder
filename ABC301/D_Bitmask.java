import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Main {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
 
    private void solution() throws Exception {
        String s = br.readLine();
        long n = Long.parseLong(br.readLine());
 
        long lowNum = Long.parseLong(s.replaceAll("\\?", "0"), 2);
        if (lowNum > n) {
            System.out.println(-1);
            return;
        }
        if (n == lowNum) {
            System.out.println(n);
            return;
        }
 
        long highNum = Long.parseLong(s.replaceAll("\\?", "1"), 2);
        if (highNum <= n) {
            System.out.println(highNum);
            return;
        }
 
        String num = Long.toString(n, 2);
        int numLen = num.length();
        for (int i = 0; i < s.length()-numLen; i++) {
            num = "0" + num;
        }
 
        StringBuilder sb = new StringBuilder();
        int idxTmp = -1;
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = num.charAt(i);
 
            if (c1 == '?' && c2 == '1') idxTmp = i;
 
            if (c1 == c2) {
                sb.append(c1);
                continue;
            }
 
            if (c1 == '0' && c2 == '1') {
                for (int j = i; j < s.length(); j++) {
                    sb.append(s.charAt(j) == '?' ? '1' : s.charAt(j));
                }
                break;
            }
 
            if (c1 == '1' && c2 == '0') {
                sb = new StringBuilder();
                for (int j = 0; j < idxTmp; j++) {
                    sb.append(num.charAt(j));
                }
                sb.append('0');
 
                for (int j = idxTmp+1; j < s.length(); j++) {
                    sb.append(s.charAt(j) == '?' ? '1' : s.charAt(j));
                }
                break;
            }
 
            sb.append(c2);
        }
 
        System.out.println(Long.parseLong(sb.toString(), 2));
    }
}
