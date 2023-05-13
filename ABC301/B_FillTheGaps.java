import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Main {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
 
    private void solution() throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
 
        List<Integer> res = new ArrayList<>();
        res.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i-1] - arr[i]) <= 1) {
                res.add(arr[i]);
                continue;
            }
 
            if (arr[i-1] < arr[i]) {
                for (int j = arr[i-1]+1; j < arr[i]; j++)
                    res.add(j);
            } else {
                for (int j = arr[i-1]-1; j > arr[i]; j--)
                    res.add(j);
            }
 
            res.add(arr[i]);
        }
 
        StringBuilder sb = new StringBuilder();
        for (Integer cur : res)
            sb.append(cur).append(' ');
 
        System.out.println(sb);
    }
}
