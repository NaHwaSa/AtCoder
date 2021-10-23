import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class Main {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.print(s.charAt(s.length()-1) == 'r' ? "er" : "ist");
    }
 
    public static void main(String[] args) throws Exception {
        solution();
    }
}
