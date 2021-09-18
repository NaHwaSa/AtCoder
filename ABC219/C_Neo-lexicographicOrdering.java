import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
 
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String x = br.readLine();
        int[] order = new int[x.length()];
        for (int i = 0; i < x.length(); i++) {
            order[x.charAt(i)-'a'] = i;
        }
 
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        while (n-->0) arr[n] = br.readLine();
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int len = Math.min(o1.length(), o2.length());
 
                for (int i = 0; i < len; i++) {
                    int order1 = order[o1.charAt(i) - 'a'];
                    int order2 = order[o2.charAt(i) - 'a'];
                    if (order1 == order2) continue;
                    return order1 - order2;
                }
                return o1.length() - o2.length();
            }
        });
 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append('\n');
        }
        System.out.print(sb);
    }
}
