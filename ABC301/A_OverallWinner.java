import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Main {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
 
    private void solution() throws Exception {
        br.readLine();
        String s = br.readLine();
        int cnt1 = 0;
        int cnt2 = 0;
        int max = 0;
        char c = ' ';
 
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') cnt1++;
            else cnt2++;
            
            if (cnt1 > max) {
                max = cnt1;
                c = 'A';
            }
            if (cnt2 > max) {
                max = cnt2;
                c = 'T';
            }
        }
 
        System.out.println(cnt1==cnt2 ? c : (cnt1>cnt2 ? 'A' : 'T'));
    }
}
