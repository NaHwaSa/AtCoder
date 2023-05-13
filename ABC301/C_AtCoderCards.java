import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Main {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
 
    private void solution() throws Exception {
        String s = br.readLine();
        String t = br.readLine();
 
        int[] cnt = new int['z'-'a'+1];
        int cntS = 0;
        int cntT = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '@') {
                cntS++;
                continue;
            }
            cnt[atoi(s.charAt(i))]++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '@') {
                cntT++;
                continue;
            }
            cnt[atoi(t.charAt(i))]--;
        }
 
        for (int i = 0; i < cnt.length; i++) {
            if (i == atoi('a') || i == atoi('t') || i == atoi('c')
                    || i == atoi('o') || i == atoi('d') || i == atoi('e') || i == atoi('r'))
                continue;
 
            if (cnt[i] != 0) {
                System.out.println("No");
                return;
            }
        }
 
        String tmp = "atcoder";
        for (int i = 0; i < tmp.length(); i++) {
            int idx = atoi(tmp.charAt(i));
 
            if (cnt[idx] == 0) continue;
 
            if (cnt[idx] > 0) {
                cntT -= cnt[idx];
            } else {
                cntS += cnt[idx];
            }
        }
 
        System.out.println(cntT >= 0 && cntS >= 0 ? "Yes" : "No");
    }
 
    private int atoi(char c) {
        return c-'a';
    }
}
